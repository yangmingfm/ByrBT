package com.example.aboutImage;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ymjkMaster on 2015/6/13 0013.
 */
public class SingleRequestQueue {

    private static RequestQueue mQueue;

    private SingleRequestQueue(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public static synchronized RequestQueue getRequestQueue(Context context){
        if (mQueue == null){
            new SingleRequestQueue(context.getApplicationContext());
        }
        return mQueue;
    }
}