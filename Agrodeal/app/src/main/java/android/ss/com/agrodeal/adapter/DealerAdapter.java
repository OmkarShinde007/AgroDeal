package android.ss.com.agrodeal.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.activity.AllDealersActivity;
import android.ss.com.agrodeal.activity.ReviewActivity;
import android.ss.com.agrodeal.util.Dealer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DealerAdapter extends RecyclerView.Adapter<DealerAdapter.MyViewHolder>{

    private List<Dealer> dealerList;
    AllDealersActivity obj;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dealerName, mobile,rating;
        public Button reviewBtn;
        public MyViewHolder(View view) {
            super(view);
            dealerName = (TextView) view.findViewById(R.id.dealerName);
            mobile = (TextView) view.findViewById(R.id.mobile);
            rating  = (TextView) view.findViewById(R.id.rating);
            reviewBtn = (Button) view.findViewById(R.id.reviewBtn);
        }
    }


    public DealerAdapter(List<Dealer> dealerList, AllDealersActivity obj) {
        this.dealerList = dealerList;
        this.obj = obj;

    }

    @Override
    public DealerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dealer_list_row, parent, false);

        return new DealerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DealerAdapter.MyViewHolder holder, int position) {
        final Dealer dealer = dealerList.get(position);
        Resources res = obj.getResources();

        holder.dealerName.setText(dealer.getName());
        holder.mobile.setText(dealer.getMobile());
        holder.reviewBtn.setText(String.format(res.getString(R.string.addReview)));
        holder.rating.setText(String.format(res.getString(R.string.rate))+": "+String.valueOf(dealer.getRating())+"%");

        holder.reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(obj, ReviewActivity.class);

                intent.putExtra("dealerId",dealer.getDealerId());

                obj.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dealerList.size();
    }
}
