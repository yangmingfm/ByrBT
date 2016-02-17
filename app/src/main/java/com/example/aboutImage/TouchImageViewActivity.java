package com.example.aboutImage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.example.ymjkmaster.byrbta.R;

/**
 * Created by ymjkMaster on 2015/6/13 0013.
 */
public class TouchImageViewActivity extends  Activity {
    private MyTouchImageView touchImageView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        RequestQueue mQueue = SingleRequestQueue.getRequestQueue(getApplicationContext());
        //ImageLoader mImageLoader = new ImageLoader(SingleRequestQueue.getRequestQueue(getApplicationContext()), new MyImageCache2());
        setContentView(R.layout.touch_imageview_layout);
        touchImageView = (MyTouchImageView)findViewById(R.id.imageview_touch);
        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        touchImageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                touchImageView.setImageResource(R.drawable.load_error_b);
            }
        });
        mQueue.add(imageRequest);
    }
}
