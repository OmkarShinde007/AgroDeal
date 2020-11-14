package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.ss.com.agrodeal.MainActivity;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.adapter.AuctionAdapter;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.AuctionResponse;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.ss.com.agrodeal.util.Auction;
import android.ss.com.agrodeal.util.RecyclerTouchListener;
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
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyAuctionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Auction> auctionList = new ArrayList<>();
    private AuctionAdapter aAdapter;
    Context context = MyAuctionsActivity.this;
    private String TAG = MyAuctionsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_auctions);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        aAdapter = new AuctionAdapter(auctionList);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(aAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Auction auction = auctionList.get(position);
                Toast.makeText(getApplicationContext(), auction.getCropName() + " is selected!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,ViewBiddingActivity.class);

                intent.putExtra("auctionId",auction.getAuctionId());
                intent.putExtra("cropName",auction.getCropName());
                intent.putExtra("cropRate",auction.getCropRate());

                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        final ProgressDialog progressDialog=new ProgressDialog(context);

        Retro.getInterface(context).allAuctions("test",new Callback<GenralResponse>() {
            @Override
            public void success(GenralResponse generalResponse, Response response) {
                progressDialog.dismiss();
                System.out.println("Inside "+generalResponse.getAuctionResult());
                if (generalResponse.getAuctionResult().equalsIgnoreCase("success")) {

                    AuctionResponse auctionResponse[] = generalResponse.getAuctionJarray();


                    for(int i =0;i<auctionResponse.length;i++)
                    {
                        if(String.valueOf(auctionResponse[i].getFarmerId()).equals( Shareprefrance.getUserId(context)) )
                        {
                            Auction auction = new Auction(auctionResponse[i].getAuctionId(),auctionResponse[i].getCropId(),auctionResponse[i].getCreationTime(),auctionResponse[i].getCropName(),auctionResponse[i].getCropQuality(),auctionResponse[i].getCropDetail(),auctionResponse[i].getCropRate(),auctionResponse[i].getFarmerId(),auctionResponse[i].getFarmerName(),auctionResponse[i].getAuctionStatus());
                            auctionList.add(auction);
                        }
                    }

                    aAdapter.notifyDataSetChanged();

                    // finish();
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

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
