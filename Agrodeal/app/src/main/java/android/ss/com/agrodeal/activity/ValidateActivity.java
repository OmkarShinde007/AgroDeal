package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ValidateActivity extends AppCompatActivity {

    String userId;
    String otp, userType;
    Context context = ValidateActivity.this;
    EditText otpEd;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        otpEd = findViewById(R.id.otp);
        submit = findViewById(R.id.submit);

        Bundle bundle = getIntent().getExtras();
        userId = bundle.getString("userId");
        userType= bundle.getString("userType");

        final ProgressDialog progressDialog = new ProgressDialog(context);

        Retro.getInterface(context).checkValid(userId, userType, new Callback<GenralResponse>() {
            @Override
            public void success(GenralResponse genralResponse, Response response) {
                progressDialog.dismiss();

                if (genralResponse.getResult().equalsIgnoreCase("success")) {

                    if (userType.equalsIgnoreCase("farmer"))
                        startActivity(new Intent(context, HomeActivity.class));
                    if (userType.equalsIgnoreCase("dealer"))
                        startActivity(new Intent(context, DealerHomeActivity.class));
                    if (userType.equalsIgnoreCase("broker"))
                        startActivity(new Intent(context, BrokerHomeActivity.class));
                    // finish();
                } else {
                    Toast.makeText(context, "Please enter verification code", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Toast.makeText(context, "Retro error", Toast.LENGTH_SHORT).show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otp = otpEd.getText().toString();

                Retro.getInterface(context).validate(userId, userType, otp, new Callback<GenralResponse>() {
                    @Override
                    public void success(GenralResponse genralResponse, Response response) {
                        progressDialog.dismiss();

                        if (genralResponse.getResult().equalsIgnoreCase("success")) {

                            if (userType.equalsIgnoreCase("farmer"))
                                startActivity(new Intent(context, HomeActivity.class));
                            if (userType.equalsIgnoreCase("dealer"))
                                startActivity(new Intent(context, DealerHomeActivity.class));
                            if (userType.equalsIgnoreCase("broker"))
                                startActivity(new Intent(context, BrokerHomeActivity.class));
                            // finish();
                        } else {
                            Toast.makeText(context, "Wrong verification code", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(context, LoginActivity.class));
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Retro error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
