package com.example.MyPagerSlidingTabStrip;

import android.content.Context;
import android.util.AttributeSet;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by ymjkMaster on 2015/6/8 0008.
 */
public class MyPagerSlidingTabStrip extends PagerSlidingTabStrip {
    public MyPagerSlidingTabStrip(Context context) {
        super(context);
    }
    public MyPagerSlidingTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyPagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void setBackgroundColor(int color){
        super.setBackgroundColor(color);
    }
}
