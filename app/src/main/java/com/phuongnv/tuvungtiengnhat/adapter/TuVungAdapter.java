package com.phuongnv.tuvungtiengnhat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.data.TuVung;
import com.phuongnv.tuvungtiengnhat.event.CLickTuVungListenner;


import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by Ominext on 9/22/2017.
 */

public class TuVungAdapter extends RecyclerView.Adapter<TuVungAdapter.ViewHolder> {
    CLickTuVungListenner mListener;
    ArrayList<TuVung> tuvungs;


    public TuVungAdapter(ArrayList<TuVung> lessons, CLickTuVungListenner mListener) {
        this.tuvungs = lessons;
        this.mListener = mListener;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tuvung, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TuVung tuvung = tuvungs.get(position);
        String index = String.valueOf(position + 1);
        holder.tvTuVung.setText(tuvung.getTuvung());
        holder.tvKanj.setText(tuvung.getKanj());
        holder.tvNghia.setText(tuvung.getNghia());
        holder.tv_romaji.setText("(" + tuvung.getRomaji() + ")");
        holder.tvSTT.setText(index);

    }

    @Override
    public int getItemCount() {
        return tuvungs.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSTT;
        TextView tvTuVung;
        TextView tvKanj;
        TextView tv_romaji;
        TextView tvNghia;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSTT = (TextView) itemView.findViewById(R.id.tv_stt);
            tvTuVung = (TextView) itemView.findViewById(R.id.tv_tuvung);
            tvKanj = (TextView) itemView.findViewById(R.id.tv_kanj);
            tvNghia = (TextView) itemView.findViewById(R.id.tv_nghia);
            tv_romaji = (TextView) itemView.findViewById(R.id.tv_romaji);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(tuvungs.get(getAdapterPosition()));

                }
            });

        }
    }
}