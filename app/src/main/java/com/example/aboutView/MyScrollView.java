package com.example.aboutView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;


/**
 * Created by ymjkMaster on 2015/6/6 0006.
 */
public class MyScrollView extends ScrollView {
    private ScrollViewListener mScrollViewListener = null;
    public MyScrollView(Context context){
        super(context);
    }
    public MyScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public interface ScrollViewListener{
        void onScroll(int x, int y, int oldx, int oldy);
    }
    public void setOnScrollViewListener(ScrollViewListener mScrollViewListener){
        this.mScrollViewListener = mScrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        if (mScrollViewListener!=null)
        {
            mScrollViewListener.onScroll(x,y,oldx,oldy);
        }
        super.onScrollChanged(x, y, oldx, oldy);
    }


//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//    }
}
