package android.ss.com.agrodeal.adapter;

import android.content.res.Resources;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.activity.DealerCropsActivity;
import android.ss.com.agrodeal.retro.SoldResponse;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DealerCropAdapter extends RecyclerView.Adapter<DealerCropAdapter.MyViewHolder> {


    private List<SoldResponse> soldList;
    DealerCropsActivity obj;

    public DealerCropAdapter(List<SoldResponse> soldList, DealerCropsActivity obj) {
        this.soldList = soldList;
        this.obj = obj;
    }

    @Override
    public DealerCropAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dealer_crops_list, parent, false);

        return new DealerCropAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DealerCropAdapter.MyViewHolder holder, final int position) {
        SoldResponse sold = soldList.get(position);
        Resources res = obj.getResources();

        holder.farmerNameSold.setText(String.format(res.getString(R.string.farmer))+": "+sold.getFarmerName());
        holder.cropNameSold.setText(String.format(res.getString(R.string.crop))+": " + sold.getCropName());
        holder.soldTime.setText(sold.getSoldTime());
        holder.amount.setText(String.format(res.getString(R.string.winningAmount))+" Rs."+sold.getAmount());
    }

    @Override
    public int getItemCount() {
        return soldList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView farmerNameSold, cropNameSold, soldTime, amount;

        public MyViewHolder(View view) {

            super(view);
            amount = view.findViewById(R.id.amount);
            soldTime = view.findViewById(R.id.soldTime);
            cropNameSold = view.findViewById(R.id.cropNameSold);
            farmerNameSold = view.findViewById(R.id.farmerNameSold);
        }
    }


}
