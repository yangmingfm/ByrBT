package com.example.ymjkmaster.byrbta;

/**
 * Created by ymjkMaster on 2015/5/22 0022.
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.Toolbar;
import android.widget.ScrollView;

import com.astuetz.PagerSlidingTabStrip;

public class MyPageChangeListener implements OnPageChangeListener {

    private Toolbar toolbar;
    private PagerSlidingTabStrip mTabStrip;
    private ScrollView mScrollView;
    public MyPageChangeListener(PagerSlidingTabStrip mTabStrip,Toolbar toolbar,ScrollView mScrollView){
        this.mScrollView = mScrollView;
        this.mTabStrip = mTabStrip;
        this.toolbar = toolbar;
    }
    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        switch(position)
        {
            case 0:
                ObjectAnimator.ofInt(mScrollView, "translationY", 0, -300).setDuration(500).start();
                mTabStrip.setBackgroundColor(0xff009688);
                toolbar.setBackgroundColor(0xff009688);
                ObjectAnimator.ofInt(mScrollView, "translationY", -300, 0).setDuration(500).setStartDelay(500);
                break;
            case 1:
                ObjectAnimator.ofInt(mScrollView, "translationY", 0, -300).setDuration(500).start();
                mTabStrip.setBackgroundColor(0xffff6d00);
                toolbar.setBackgroundColor(0xffff6d00);
                ObjectAnimator.ofInt(mScrollView, "translationY", -300, 0).setDuration(500).setStartDelay(500);
                AnimatorSet animator = new AnimatorSet();
                break;
            case 2:
                ObjectAnimator.ofFloat(mScrollView,"translationY",0,-300).setDuration(500).start();
                mTabStrip.setBackgroundColor(0xff1e88e5);
                toolbar.setBackgroundColor(0xff1e88e5);
                ObjectAnimator.ofInt(mScrollView, "translationY", -300, 0).setDuration(500).setStartDelay(500);
                break;
            case 3:
                ObjectAnimator.ofFloat(mScrollView,"translationY",0,-300).setDuration(300).start();
                mTabStrip.setBackgroundColor(0xff4e342e);
                toolbar.setBackgroundColor(0xff4e342e);
                ObjectAnimator.ofInt(mScrollView, "translationY", -300, 0).setDuration(500).setStartDelay(500);
                break;
            case 4:
                ObjectAnimator.ofFloat(mScrollView,"translationY",0,-300).setDuration(500).start();
                mTabStrip.setBackgroundColor(0xff673ab7);
                toolbar.setBackgroundColor(0xff673ab7);
                ObjectAnimator.ofInt(mScrollView, "translationY", -300, 0).setDuration(500).setStartDelay(500);
                break;
            default:
        }
    }
    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }
}