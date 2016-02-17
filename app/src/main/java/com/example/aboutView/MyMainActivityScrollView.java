package com.example.aboutView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by ymjkMaster on 2015/6/10 0010.
 */
public class MyMainActivityScrollView extends ScrollView {
    public static boolean getIn = false;
    public static boolean getOut = true;
    public MyMainActivityScrollView(Context context){
        super(context);
    }

    public MyMainActivityScrollView(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyMainActivityScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
