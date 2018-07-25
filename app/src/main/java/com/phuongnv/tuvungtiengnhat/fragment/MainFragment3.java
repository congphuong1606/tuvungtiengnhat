package com.phuongnv.tuvungtiengnhat.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.adapter.NguPhapAdapter;
import com.phuongnv.tuvungtiengnhat.data.NguPhap;
import com.phuongnv.tuvungtiengnhat.ui.MainActivity;
import com.phuongnv.tuvungtiengnhat.utils.Database;

import java.util.ArrayList;


public class MainFragment3 extends Fragment   {
    NguPhapAdapter nguphapAdapter;
    ArrayList<NguPhap> nguphaps=new ArrayList<>();
    private RecyclerView rcvNguPhap;
    int index=1;
    private SQLiteDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment3, container, false);
        rcvNguPhap = (RecyclerView) view.findViewById(R.id.mrcv_nguPhap);
        setData();
        return view;
    }

    private void setData() {
        rcvNguPhap.setLayoutManager(new
                GridLayoutManager(getContext(), 1));
        rcvNguPhap.setHasFixedSize(true);
        nguphapAdapter = new NguPhapAdapter(nguphaps);
        rcvNguPhap.setAdapter(nguphapAdapter);
        readData(index);
        rcvNguPhap.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!MainActivity.screen.isRcvBaiVisible){
                    MainActivity.screen.showHideRcvBai();
                }
            }
        });
    }

    public void readData(int index) {
        this.index = index;
        database = Database.initDatabase(MainActivity.screen, "nguphap.db");
        Cursor cursor = database.rawQuery("select * from nguphap where lesson = " + this.index, null);
        nguphaps.clear();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String nguphap = cursor.getString(1);
                    String nghia = cursor.getString(2);
                    String viDu = cursor.getString(3);
                    int bai = cursor.getInt(4);
                    nguphaps.add(new NguPhap(id, nguphap, nghia,viDu, bai));
                } while (cursor.moveToNext());
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        nguphapAdapter.notifyDataSetChanged();
        rcvNguPhap.smoothScrollToPosition(0);
    }

}