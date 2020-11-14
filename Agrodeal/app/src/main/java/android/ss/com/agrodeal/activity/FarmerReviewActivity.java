package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.ss.com.agrodeal.MainActivity;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.adapter.ReviewAdapter;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.DealerResponse;
import android.ss.com.agrodeal.retro.FarmerResponse;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.ss.com.agrodeal.retro.ReviewResponse;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FarmerReviewActivity extends AppCompatActivity {


    EditText review;
    int farmerId = 0;
    EditText rating;
    Context context = FarmerReviewActivity.this;
    Button submit;
    String reviewStr ;
    String ratingStr;
    private RecyclerView recyclerView;
    private ReviewAdapter rAdapter;
    public List<ReviewResponse> reviewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_review);
        review =  findViewById(R.id.review);
        rating =  findViewById(R.id.rating);
        submit = findViewById(R.id.submit);
        Bundle bundle = getIntent().getExtras();
        farmerId = bundle.getInt("farmerId");

        recyclerView = findViewById(R.id.recycler_view_farmer_reviews);

        rAdapter = new ReviewAdapter(reviewList,"farmer");

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(rAdapter);

        final ProgressDialog progressDialog = new ProgressDialog(context);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reviewStr = review.getText().toString();
                ratingStr = rating.getText().toString();

                if (reviewStr.length() > 0 && ratingStr.length() > 0) {

                    Retro.getInterface(context).addReview(String.valueOf(farmerId),Integer.parseInt(Shareprefrance.getUserId(context)), reviewStr, "farmer", ratingStr, new Callback<GenralResponse>() {
                        @Override
                        public void success(GenralResponse generalResponse, Response response) {
                            progressDialog.dismiss();
                            System.out.println(generalResponse.getResult());
                            if (generalResponse.getResult().equals("success")) {

                                Toast.makeText(context, "Review posted", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(context, AllFarmerActivity.class));
                                finish();
                            } else {
                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context, "All Feilds are compulsary", Toast.LENGTH_SHORT).show();

                }
            }
        });

        Retro.getInterface(context).viewFarmer(farmerId,new Callback<FarmerResponse>() {
            @Override
            public void success(FarmerResponse farmerResponse, Response response) {
                progressDialog.dismiss();

                if (farmerResponse.getResult().equalsIgnoreCase("success")) {

                    ReviewResponse reviewResponse[] = farmerResponse.getJarray();

                    for(int i =0;i<reviewResponse.length;i++)
                    {
                        ReviewResponse review= new ReviewResponse(reviewResponse[i].getReviewId(),reviewResponse[i].getRatings(),reviewResponse[i].getReviews(),reviewResponse[i].getReviewTime(),reviewResponse[i].getFarmerName(),reviewResponse[i].getDealerName());
                        reviewList.add(review);
                    }

                    rAdapter.notifyDataSetChanged();

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
