package com.phuongnv.tuvungtiengnhat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.data.Kaiwa;
import com.phuongnv.tuvungtiengnhat.data.TuVung;
import com.phuongnv.tuvungtiengnhat.event.CLickTuVungListenner;

import java.util.ArrayList;

/**
 * Created by Ominext on 7/19/2018.
 */

public class KaiwaAdapter extends RecyclerView.Adapter<KaiwaAdapter.ViewHolder> {
    ArrayList<Kaiwa> kaiwas;


    public KaiwaAdapter(ArrayList<Kaiwa> lessons) {
        this.kaiwas = lessons;
    }


    @Override
    public KaiwaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kaiwa, parent, false);
        return new KaiwaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KaiwaAdapter.ViewHolder holder, int position) {
        Kaiwa kaiwa = kaiwas.get(position);
        if (kaiwa.getIsTitle() == 1) {
            holder.tvUserJ.setVisibility(View.GONE);
            holder.tvUserR.setVisibility(View.GONE);
            holder.lv.setVisibility(View.GONE);
            holder.ln.setGravity(Gravity.CENTER);
            holder.tvKJ.setTextSize(25);

        } else {
            holder.tvUserJ.setText(kaiwa.getUserJ());
            holder.tvUserR.setText(kaiwa.getUserR());
        }
        holder.tvKJ.setText(kaiwa.getKaiwaJ());
        holder.tvKR.setText(kaiwa.getKaiwaR());
        holder.tvKV.setText(kaiwa.getKaiwaV());

    }

    @Override
    public int getItemCount() {
        return kaiwas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserJ;
        TextView tvUserR;
        TextView tvKJ;
        TextView tvKR;
        TextView tvKV;
        LinearLayout lv;
        LinearLayout ln;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUserJ = (TextView) itemView.findViewById(R.id.tv_user_japanese);
            tvUserR = (TextView) itemView.findViewById(R.id.tv_user_romaji);
            tvKJ = (TextView) itemView.findViewById(R.id.tv_kaiwa_japanese);
            tvKR = (TextView) itemView.findViewById(R.id.tv_kaiwa_romaji);
            tvKV = (TextView) itemView.findViewById(R.id.tv_kaiwa_vietnammese);
            lv = (LinearLayout) itemView.findViewById(R.id.lv);
            ln = (LinearLayout) itemView.findViewById(R.id.ln);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    mListener.onClick(tuvungs.get(getAdapterPosition()));

                }
            });

        }
    }
}