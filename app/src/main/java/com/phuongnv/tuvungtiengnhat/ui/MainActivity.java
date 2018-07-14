package com.phuongnv.tuvungtiengnhat.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.adapter.TuVungAdapter;
import com.phuongnv.tuvungtiengnhat.data.TuVung;
import com.phuongnv.tuvungtiengnhat.event.CLickTuVungListenner;
import com.phuongnv.tuvungtiengnhat.utils.Database;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements CLickTuVungListenner{
    RecyclerView rcvTuVung;
    private SQLiteDatabase database;
    private ArrayList<TuVung> tuVungs = new ArrayList<>();
    private TuVungAdapter tuvungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setData();
    }

    private void initView() {
        rcvTuVung =(RecyclerView) findViewById(R.id.rcv_tuvung);
    }

    private void setData() {
        rcvTuVung.setLayoutManager(new
                GridLayoutManager(this, 1));
        rcvTuVung.setHasFixedSize(true);
        tuvungAdapter = new TuVungAdapter(tuVungs,this);
        rcvTuVung.setAdapter(tuvungAdapter);
        readData(1);

    }


    private void readData(int c) {
        database = Database.initDatabase(this, "tuvungtiengnhat.db");
        Cursor cursor = database.rawQuery("select * from tuvung where bai = " + c, null);
        tuVungs.clear();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {

                    int id = cursor.getInt(0);
                    String tuvung = cursor.getString(1);
                    String kanj = cursor.getString(2);
                    String nghia = cursor.getString(3);
                    int bai = cursor.getInt(4);
                    tuVungs.add(new TuVung(id, tuvung, kanj, nghia, bai));
                } while (cursor.moveToNext());
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        tuvungAdapter.notifyDataSetChanged();
        rcvTuVung.smoothScrollToPosition(0);


    }

    @Override
    public void onClick(String path, String name) {

    }
}
