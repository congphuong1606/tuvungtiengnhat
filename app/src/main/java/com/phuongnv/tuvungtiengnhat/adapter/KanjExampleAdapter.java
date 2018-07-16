package com.phuongnv.tuvungtiengnhat.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.data.KanjExample;
import com.phuongnv.tuvungtiengnhat.ui.KanjActivity;

import java.util.ArrayList;


/**
 * Created by Ominext on 7/12/2018.
 */

public class KanjExampleAdapter extends RecyclerView.Adapter<KanjExampleAdapter.ViewHolder> {

    private ArrayList<KanjExample> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private KanjRcvAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public KanjExampleAdapter(Context context, ArrayList<KanjExample> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;

    }

    // inflates the cell layout from xml when needed
    @Override
    public KanjExampleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_example, parent, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        lp.height = KanjActivity.width / 5;
        view.setLayoutParams(lp);
        return new KanjExampleAdapter.ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(KanjExampleAdapter.ViewHolder holder, int position) {
        holder.tvWord.setText(mData.get(position).getWord());
        holder.tvHanViet.setText(mData.get(position).getHanViet());
        holder.tvHiragana.setText(mData.get(position).getHiragana());
        holder.tvMeaning.setText(mData.get(position).getMeaning());
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord;
        TextView tvHanViet;
        TextView tvHiragana;
        TextView tvMeaning;

        ViewHolder(View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.tv_example_word);
            tvHanViet = (TextView) itemView.findViewById(R.id.tv_example_hanviet);
            tvHiragana = (TextView) itemView.findViewById(R.id.tv_example_hira);
            tvMeaning = (TextView) itemView.findViewById(R.id.tv_example_mean);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null)
//                mClickListener.onItemClick(view, mData.get(getAdapterPosition()));
//        }
    }


//    // allows clicks events to be caught
//    public void setClickListener(KanjRcvAdapter.ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ClickListener {
//        void onItemClick(View view, KanjExample kanj);
//    }
}