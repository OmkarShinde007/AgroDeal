package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.ss.com.agrodeal.MainActivity;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.BrokerResponse;
import android.ss.com.agrodeal.retro.FarmerResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.ss.com.agrodeal.retro.ReviewResponse;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BrokerHomeActivity extends AppCompatActivity {

    Button btnBroAuctions;
    TextView wallet,referralCode;
    Context context = BrokerHomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker_home);

        btnBroAuctions = findViewById(R.id.btnBroAuctions);
        wallet = findViewById(R.id.wallet);
        referralCode = findViewById(R.id.referralCode);

        btnBroAuctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,AllAuctionsActivity.class));
            }
        });
        int brokerId = Integer.parseInt(Shareprefrance.getUserId(context));
        final ProgressDialog progressDialog = new ProgressDialog(context);
        Retro.getInterface(context).viewBroker(brokerId,new Callback<BrokerResponse>() {
            @Override
            public void success(BrokerResponse brokerResponse, Response response) {
                progressDialog.dismiss();

                if (brokerResponse.getResult().equalsIgnoreCase("success")) {

                    referralCode.setText("ReferralCode: "+brokerResponse.getReferralCode());
                    wallet.setText("Wallet: Rs. "+brokerResponse.getWallet());

                    // finish();
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(context, HomeActivity.class));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Toast.makeText(context, "Retro error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.home:
                String userType = Shareprefrance.getUserType(context);
                if (userType.equalsIgnoreCase("farmer"))
                    startActivity(new Intent(context, HomeActivity.class));
                if (userType.equalsIgnoreCase("dealer"))
                    startActivity(new Intent(context, DealerHomeActivity.class));
                if (userType.equalsIgnoreCase("broker"))
                    startActivity(new Intent(context, BrokerHomeActivity.class));
                return true;

            case R.id.logout:
                startActivity(new Intent(context, MainActivity.class));
                Shareprefrance.clear(context);
                finish();
                return true;
        }

        return true;
    }
}
