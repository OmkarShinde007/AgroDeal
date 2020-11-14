package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.ss.com.agrodeal.MainActivity;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.adapter.DealerCropAdapter;
import android.ss.com.agrodeal.adapter.SoldAdapter;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.ss.com.agrodeal.retro.SoldResponse;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DealerCropsActivity extends AppCompatActivity {


    Context context = DealerCropsActivity.this;
    private RecyclerView recyclerView;
    private DealerCropAdapter dAdapter;
    public List<SoldResponse> soldList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_crops);

        recyclerView = findViewById(R.id.recycler_dealerCrops);

        dAdapter = new DealerCropAdapter(soldList,this);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(dAdapter);

        int dealerId = Integer.parseInt(Shareprefrance.getUserId(context));
        final ProgressDialog progressDialog = new ProgressDialog(context);

        Retro.getInterface(context).wonnedCrops(dealerId,new Callback<GenralResponse>() {
            @Override
            public void success(GenralResponse genralResponse, Response response) {
                progressDialog.dismiss();

                if (genralResponse.getResult().equalsIgnoreCase("success")) {

                    SoldResponse soldResponse[] = genralResponse.getSoldJarray();

                    if(soldResponse!=null)
                    {
                        for(int i =0;i<soldResponse.length;i++)
                        {
                            SoldResponse sold= new SoldResponse(soldResponse[i].getName(),soldResponse[i].getCropName(),soldResponse[i].getAmount(),soldResponse[i].getSoldTime(),soldResponse[i].getFarmerName());
                            soldList.add(sold);
                        }

                        dAdapter.notifyDataSetChanged();

                    }
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
