package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etMobile;
    EditText etCity;
    Spinner spinner;
    String userType;
    EditText etReferralCode;


    Context context = RegisterActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Resources res = getResources();

        String farmer = String.format(res.getString(R.string.farmer));
        String dealer = String.format(res.getString(R.string.dealer));
        String broker = String.format(res.getString(R.string.broker));

        String[] paths = {farmer,dealer, broker};
        btnRegister = (Button) findViewById(R.id.register);
        etName = (EditText) findViewById(R.id.name);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etMobile = (EditText) findViewById(R.id.mobile);
        etCity = (EditText) findViewById(R.id.city);
        spinner = (Spinner) findViewById(R.id.userType);
        etReferralCode = (EditText)findViewById(R.id.referralCode);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                userType = (String) parent.getItemAtPosition(position);
                Resources res = getResources();

                if(!userType.equals(String.format(res.getString(R.string.dealer))))
                {
                    Toast.makeText(RegisterActivity.this, "Not Dealer", Toast.LENGTH_SHORT);
                    etReferralCode.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Dealer", Toast.LENGTH_SHORT);
                    etReferralCode.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String mobile = etMobile.getText().toString();
                String city = etCity.getText().toString();
                String referralCode = etReferralCode.getText().toString();

                if (name.equals(""))
                    Toast.makeText(RegisterActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                else if (email.equals(""))
                    Toast.makeText(RegisterActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                else if (password.equals(""))
                    Toast.makeText(RegisterActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                else if (password.length() < 6)
                    Toast.makeText(RegisterActivity.this, "Password length should be greater than 6", Toast.LENGTH_SHORT).show();
                else if (mobile.length() < 10)
                    Toast.makeText(RegisterActivity.this, "Mobile length should be equal to 10", Toast.LENGTH_SHORT).show();
                else if (city.equals(""))
                    Toast.makeText(RegisterActivity.this, "City cannot be empty", Toast.LENGTH_SHORT).show();
                else
                    doRegister(email, password, name, mobile, city, userType, referralCode);

            }
        });

    }

    private void doRegister(String str_email, String str_password, String str_name, String str_mobile, String str_city, String str_userType, String str_referralCode) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        Resources res = getResources();

        if (userType.equalsIgnoreCase(String.format(res.getString(R.string.farmer)))) {


            Retro.getInterface(context).registerUser(str_email, str_password, str_name, str_mobile, str_city, new Callback<GenralResponse>() {
                @Override
                public void success(GenralResponse generalResponse, Response response) {
                    progressDialog.dismiss();

                    Toast.makeText(context, generalResponse.getResult() + "", Toast.LENGTH_SHORT).show();
                    if (generalResponse.getResult().equals("success")) {
                        startActivity(new Intent(context, LoginActivity.class));
                        // finish();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    progressDialog.dismiss();
                    Log.e("RegisterActivity", " Error as " + error.getMessage());
                }
            });

        } else if (userType.equalsIgnoreCase(String.format(res.getString(R.string.broker)))) {
            {
                Retro.getInterface(context).registerBroker(str_email, str_password, str_name, str_mobile, str_city, str_referralCode, new Callback<GenralResponse>() {
                    @Override
                    public void success(GenralResponse generalResponse, Response response) {
                        progressDialog.dismiss();

                        Toast.makeText(context, generalResponse.getResult() + "", Toast.LENGTH_SHORT).show();
                        if (generalResponse.getResult().equals("success")) {
                            startActivity(new Intent(context, LoginActivity.class));
                            // finish();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        Log.e("RegisterActivity", " Error as " + error.getMessage());
                    }
                });
            }
        } else {
            {


                Retro.getInterface(context).registerDealer(str_email, str_password, str_name, str_mobile, str_city, str_referralCode,new Callback<GenralResponse>() {
                    @Override
                    public void success(GenralResponse generalResponse, Response response) {
                        progressDialog.dismiss();

                        Toast.makeText(context, generalResponse.getResult() + "", Toast.LENGTH_SHORT).show();
                        if (generalResponse.getResult().equals("success")) {
                            startActivity(new Intent(context, LoginActivity.class));
                            // finish();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        Log.e("RegisterActivity", " Error as " + error.getMessage());
                    }
                });
            }
        }
    }
}
