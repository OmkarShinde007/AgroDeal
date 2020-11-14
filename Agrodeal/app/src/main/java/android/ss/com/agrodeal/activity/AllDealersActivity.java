package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.ss.com.agrodeal.MainActivity;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.adapter.AuctionAdapter;
import android.ss.com.agrodeal.adapter.DealerAdapter;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.AuctionResponse;
import android.ss.com.agrodeal.retro.DealerResponse;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.ss.com.agrodeal.util.Auction;
import android.ss.com.agrodeal.util.Dealer;
import android.ss.com.agrodeal.util.RecyclerTouchListener;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AllDealersActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<Dealer> dealerList = new ArrayList<>();
    private DealerAdapter dAdapter;
    Context context = AllDealersActivity.this;
    private String TAG = AllDealersActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_dealers);

        recyclerView = findViewById(R.id.recycler_view_dealer);

        dAdapter = new DealerAdapter(dealerList,this);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(dAdapter);

        final ProgressDialog progressDialog=new ProgressDialog(context);

        Retro.getInterface(context).allDealers("test",new Callback<GenralResponse>() {
            @Override
            public void success(GenralResponse generalResponse, Response response) {
                progressDialog.dismiss();

                if (generalResponse.getResult().equalsIgnoreCase("success")) {

                    DealerResponse dealerResponse[] = generalResponse.getDealerJarray();

                    for(int i =0;i<dealerResponse.length;i++)
                    {
                        Dealer dealer = new Dealer(dealerResponse[i].getDealerId(),dealerResponse[i].getName(),dealerResponse[i].getEmail(),dealerResponse[i].getMobile(),dealerResponse[i].getCity(),dealerResponse[i].getRating(),dealerResponse[i].getStatus());
                        dealerList.add(dealer);
                    }

                    dAdapter.notifyDataSetChanged();

                    // finish();
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                   // startActivity(new Intent(context, AddCropActivity.class));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Log.e(TAG, "Error as " + error.getMessage());
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
