package android.ss.com.agrodeal.adapter;

import android.content.res.Resources;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.activity.MyAuctionsActivity;
import android.ss.com.agrodeal.util.Auction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AuctionAdapter extends RecyclerView.Adapter<AuctionAdapter.MyViewHolder>{

    private List<Auction> auctionsList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView farmerName, cropName, auctionStatus;

        public MyViewHolder(View view) {
            super(view);
            farmerName = (TextView) view.findViewById(R.id.farmerName);
            cropName = (TextView) view.findViewById(R.id.cropName);
            auctionStatus = (TextView) view.findViewById(R.id.auctionStatus);
        }
    }


    public AuctionAdapter(List<Auction> auctionsList) {
        this.auctionsList = auctionsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.auction_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Auction auction = auctionsList.get(position);
        holder.farmerName.setText(auction.getFarmerName());
        holder.cropName.setText(auction.getCropName());
        holder.auctionStatus.setText(holder.auctionStatus.getText().toString() +": "+auction.getAuctionStatus());
    }

    @Override
    public int getItemCount() {
        return auctionsList.size();
    }
}
