package android.ss.com.agrodeal.adapter;

import android.content.res.Resources;
import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.activity.AllDealersActivity;
import android.ss.com.agrodeal.activity.ReviewActivity;
import android.ss.com.agrodeal.retro.ReviewResponse;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {


    private List<ReviewResponse> reviewList;
    private String userType;

    public ReviewAdapter(List<ReviewResponse> reviewList, String userType) {
        this.reviewList = reviewList;
        this.userType = userType;
    }

    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list, parent, false);

        return new ReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReviewAdapter.MyViewHolder holder, final int position) {
        ReviewResponse review = reviewList.get(position);

        if (userType.equalsIgnoreCase("farmer"))
        {
            holder.dealerName.setText(review.getDealerName());
            holder.dealerName.setVisibility(View.VISIBLE);

        }
        else
        {
            holder.farmerName.setText(review.getFarmerName());
            holder.farmerName.setVisibility(View.VISIBLE);
        }



        holder.reviewTime.setText(review.getReviewTime());
        holder.reviewRating.setText(holder.reviewRating.getText().toString()+": " + review.getRatings());
        holder.review.setText(review.getReviews());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView farmerName, reviewTime, reviewRating, review,dealerName;

        public MyViewHolder(View view) {

            super(view);
            farmerName = view.findViewById(R.id.farmerName);
            reviewTime = view.findViewById(R.id.reviewTime);
            reviewRating = view.findViewById(R.id.reviewRating);
            review = view.findViewById(R.id.review);
            dealerName = view.findViewById(R.id.dealerName);
        }
    }


}
