package com.phuongnv.tuvungtiengnhat.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.adapter.KanjRcvAdapter;
import com.phuongnv.tuvungtiengnhat.adapter.TuVungAdapter;
import com.phuongnv.tuvungtiengnhat.data.Kanj;
import com.phuongnv.tuvungtiengnhat.data.TuVung;
import com.phuongnv.tuvungtiengnhat.event.CLickTuVungListenner;
import com.phuongnv.tuvungtiengnhat.ui.KanjActivity;
import com.phuongnv.tuvungtiengnhat.ui.KanjDetailActivity;
import com.phuongnv.tuvungtiengnhat.ui.MainActivity;
import com.phuongnv.tuvungtiengnhat.utils.Database;

import java.util.ArrayList;

public class MainFragment1 extends Fragment implements CLickTuVungListenner {


    private RecyclerView rcvTuVung;
    private TuVungAdapter tuvungAdapter;
    private ArrayList<TuVung> tuVungs = new ArrayList<>();
    int index = 1;
    private SQLiteDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment1, container, false);
        rcvTuVung = (RecyclerView) view.findViewById(R.id.mrcv_tuvung);
        setData();
        return view;
    }

    private void setData() {
        rcvTuVung.setLayoutManager(new
                GridLayoutManager(getContext(), 1));
        rcvTuVung.setHasFixedSize(true);
        tuvungAdapter = new TuVungAdapter(tuVungs, this);
        rcvTuVung.setAdapter(tuvungAdapter);
        readData(index);
    }

    @Override
    public void onClick(TuVung tuVung) {
        MainActivity.screen.onClick(tuVung);
    }

    public void readData(int c) {
        index = c;
        database = Database.initDatabase(MainActivity.screen, "minanonihongo.db");
        Cursor cursor = database.rawQuery("select * from tuvung_mina where lesson = " + c, null);
        tuVungs.clear();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String tuvung = cursor.getString(1);
                    String romaji = cursor.getString(2);
                    String sound = cursor.getString(3);
                    String kanj = cursor.getString(4);
                    String hanViet = cursor.getString(5);
                    String nghia = cursor.getString(6);
                    int bai = cursor.getInt(7);
                    tuVungs.add(new TuVung(id, tuvung, romaji, sound, kanj, hanViet, nghia, bai));
                } while (cursor.moveToNext());
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        tuvungAdapter.notifyDataSetChanged();
        rcvTuVung.smoothScrollToPosition(0);
    }



}