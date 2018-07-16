package com.phuongnv.tuvungtiengnhat.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.data.Kanj;
import com.phuongnv.tuvungtiengnhat.ui.KanjActivity;

import java.util.ArrayList;


/**
 * Created by Ominext on 7/12/2018.
 */

public class KanjRcvAdapter extends RecyclerView.Adapter<KanjRcvAdapter.ViewHolder> {

    private ArrayList<Kanj> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public KanjRcvAdapter(Context context, ArrayList<Kanj> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;

    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_kanj, parent, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        lp.height = KanjActivity.width / 5;
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.myTextView.setText(mData.get(position).getKanj());
        holder.tvVietNam.setText(mData.get(position).getVietnam());
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView tvVietNam;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.tv_kanj_text);
            tvVietNam = (TextView) itemView.findViewById(R.id.tv_vietnam);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, mData.get(getAdapterPosition()));
        }
    }


    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, Kanj kanj);
    }
}
