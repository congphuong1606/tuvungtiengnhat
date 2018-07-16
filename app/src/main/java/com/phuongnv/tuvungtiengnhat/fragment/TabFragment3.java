package com.phuongnv.tuvungtiengnhat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.adapter.KanjRcvAdapter;
import com.phuongnv.tuvungtiengnhat.data.Kanj;
import com.phuongnv.tuvungtiengnhat.ui.KanjActivity;
import com.phuongnv.tuvungtiengnhat.ui.KanjDetailActivity;


public class TabFragment3 extends Fragment implements KanjRcvAdapter.ItemClickListener {
    private KanjRcvAdapter adapter;
    private RecyclerView rcvViewTab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        rcvViewTab = (RecyclerView) view.findViewById(R.id.rcv_tab3);
        rcvViewTab.setLayoutManager(new GridLayoutManager(this.getContext(), 5));
        adapter = new KanjRcvAdapter(this.getContext(), KanjActivity.kanjsTab3);
        adapter.setClickListener(this);
        rcvViewTab.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(View view, Kanj kanj) {
        Intent intent=new Intent(getActivity(), KanjDetailActivity.class);
        intent.putExtra("kanj", kanj);
        startActivity(intent);
    }
}