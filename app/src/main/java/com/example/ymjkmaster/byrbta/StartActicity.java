package com.example.ymjkmaster.byrbta;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.Check.CheckCookie;

/**
 * Created by ymjkMaster on 2015/6/3 0003.
 */
public class StartActicity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        ImageView imageView =(ImageView)findViewById(R.id.imageview1);
        imageView.setAlpha(0f);
        AnimatorSet set  = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView,"Alpha",0.3f,1.0f).setDuration(500);
        set.playSequentially(animator1);
        set.setStartDelay(500);
        set.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(CheckCookie.checkCookie(StartActicity.this))
                {
                    Intent intent = new Intent(StartActicity.this,MainActivity.class);
                    StartActicity.this.startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(StartActicity.this,LandActivity.class);
                    StartActicity.this.startActivity(intent);
                    finish();
                }
            }
        }, 1800);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
