package android.ss.com.agrodeal.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.activity.AllDealersActivity;
import android.ss.com.agrodeal.activity.AllFarmerActivity;
import android.ss.com.agrodeal.activity.FarmerReviewActivity;
import android.ss.com.agrodeal.activity.ReviewActivity;
import android.ss.com.agrodeal.retro.FarmerResponse;
import android.ss.com.agrodeal.util.Dealer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class FarmerAdapter extends RecyclerView.Adapter<FarmerAdapter.MyViewHolder>{

    private List<FarmerResponse> farmerList;
    AllFarmerActivity obj;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView farmerName, mobile, rating;
        public Button reviewBtn;
        public MyViewHolder(View view) {
            super(view);
            farmerName = (TextView) view.findViewById(R.id.farmerName);
            mobile = (TextView) view.findViewById(R.id.mobile);
            reviewBtn = (Button) view.findViewById(R.id.reviewBtn);
            rating= (TextView) view.findViewById(R.id.rating);
        }
    }


    public FarmerAdapter(List<FarmerResponse> farmerList, AllFarmerActivity obj) {
        this.farmerList = farmerList;
        this.obj = obj;

    }

    @Override
    public FarmerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.allfarmer_list, parent, false);

        return new FarmerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FarmerAdapter.MyViewHolder holder, int position) {
        final FarmerResponse farmer = farmerList.get(position);

        Resources res = obj.getResources();

        holder.farmerName.setText(farmer.getName());
        holder.mobile.setText(farmer.getMobile());
        holder.reviewBtn.setText(String.format(res.getString(R.string.addReview)));
        holder.rating.setText(String.format(res.getString(R.string.rate))+": "+String.valueOf(farmer.getRating())+"%");

        holder.reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(obj, FarmerReviewActivity.class);

                intent.putExtra("farmerId",farmer.getFarmerId());

                obj.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return farmerList.size();
    }
}
