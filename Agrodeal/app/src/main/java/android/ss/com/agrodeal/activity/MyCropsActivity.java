package android.ss.com.agrodeal.activity;

import android.content.Context;
import android.content.Intent;
import android.ss.com.agrodeal.MainActivity;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.CropsResponse;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyCropsActivity extends AppCompatActivity {

    ArrayList<String> allCrops;
    Context context = MyCropsActivity.this;
    ListView allMyCrops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_crops);
        allCrops = new ArrayList<String>();
        allMyCrops = (ListView)findViewById(R.id.allMyCrops);

        Retro.getInterface(context).myCrops(Shareprefrance.getUserId(context),new Callback<GenralResponse>() {

            @Override
            public void success(GenralResponse generalResponse, Response response) {

                // Toast.makeText(context, generalResponse.getResult(), Toast.LENGTH_SHORT).show();
                if (generalResponse.getResult().equals("success")) {

                    CropsResponse cropsResponse[] = generalResponse.getJarray();

                    for(int i=0;i<cropsResponse.length;i++)
                    {
                        allCrops.add(cropsResponse[i].getCropName()+" with "+cropsResponse[i].getCropQuality()+" quality");
                        System.out.println("Crop name is "+cropsResponse[i].getCropName());
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1, allCrops);

                    allMyCrops.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("MyCropsActivity", " Error as " + error.getMessage());
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
