package com.example.aboutImage;

import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by ymjkMaster on 2015/6/14 0014.
 */
public class MyImageLoader extends com.android.volley.toolbox.ImageLoader {
    public MyImageLoader(RequestQueue queue, ImageLoader.ImageCache imageCache) {
        super(queue,imageCache);
    }
    public static ImageLoader.ImageListener getImageListener(final ImageView view, final int defaultImageResId, final int errorImageResId) {
        return new ImageLoader.ImageListener() {
            public void onErrorResponse(VolleyError error) {
                if(errorImageResId != 0) {
                    view.setImageResource(errorImageResId);
                }

            }

            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if(response.getBitmap() != null) {
                    view.setImageBitmap(response.getBitmap());
                } else if(defaultImageResId != 0) {
                    view.setImageResource(defaultImageResId);
                }

            }
        };
    }
}