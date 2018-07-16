package com.phuongnv.tuvungtiengnhat.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.adapter.PagerAdapter;
import com.phuongnv.tuvungtiengnhat.data.Kanj;
import com.phuongnv.tuvungtiengnhat.utils.Database;

import java.util.ArrayList;


public class KanjActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private ArrayList<Kanj> words = new ArrayList<>();
    public static ArrayList<Kanj> kanjsTab1 = new ArrayList<>();
    public static ArrayList<Kanj> kanjsTab2 = new ArrayList<>();
    public static ArrayList<Kanj> kanjsTab3 = new ArrayList<>();
    public static int height;
    public static int width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanj);
        readData();
        addData();
        getWidth();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void addData() {
        kanjsTab1.clear();
        kanjsTab2.clear();
        kanjsTab3.clear();
        for (Kanj kanj : words) {
            if (kanj.getId() < 36) {
                kanjsTab1.add(kanj);
            } else if (kanj.getId() >= 36 && kanj.getId() < 71) {
                kanjsTab2.add(kanj);
            } else {
                kanjsTab3.add(kanj);
            }
        }
    }

    private void getWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private void readData() {
        database = Database.initDatabase(this, "kanjn5.db");
        Cursor cursor = database.rawQuery("select * from word", null);
        words.clear();
//        listSeach.clear();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String kanj = cursor.getString(1);
                    String negetive = cursor.getString(2);
                    String soundkun = cursor.getString(3);
                    String vietnam = cursor.getString(4);
                    String meaning = cursor.getString(5);
                    words.add(new Kanj(id, kanj, negetive, soundkun, vietnam, meaning));
                } while (cursor.moveToNext());
            }
        }
        if (cursor != null) {
            cursor.close();
        }

    }
}
