package com.phuongnv.tuvungtiengnhat.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.adapter.BaiAdapter;
import com.phuongnv.tuvungtiengnhat.adapter.TuVungAdapter;
import com.phuongnv.tuvungtiengnhat.data.TuVung;
import com.phuongnv.tuvungtiengnhat.event.CLickTuVungListenner;
import com.phuongnv.tuvungtiengnhat.utils.Database;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements CLickTuVungListenner{
    RecyclerView rcvTuVung;
    RecyclerView rcvBai;
    Button btnSound;
    Button btnCloseBS;
   private TextView tvTuVung;
   private TextView tvkanj;
   private TextView tvNghia;
   private LinearLayout backdrop;
    private SQLiteDatabase database;
    private ArrayList<TuVung> tuVungs = new ArrayList<>();
    private TuVungAdapter tuvungAdapter;
    private BaiAdapter baiAdapter;

    public static int index ;
    public static MainActivity mainActivity;
    private BottomSheetBehavior<RelativeLayout> bottomSheetBehavior;
    private RelativeLayout bottomSheetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity=this;
        initView();
        setData();
        notifyData(1);

        setBotomSHeet();
        setOnClick();


    }

    private void setOnClick() {
        bottomSheetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("kkk","h");
            }
        });
        btnCloseBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    public  void showBTMSHeet(){
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


    }

    private void setBotomSHeet() {
      bottomSheetLayout= (RelativeLayout) findViewById(R.id.linear_layout_bottom_sheet);
       bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
       bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
           @Override
           public void onStateChanged(@NonNull View view, int state) {
               switch (state){
                   case BottomSheetBehavior.STATE_EXPANDED:
                       backdrop.setVisibility(View.VISIBLE);
                       break;
                  default:
                       backdrop.setVisibility(View.GONE);
                       break;
               }
           }

           @Override
           public void onSlide(@NonNull View view, float v) {

           }
       });


    }

    private void initView() {
        rcvTuVung =(RecyclerView) findViewById(R.id.rcv_tuvung);
        rcvBai =(RecyclerView) findViewById(R.id.rcv_bai);
        tvTuVung =(TextView) findViewById(R.id.btn_tv_tuvung);
        tvkanj =(TextView) findViewById(R.id.btn_tv_kanj);
        tvNghia =(TextView) findViewById(R.id.btn_tv_nghia);
        backdrop =(LinearLayout) findViewById(R.id.backdrop);
        btnSound =(Button) findViewById(R.id.btn_sound);
        btnCloseBS =(Button) findViewById(R.id.btn_close_btm_sheet);
    }

    private void setData() {
        rcvTuVung.setLayoutManager(new
                GridLayoutManager(this, 1));
        rcvTuVung.setHasFixedSize(true);
        tuvungAdapter = new TuVungAdapter(tuVungs,this);
        rcvTuVung.setAdapter(tuvungAdapter);


        rcvBai.setLayoutManager(new
                GridLayoutManager(this, 1));
        rcvBai.setHasFixedSize(true);
        baiAdapter = new BaiAdapter(this);
        rcvBai.setAdapter(baiAdapter);
        rcvBai.smoothScrollToPosition(0);


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


    public void notifyData(Integer integer) {
        index=integer;
        readData(index);
    }

    @Override
    public void onClick(TuVung tuVung) {
        tvTuVung.setText(tuVung.getTuvung());
        tvkanj.setText(tuVung.getKanj());
        tvNghia.setText(tuVung.getNghia());
        showBTMSHeet();

    }
}
