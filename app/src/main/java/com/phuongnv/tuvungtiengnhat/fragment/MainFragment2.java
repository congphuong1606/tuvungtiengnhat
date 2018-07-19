package com.phuongnv.tuvungtiengnhat.fragment;

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
import com.phuongnv.tuvungtiengnhat.adapter.KaiwaAdapter;
import com.phuongnv.tuvungtiengnhat.data.Kaiwa;
import com.phuongnv.tuvungtiengnhat.ui.MainActivity;
import com.phuongnv.tuvungtiengnhat.utils.Database;

import java.util.ArrayList;


public class MainFragment2 extends Fragment   {
    KaiwaAdapter kaiwaAdapter;
    ArrayList<Kaiwa> kaiwas=new ArrayList<>();
    private RecyclerView rcvKaiwa;
    int index=1;
    private SQLiteDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment2, container, false);
        rcvKaiwa = (RecyclerView) view.findViewById(R.id.mrcv_kaiwa);
        setData();
        return view;
    }

    private void setData() {
        rcvKaiwa.setLayoutManager(new
                GridLayoutManager(getContext(), 1));
        rcvKaiwa.setHasFixedSize(true);
        kaiwaAdapter = new KaiwaAdapter(kaiwas);
        rcvKaiwa.setAdapter(kaiwaAdapter);
        readData(index);
    }

    public void readData(int index) {
        this.index = index;
        database = Database.initDatabase(MainActivity.screen, "kaiwa.db");
        Cursor cursor = database.rawQuery("select * from kaiwa_mina where lesson = " + this.index, null);
        kaiwas.clear();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    int isTitle = cursor.getInt(1);
                    String uJ = cursor.getString(2);
                    String uR = cursor.getString(3);
                    String kJ = cursor.getString(4);
                    String kR = cursor.getString(5);
                    String kV = cursor.getString(6);
                    int bai = cursor.getInt(7);
                    kaiwas.add(new Kaiwa(id, isTitle, uJ, uR, kJ, kR, kV, bai));
                } while (cursor.moveToNext());
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        kaiwaAdapter.notifyDataSetChanged();
        rcvKaiwa.smoothScrollToPosition(0);
    }

}