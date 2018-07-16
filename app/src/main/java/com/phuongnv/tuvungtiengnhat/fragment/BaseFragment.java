package com.phuongnv.tuvungtiengnhat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.adapter.KanjRcvAdapter;
import com.phuongnv.tuvungtiengnhat.ui.KanjActivity;


/**
 * Created by Ominext on 7/12/2018.
 */

public class BaseFragment extends Fragment {
    RecyclerView rcvView;
    KanjRcvAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        rcvView = (RecyclerView) view.findViewById(R.id.rcv_tab1);
        rcvView.setLayoutManager(new GridLayoutManager(this.getContext(), 5));
        adapter = new KanjRcvAdapter(this.getContext(), KanjActivity.kanjsTab1);
        rcvView.setAdapter(adapter);
        return view;
    }
}
