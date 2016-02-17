package com.example.aboutImage;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by ymjkMaster on 2015/6/3 0003.
 */
public class MyImageCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> cache;

    public MyImageCache() {
        cache = new LruCache<String, Bitmap>(8 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
//        Log.e("==Cache===","getBitMap"+url);
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
//        Log.e("==Cache===","putBitmap"+url);
        cache.put(url, bitmap);
    }
}
