package com.example.ymjkmaster.byrbta;

/**
 * Created by ymjkMaster on 2015/5/22 0022.
*/

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private final String[] titles;
    private List<Fragment> mFragments;


    public MyViewPagerAdapter(FragmentManager fm,String[] titles,List<Fragment> mFragments) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.titles = titles;
        this.mFragments = mFragments;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }
    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return mFragments.get(arg0);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // TODO Auto-generated method stub
        return titles[position];
    }
}