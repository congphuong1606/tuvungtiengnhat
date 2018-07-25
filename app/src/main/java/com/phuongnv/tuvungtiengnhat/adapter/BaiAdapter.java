package com.phuongnv.tuvungtiengnhat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.data.TuVung;
import com.phuongnv.tuvungtiengnhat.event.CLickTuVungListenner;
import com.phuongnv.tuvungtiengnhat.ui.MainActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Ominext on 9/22/2017.
 */

public class BaiAdapter extends RecyclerView.Adapter<BaiAdapter.ViewHolder> {
    public  static ArrayList<Integer> bais=new ArrayList<>();
    Context context;
    int flag = 1;


    public BaiAdapter(Context context) {
        for (int i = 1; i <= 50; i++) {
            this.bais.add(i);
        }
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int number = bais.get(position);
        if (number == 1) {
            holder.vTop.setVisibility(View.GONE);
            holder.lnfacke.setVisibility(View.VISIBLE);

        }
        if (number == 50) {
            holder.vBtm.setVisibility(View.GONE);
        }
        holder.tvNumber.setText(String.valueOf(number));
        if (number == MainActivity.index) {
            holder.cimvBai.setImageDrawable(context.getResources().getDrawable(R.drawable.clicked));
            holder.tvNumber.setTextColor(context.getResources().getColor(R.color.gray));
        } else {
            holder.tvNumber.setTextColor(context.getResources().getColor(R.color.white));
            holder.cimvBai.setImageDrawable(context.getResources().getDrawable(R.drawable.click));
        }


    }

    @Override
    public int getItemCount() {
        return bais.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View vTop;
        View vBtm;
        TextView tvNumber;
        CircleImageView cimvBai;
        LinearLayout lnfacke;

        public ViewHolder(View itemView) {
            super(itemView);
            vTop = (View) itemView.findViewById(R.id.v_top);
            vBtm = (View) itemView.findViewById(R.id.v_btm);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
            cimvBai = (CircleImageView) itemView.findViewById(R.id.cimv_bai);
            lnfacke = (LinearLayout) itemView.findViewById(R.id.lnfacke);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.mainActivity.notifyData(bais.get(getAdapterPosition()-1));

//                    mListener.onClick(tuvungs.get(getAdapterPosition()));
                }
            });

        }
    }
}