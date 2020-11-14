package android.ss.com.agrodeal.adapter;

import android.content.Context;

import android.ss.com.agrodeal.R;
import android.ss.com.agrodeal.module.AuctionItems;
import android.ss.com.agrodeal.preferance.Shareprefrance;
import android.ss.com.agrodeal.util.SetFonts;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    Context context;
    int type = 0;
    private ArrayList<AuctionItems> arrayList;

    public ChatAdapter(Context context, ArrayList<AuctionItems> arrayList, int typ) {

        this.arrayList = arrayList;
        this.context = context;
        type = typ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list_items, parent, false);
        SetFonts.setFontToAdapter(context, itemView);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final AuctionItems auctionItems = arrayList.get(position);

        if (auctionItems.getDealerName().equalsIgnoreCase(Shareprefrance.getName(context))) {
            holder.card_left.setVisibility(View.GONE);
            holder.card_right.setVisibility(View.VISIBLE);
            holder.title1.setText(auctionItems.getDealerName());
            holder.message1.setText(String.valueOf(auctionItems.getBidAmount()));
            holder.time1.setText(auctionItems.getBidTime());

            if (String.valueOf(auctionItems.getBidAmount()).contains("###")) {
                holder.message1.setVisibility(View.GONE);

            } else {
                holder.message1.setVisibility(View.VISIBLE);
            }

        } else {

            holder.card_left.setVisibility(View.VISIBLE);
            holder.card_right.setVisibility(View.GONE);
            holder.title.setText(auctionItems.getDealerName());
            holder.message.setText(String.valueOf(auctionItems.getBidAmount()));
            holder.time.setText(auctionItems.getBidTime());

            if (String.valueOf(auctionItems.getBidAmount()).contains("###")) {
                holder.message.setVisibility(View.GONE);
            } else {
                holder.message.setVisibility(View.VISIBLE);
            }
        }



    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title1, title, message1, message, time1, time;
        CardView card_left, card_right;
        ImageButton btn_delete, btn_delete1;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            title1 = (TextView) view.findViewById(R.id.title1);
            message = (TextView) view.findViewById(R.id.message);
            message1 = (TextView) view.findViewById(R.id.message1);
            time = (TextView) view.findViewById(R.id.time);
            time1 = (TextView) view.findViewById(R.id.time1);

            card_left = (CardView) view.findViewById(R.id.card_left);
            card_right = (CardView) view.findViewById(R.id.card_right);


        }
    }
}
