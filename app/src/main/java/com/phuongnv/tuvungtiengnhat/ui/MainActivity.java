package com.phuongnv.tuvungtiengnhat.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.phuongnv.tuvungtiengnhat.R;
import com.phuongnv.tuvungtiengnhat.adapter.BaiAdapter;
import com.phuongnv.tuvungtiengnhat.adapter.MainPagerAdapter;
import com.phuongnv.tuvungtiengnhat.adapter.PagerAdapter;
import com.phuongnv.tuvungtiengnhat.adapter.TuVungAdapter;
import com.phuongnv.tuvungtiengnhat.data.TuVung;
import com.phuongnv.tuvungtiengnhat.event.CLickTuVungListenner;
import com.phuongnv.tuvungtiengnhat.fragment.MainFragment1;
import com.phuongnv.tuvungtiengnhat.utils.Database;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements OnClickListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener {
    public static MainActivity screen;
    RecyclerView rcvBai;
    Button btnSound;
    Button btnCloseBS;
    Button btnSoundBai;
    private TextView tvTenBai;
    private TextView tvTuVung;
    private TextView tvkanj;
    private TextView tvNghia;
    private LinearLayout backdrop;
    private SQLiteDatabase database;
    private BaiAdapter baiAdapter;
    public static int index;
    public static MainActivity mainActivity;
    private BottomSheetBehavior<RelativeLayout> bottomSheetBehavior;
    private RelativeLayout bottomSheetLayout;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private int mediaFileLengthInMilliseconds;
    private final Handler handler = new Handler();
    private SeekBar seekBarProgress;
    private BoomMenuButton bmb;
    private TuVung currentTuVung;
    private TextView tvHanViet;
    private TextView tvRomaji;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = this;
        addTab();
        mediaPlayer = new MediaPlayer();
        mediaPlayer2 = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);

        mainActivity = this;
        initView();

        setData();
        setBotomSheet();
        setOnClick();




    }

    private void addTab() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.main_pager);
        final MainPagerAdapter adapter = new MainPagerAdapter
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

    private void setOnClick() {
        bottomSheetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("kkk", "h");
            }
        });
        backdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("kkk", "h");
            }
        });
        btnCloseBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                try {
//                    mediaPlayer2.reset();
//                    mediaPlayer2.setDataSource(currentTuVung.getSound());
//                    mediaPlayer2.prepare();
//                    mediaPlayer2.start();
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                playSound(currentTuVung.getSound());
//                Toast.makeText(MainActivity.this, "CHỨC NĂNG NÀY ĐANG ĐƯỢC PHÁT TRIỂN", Toast.LENGTH_LONG).show();
            }
        });
        btnSoundBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"CHỨC NĂNG NÀY ĐANG ĐƯỢC PHÁT TRIỂN",Toast.LENGTH_LONG).show();
                playMp3(index);

            }
        });
    }

    private void playMp3(int index) {
        String src = "http://eup.mobi/apps/mina/listen/" + index + " - 1 - Kotoba.mp3";
        try {
            mediaPlayer.setDataSource(src); // setup song from https://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
            mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
        } catch (Exception e) {
            e.printStackTrace();
        }

        mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            seekBarProgress.setVisibility(View.VISIBLE);
            btnSoundBai.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_pause));
//                buttonPlayPause.setImageResource(R.drawable.button_pause);
        } else {
            btnSoundBai.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_play));
            mediaPlayer.pause();
//                buttonPlayPause.setImageResource(R.drawable.button_play);
        }
        primarySeekBarProgressUpdater();
    }

    private void playSound(String src) {
        if (!src.trim().equals("")) {
            src="http://jls.vnjpclub.com/" +src.replace("\\","/");
//            src="http://jls.vnjpclub.com/audio/minna/bai01/anata.mp3";
            if (mediaPlayer2.isPlaying()) {
                mediaPlayer2.stop();
            } else {
                try {
                    mediaPlayer2.reset();
                    mediaPlayer2 = new MediaPlayer();
                    mediaPlayer2.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                        @Override
                        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

                        }
                    });
                    mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.reset();
                        }
                    });
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer2.setDataSource( src);
                    mediaPlayer2.prepareAsync();
                    mediaPlayer2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }


    public void showBTMSHeet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


    }

    private void setBotomSheet() {
        bottomSheetLayout = (RelativeLayout) findViewById(R.id.linear_layout_bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int state) {
                switch (state) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView() {
        rcvBai = (RecyclerView) findViewById(R.id.rcv_bai);
        tvTuVung = (TextView) findViewById(R.id.btn_tv_tuvung);
        tvkanj = (TextView) findViewById(R.id.btn_tv_kanj);
        tvNghia = (TextView) findViewById(R.id.btn_tv_nghia);
        tvHanViet = (TextView) findViewById(R.id.btn_tv_han_viet);
        tvRomaji = (TextView) findViewById(R.id.btn_tv_romaji);
        tvTenBai = (TextView) findViewById(R.id.tv_tenbai);
        backdrop = (LinearLayout) findViewById(R.id.backdrop);
        btnSound = (Button) findViewById(R.id.btn_sound);
        btnCloseBS = (Button) findViewById(R.id.btn_close_btm_sheet);
        btnSoundBai = (Button) findViewById(R.id.btn_sound_bai);
        seekBarProgress = (SeekBar) findViewById(R.id.seekBar);


        seekBarProgress.setMax(99); // It means 100% .0-99
//        seekBarProgress.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
//        seekBarProgress.getThumb().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
//        seekBarProgress.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return false;
//            }
//        });

        bmb = (BoomMenuButton) findViewById(R.id.btn_menu);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder();
            switch (i) {
                case 0:
                    builder.normalImageRes(R.drawable.ic_create_black_24dp)
                            .normalText("Kiểm tra từ vựng")
                            .subNormalText("Luyện nhớ từ vựng");
                    break;
                case 1:
                    builder.normalImageRes(R.drawable.ic_test)
                            .normalText("Kanj N5")
                            .subNormalText("Xem 103 hán tự n5");
                    break;
                case 2:
                    builder.normalImageRes(R.drawable.btn_unclick)
                            .normalText("Đang phát triển")
                            .subNormalText(".........");
                    break;
                case 3:
                    builder.normalImageRes(R.drawable.btn_unclick)
                            .normalText("Đang phát triển")
                            .subNormalText(".........");
                    break;
            }
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    switch (index) {
                        case 0:
                            break;
                        case 2:

                            break;
                        case 1:

                            startActivity(new Intent(MainActivity.this, KanjActivity.class));
                            break;
                        case 3:

                            break;
                    }
                }
            });
            bmb.addBuilder(builder);

        }
    }

    private void setData() {



        rcvBai.setLayoutManager(new
                GridLayoutManager(this, 1));
        rcvBai.setHasFixedSize(true);
        baiAdapter = new BaiAdapter(this);
        rcvBai.setAdapter(baiAdapter);
        rcvBai.smoothScrollToPosition(0);


    }



    public void notifyData(Integer integer) {
        if (index != integer) {
            index = integer;
            if (mediaPlayer.isPlaying()) {
                btnSoundBai.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_play));
                mediaPlayer.stop();
                seekBarProgress.setVisibility(View.GONE);
            }
            tvTenBai.setText("Từ vựng bài " + index);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            MainPagerAdapter.tab1.readData(integer);
        }


    }


    public void onClick(TuVung tuVung) {
        currentTuVung = tuVung;
        tvTuVung.setText(tuVung.getTuvung());
        tvkanj.setText(tuVung.getKanj());
        tvNghia.setText(tuVung.getNghia());
        tvRomaji.setText(tuVung.getRomaji());
        tvHanViet.setText(tuVung.getHanViet());
        showBTMSHeet();
        playSound(tuVung.getSound());


    }


    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
        seekBarProgress.setSecondaryProgress(percent);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
        mediaPlayer.reset();
        seekBarProgress.setVisibility(View.GONE);
        btnSoundBai.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_play));

    }

    @Override
    public void onClick(View view) {

    }


    /**
     * Method which updates the SeekBar primary progress by current song playing position
     */
    private void primarySeekBarProgressUpdater() {
        float value = (float) mediaPlayer.getCurrentPosition() / mediaFileLengthInMilliseconds;
        Log.e("%%%", String.valueOf((int) (value * 100)));
        seekBarProgress.setProgress((int) (value * 100));
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    primarySeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification, 1000);
        }
    }


}
