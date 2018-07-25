package com.phuongnv.tuvungtiengnhat.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.data.NguPhap;

import java.util.ArrayList;

/**
 * Created by Ominext on 7/19/2018.
 */

public class NguPhapAdapter extends RecyclerView.Adapter<NguPhapAdapter.ViewHolder> {
    ArrayList<NguPhap> nguPhaps;


    public NguPhapAdapter(ArrayList<NguPhap> lessons) {
        this.nguPhaps = lessons;
    }


    @Override
    public NguPhapAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngu_phap, parent, false);
        return new NguPhapAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NguPhapAdapter.ViewHolder holder, int position) {
        NguPhap nguphap = nguPhaps.get(position);
        holder.tvNguPhap.setText(nguphap.getNguPhap());
        holder.tvNghia.setText(nguphap.getNghia());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.tvNguPhap.setText(Html.fromHtml(nguphap.getNguPhap(), Html.FROM_HTML_MODE_LEGACY));
            holder.tvNghia.setText(Html.fromHtml(nguphap.getNghia(), Html.FROM_HTML_MODE_LEGACY));
            holder.tvViDu.setText(Html.fromHtml(nguphap.getViDu(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.tvNguPhap.setText(Html.fromHtml(nguphap.getNguPhap()));
            holder.tvNghia.setText(Html.fromHtml(nguphap.getNghia()));
            holder.tvViDu.setText(Html.fromHtml(nguphap.getViDu()));
        }


    }

    @Override
    public int getItemCount() {
        return nguPhaps.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNguPhap;
        TextView tvNghia;
        TextView tvViDu;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNguPhap = (TextView) itemView.findViewById(R.id.tv_nguphap);
            tvNghia = (TextView) itemView.findViewById(R.id.tv_nghia);
            tvViDu = (TextView) itemView.findViewById(R.id.tv_vidu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    mListener.onClick(tuvungs.get(getAdapterPosition()));

                }
            });

        }
    }
}