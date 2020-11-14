package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.ss.com.agrodeal.MainActivity;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.adapter.DealerAdapter;
import android.ss.com.agrodeal.adapter.FarmerAdapter;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.DealerResponse;
import android.ss.com.agrodeal.retro.FarmerResponse;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.ss.com.agrodeal.util.Dealer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AllFarmerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<FarmerResponse> farmerList = new ArrayList<>();
    private FarmerAdapter fAdapter;
    Context context = AllFarmerActivity.this;
    private String TAG = AllFarmerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_farmer);

        recyclerView = findViewById(R.id.recycler_view_allfarmers);

        fAdapter = new FarmerAdapter(farmerList,this);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(fAdapter);

        final ProgressDialog progressDialog=new ProgressDialog(context);

        Retro.getInterface(context).allFarmers("test",new Callback<GenralResponse>() {
            @Override
            public void success(GenralResponse generalResponse, Response response) {
                progressDialog.dismiss();

                if (generalResponse.getResult().equalsIgnoreCase("success")) {

                    FarmerResponse farmerResponse[] = generalResponse.getFarmerJarray();

                    for(int i =0;i<farmerResponse.length;i++)
                    {
                        FarmerResponse farmer = new FarmerResponse(farmerResponse[i].getFarmerId(),farmerResponse[i].getName(),farmerResponse[i].getMobile(),farmerResponse[i].getCity(),farmerResponse[i].getRating());
                        farmerList.add(farmer);
                    }

                    fAdapter.notifyDataSetChanged();

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
