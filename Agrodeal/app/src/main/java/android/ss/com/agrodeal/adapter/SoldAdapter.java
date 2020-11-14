package android.ss.com.agrodeal.adapter;

import android.content.res.Resources;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.activity.SoldActivity;
import android.ss.com.agrodeal.retro.ReviewResponse;
import android.ss.com.agrodeal.retro.SoldResponse;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SoldAdapter extends RecyclerView.Adapter<SoldAdapter.MyViewHolder> {


    private List<SoldResponse> soldList;
    SoldActivity obj;

    public SoldAdapter(List<SoldResponse> soldList, SoldActivity obj) {
        this.soldList = soldList;
        this.obj = obj;
    }

    @Override
    public SoldAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sold_list, parent, false);

        return new SoldAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SoldAdapter.MyViewHolder holder, final int position) {
        SoldResponse sold = soldList.get(position);
        Resources res = obj.getResources();

        holder.dealerNameSold.setText(String.format(res.getString(R.string.dealer))+": "+sold.getName());
        holder.cropNameSold.setText(String.format(res.getString(R.string.crop))+": " + sold.getCropName());
        holder.soldTime.setText(sold.getSoldTime());
        holder.amount.setText(String.format(res.getString(R.string.winningAmount))+" Rs."+sold.getAmount());
    }

    @Override
    public int getItemCount() {
        return soldList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dealerNameSold, cropNameSold, soldTime, amount;

        public MyViewHolder(View view) {

            super(view);
            amount = view.findViewById(R.id.amount);
            soldTime = view.findViewById(R.id.soldTime);
            cropNameSold = view.findViewById(R.id.cropNameSold);
            dealerNameSold = view.findViewById(R.id.dealerNameSold);
        }
    }


}
