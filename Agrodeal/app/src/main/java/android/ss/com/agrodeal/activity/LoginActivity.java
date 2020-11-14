package android.ss.com.agrodeal.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.ss.com.agrodeal.MainActivity;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.LoginResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etEmail;
    EditText etPassword;
    TextView txtvRegister;
    ImageButton img_chnagip;
    Spinner spinner;

    String userType;


    Context context=LoginActivity.this;
    private String TAG = LoginActivity.class.getSimpleName();
    Shareprefrance shareprefrance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Resources res = getResources();

        String farmer = String.format(res.getString(R.string.farmer));
        String dealer = String.format(res.getString(R.string.dealer));
        String broker = String.format(res.getString(R.string.broker));

        String[] paths = {farmer,dealer, broker};

        shareprefrance = new Shareprefrance();
        btnLogin =  findViewById(R.id.login);
        etEmail =  findViewById(R.id.email);
        etPassword =  findViewById(R.id.password);
        txtvRegister =  findViewById(R.id.register);
        img_chnagip =  findViewById(R.id.img_chnagip);

        spinner = findViewById(R.id.userType);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                userType = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        txtvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,RegisterActivity.class);
                startActivity(intent);
            }
        });

        img_chnagip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog d = new Dialog(context);
                d.setTitle("Set IP");
                d.setContentView(R.layout.dialog);
                final EditText ip = (EditText) d.findViewById(R.id.ip);
                Button submit = (Button) d.findViewById(R.id.submit);
                String ipStr = shareprefrance.getServerURL(context);
                ip.setText(ipStr);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = ip.getText().toString();
                        shareprefrance.setServerURL(context, str);
                        d.dismiss();
                    }
                });
                d.show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.equals(""))
                    Toast.makeText(LoginActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                else if (password.equals(""))
                    Toast.makeText(LoginActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                else if (password.length() < 6)
                    Toast.makeText(LoginActivity.this, "Password length should be greater than 6", Toast.LENGTH_SHORT).show();
                else

                    doLogin(email,password,userType);

            }
        });
    }

    private void doLogin(String str_email,String str_password, String str_userType) {

        final ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        try {
            Resources res = getResources();

            if (str_userType.equalsIgnoreCase(String.format(res.getString(R.string.farmer)))) {
                Retro.getInterface(context).loginUser(str_email, str_password, new Callback<LoginResponse>() {
                    @Override
                    public void success(LoginResponse loginResponse, Response response) {
                        progressDialog.dismiss();
                        System.out.println(loginResponse.getResult());
                        if (loginResponse.getResult().equals("success")) {
                            Shareprefrance.loginUser(context, loginResponse.getName(), loginResponse.getUserId(), loginResponse.getMobile(), loginResponse.getEmail(), true,loginResponse.getUserType());

                            Intent intent = new Intent(context,ValidateActivity.class);

                            intent.putExtra("userId",Shareprefrance.getUserId(context));
                            intent.putExtra("userType","farmer");

                            startActivity(intent);
                            // finish();
                        } else {
                            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(context, LoginActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        Log.e(TAG, " Error as " + error.getMessage());
                    }
                });
            } else if (str_userType.equalsIgnoreCase(String.format(res.getString(R.string.broker)))) {
                Retro.getInterface(context).brokerLogin(str_email, str_password, new Callback<LoginResponse>() {
                    @Override
                    public void success(LoginResponse loginResponse, Response response) {
                        progressDialog.dismiss();
                        System.out.println(loginResponse.getResult());
                        if (loginResponse.getResult().equals("success")) {
                            Shareprefrance.loginUser(context, loginResponse.getName(), loginResponse.getUserId(), loginResponse.getMobile(), loginResponse.getEmail(), true,loginResponse.getUserType());

                            Intent intent = new Intent(context,ValidateActivity.class);

                            intent.putExtra("userId",Shareprefrance.getUserId(context));
                            intent.putExtra("userType","broker");

                            startActivity(intent);
                            // finish();
                        } else {
                            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(context, LoginActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        Log.e(TAG, " Error as " + error.getMessage());
                    }
                });
            } else {
                Retro.getInterface(context).dealerLogin(str_email, str_password, new Callback<LoginResponse>() {
                    @Override
                    public void success(LoginResponse loginResponse, Response response) {
                        progressDialog.dismiss();
                        System.out.println(loginResponse.getResult());
                        if (loginResponse.getResult().equals("success")) {
                            Shareprefrance.loginUser(context, loginResponse.getName(), loginResponse.getUserId(), loginResponse.getMobile(), loginResponse.getEmail(), true,loginResponse.getUserType());

                            Intent intent = new Intent(context,ValidateActivity.class);

                            intent.putExtra("userId",Shareprefrance.getUserId(context));
                            intent.putExtra("userType","dealer");

                            startActivity(intent);
                            // finish();
                        } else {
                            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            // shareprefrance.loginUser(context, loginResponse.getName(), loginResponse.getId(), true);
                            startActivity(new Intent(context, LoginActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        Log.e(TAG, " Error as " + error.getMessage());
                    }
                });
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}


