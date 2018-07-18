package com.phuongnv.tuvungtiengnhat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.phuongnv.tuvungtiengnhat.fragment.MainFragment1;
import com.phuongnv.tuvungtiengnhat.fragment.MainFragment2;
import com.phuongnv.tuvungtiengnhat.fragment.TabFragment1;
import com.phuongnv.tuvungtiengnhat.fragment.TabFragment2;
import com.phuongnv.tuvungtiengnhat.fragment.TabFragment3;


/**
 * Created by Ominext on 7/12/2018.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MainPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MainFragment1 tab1 = MainFragment1.newInstance();
                return tab1;
            case 1:
                MainFragment2 tab2 = new MainFragment2();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}