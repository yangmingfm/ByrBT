package com.example.aboutImage;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by ymjkMaster on 2015/6/8 0008.
 */
public class MyImageCache2 implements ImageLoader.ImageCache {
    public MyImageCache2(){
    }
    @Override
    public Bitmap getBitmap(String url) {
        return null;
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
    }
}
