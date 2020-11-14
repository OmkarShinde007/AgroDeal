package android.ss.com.agrodeal.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.adapter.ChatAdapter;
import android.ss.com.agrodeal.module.AuctionItems;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.retro.AuctionResponse;
import android.ss.com.agrodeal.retro.BiddingResponse;
import android.ss.com.agrodeal.retro.GenralResponse;
import android.ss.com.agrodeal.retro.Retro;
import android.ss.com.agrodeal.util.SetFonts;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;
import retrofit.RetrofitError;

public class ChatActivity extends AppCompatActivity {

    Context context = ChatActivity.this;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    //String groupid = "";
    ArrayList<AuctionItems> array_auction_msg = new ArrayList<>();
    //    @BindView(R.id.recycler_city)
    RecyclerView recycler_city;
    //  @BindView(R.id.edt_msg)
    EditText edt_msg;
    //@BindView(R.id.imgbtn_send)
    ImageButton imgbtn_send;
    ImageButton imgbtn_file;
    ChatAdapter chatAdapter;
    Handler handler = new Handler();
    int auctionId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        recycler_city = findViewById(R.id.recycler_city);
        edt_msg = findViewById(R.id.edt_msg);


        imgbtn_send = findViewById(R.id.imgbtn_send);
        imgbtn_file = findViewById(R.id.imgbtn_file);

        imgbtn_file.setVisibility(View.GONE);
        Resources res = getResources();

        if(Shareprefrance.getUserType(context).equals("broker"))
        {

            edt_msg.setText(String.format(res.getString(R.string.canView)));
            edt_msg.setEnabled(false);

            imgbtn_send.setVisibility(View.GONE);
        }

        Bundle bundle = getIntent().getExtras();
        auctionId = bundle.getInt("auctionId");

        SetFonts.setFontToActivity(ChatActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        toolbar.setTitle(bundle.getString("cropName")+String.format(res.getString(R.string.startsFrom))+bundle.getFloat("cropRate"));
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //startService(new Intent(this, SetUpView.class));

//        startTimer();

    }

    public void setUpView() {


        chatAdapter = new ChatAdapter(context, array_auction_msg, 1);
        recycler_city.setLayoutManager(new LinearLayoutManager(context));
        recycler_city.setHasFixedSize(true);
        recycler_city.setAdapter(chatAdapter);
        recycler_city.scrollToPosition(array_auction_msg.size() - 1);

        imgbtn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg = edt_msg.getText().toString().trim();
                sendMessage(msg);


            }
        });


        edt_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String text = charSequence.toString().trim();
                if (text.length() > 0) {
                    imgbtn_send.setVisibility(View.VISIBLE);
                } else {
                    imgbtn_send.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(final String msg) {

        progressDialog = ProgressDialog.show(context, null, "Please wait...", false, false);
        progressDialog.setCancelable(false);
        Retro.getInterface(context).doBidding(Shareprefrance.getUserId(context), auctionId, msg, new Callback<GenralResponse>() {

            @Override
            public void success(GenralResponse genralResponse, retrofit.client.Response response) {
                progressDialog.dismiss();

                if (genralResponse.getResult().equalsIgnoreCase("success")) {
                    Toast.makeText(context, genralResponse.getResult(), Toast.LENGTH_SHORT).show();
                    edt_msg.setText("");

                } else {
                    Toast.makeText(context, genralResponse.getResult(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong, try again...", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void getData() {


        Retro.getInterface(context).getBidding(auctionId, new Callback<AuctionResponse>() {

            @Override
            public void success(AuctionResponse auctionResponse, retrofit.client.Response response) {


                if (auctionResponse.getResult().equalsIgnoreCase("success")) {

                    if(auctionResponse.getAuctionStatus().equalsIgnoreCase("yes"))
                    {
                        BiddingResponse biddingJarray[] = auctionResponse.getJarray();

                        array_auction_msg.clear();
                        for (int i = 0; i < biddingJarray.length; i++) {
                            AuctionItems auctionItems = new AuctionItems(auctionId, biddingJarray[i].getBiddingId(), biddingJarray[i].getDealerId(), biddingJarray[i].getBidTime(), biddingJarray[i].getBidAmount(), auctionResponse.getFarmerId(), biddingJarray[i].getDealerName(), auctionResponse.getFarmerName(), auctionResponse.getCropName());
                            array_auction_msg.add(auctionItems);
                        }
                    }
                    else
                    {
                        Resources res = getResources();

                        edt_msg.setText(String.format(res.getString(R.string.cropSold)));
                        edt_msg.setEnabled(false);
                        imgbtn_send.setVisibility(View.GONE);

                    }
                    if(Shareprefrance.getUserType(context).equals("broker"))
                    {
                        Resources res = getResources();

                        edt_msg.setText(String.format(res.getString(R.string.canView)));
                        edt_msg.setEnabled(false);

                        imgbtn_send.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(context, "Bid failed, try again", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(context, "Bid failed, try again...", Toast.LENGTH_LONG).show();
            }

        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        stoptimertask();

        handler.removeCallbacksAndMessages(null);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    private Timer timer;
    private TimerTask timerTask;

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 10 minutus
        timer.schedule(timerTask, 100, 1000); //

    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        setUpView();
                        getData();
                    }
                });
            }
        };
    }



    public void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


}
