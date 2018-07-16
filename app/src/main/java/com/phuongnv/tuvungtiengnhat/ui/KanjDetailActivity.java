package com.phuongnv.tuvungtiengnhat.ui;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;


import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.github.badoualy.kanjistroke.KanjiStrokeView;
import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.adapter.KanjExampleAdapter;
import com.phuongnv.tuvungtiengnhat.data.Kanj;
import com.phuongnv.tuvungtiengnhat.data.KanjExample;
import com.phuongnv.tuvungtiengnhat.utils.Database;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



public class KanjDetailActivity extends AppCompatActivity {

    private ImageView imvKanj;
    private TextView tvVietNam;
    private RecyclerView rcvKanjExample;
    private KanjExampleAdapter adapter;
    ArrayList<KanjExample> kanjExamples = new ArrayList<>();
    private SQLiteDatabase database;
    private Kanj kanj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
        setContentView(R.layout.activity_kanj_detail);
        Intent i = getIntent();
        kanj = (Kanj) i.getSerializableExtra("kanj");
        imvKanj = (ImageView) findViewById(R.id.image_kanj_detail);
        tvVietNam = (TextView) findViewById(R.id.tv_vietnam_detail);
        tvVietNam.setText(kanj.getVietnam());

        getListExample();
        setExValue();

        strokeView();
    }

    private void strokeView() {
        final KanjiStrokeView strokeView = findViewById(R.id.view_stroke);
        strokeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strokeView.startDrawAnimation();
            }
        });


        AssetManager assetManager = getAssets();
        try {
            InputStream is = assetManager.open("svg/migi.svg");
            strokeView.loadSvg(is);
            getWH(strokeView);
            loadSVG(is);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getWH(final KanjiStrokeView strokeView) {
        strokeView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = strokeView.getMeasuredWidth();
                int height = strokeView.getMeasuredHeight();

            }
        });
    }

    private void loadSVG(InputStream is) {
        AssetManager assetManager = getAssets();SVG svg = null;

        try {
            InputStream iss = assetManager.open("svg/migi-full.svg");
            svg = SVG.getFromInputStream(iss);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
        if (svg.getDocumentWidth() != -1) {
            Bitmap newBM = Bitmap.createBitmap(231,
                    231,
                    Bitmap.Config.ARGB_8888);
            Canvas bmcanvas = new Canvas(newBM);
            svg.renderToCanvas(bmcanvas);
            imvKanj.setImageBitmap(newBM);
        }

    }

    private void getListExample() {
        kanjExamples.clear();
        database = Database.initDatabase(this, "kanjn5.db");
        Cursor cursor = database.rawQuery("select * from example where kanj_id = " + kanj.getId(), null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {

                    int id = cursor.getInt(0);
                    String word = cursor.getString(1);
                    String hiragana = cursor.getString(2);
                    String hanViet = cursor.getString(3);
                    String meaning = cursor.getString(4);
                    String kanjId = cursor.getString(5);
                    kanjExamples.add(new KanjExample(id, word, hiragana, hanViet, meaning, kanjId));
                } while (cursor.moveToNext());
            }
        }
        if (cursor != null) {
            cursor.close();
        }

    }

    private void setExValue() {
        rcvKanjExample = (RecyclerView) findViewById(R.id.rcv_example);
        rcvKanjExample.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new KanjExampleAdapter(this, kanjExamples);
        rcvKanjExample.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_dow);
    }
}
