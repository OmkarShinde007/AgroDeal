package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.ss.com.agrodeal.MainActivity;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class AddCropActivity extends AppCompatActivity {

    EditText cropName;
    EditText cropRate;
    EditText cropDetail;
    Button btnSubmit;
    Context context = AddCropActivity.this;
    private String TAG = AddCropActivity.class.getSimpleName();
    Spinner spinner;
    String quality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crop);

        cropName = (EditText)findViewById(R.id.cropName);
        cropRate = (EditText)findViewById(R.id.cropRate);
        cropDetail = (EditText)findViewById(R.id.cropDetail);
        btnSubmit = (Button)findViewById(R.id.submit);
        spinner = (Spinner) findViewById(R.id.cropQuality);

        String[] paths = {"High","Medium", "Low"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCropActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                quality = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String cropNameStr = cropName.getText().toString();
                String cropRateStr = cropRate.getText().toString();
                String cropDetailStr = cropDetail.getText().toString();

                submitCrop(cropNameStr,cropRateStr,quality,cropDetailStr);
            }
        });
    }

    public void submitCrop(String cropNameStr,String cropRateStr, String quality,String cropDetailStr)
    {
        String userId = Shareprefrance.getUserId(context);
        final ProgressDialog progressDialog=new ProgressDialog(context);

        Retro.getInterface(context).addCrop(cropNameStr, cropRateStr,quality,cropDetailStr,userId,new Callback<GenralResponse>() {
            @Override
            public void success(GenralResponse generalResponse, Response response) {
                progressDialog.dismiss();
                System.out.println(generalResponse.getResult());
                if (generalResponse.getResult().equals("success")) {

                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(context, AddCropActivity.class));
                     finish();
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(context, AddCropActivity.class));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Log.e(TAG, " Error as " + error.getMessage());
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
