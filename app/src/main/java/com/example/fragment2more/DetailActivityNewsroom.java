package com.example.fragment2more;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.aboutImage.MyImageCache2;
import com.example.aboutImage.MyImageLoader;
import com.example.aboutImage.SingleRequestQueue;
import com.example.aboutImage.TouchImageViewActivity;
import com.example.aboutStringFilter.GetUTF8Url;
import com.example.aboutStringFilter.TitleFilter;
import com.example.aboutView.MyScrollView;
import com.example.ymjkmaster.byrbta.R;
import com.gc.materialdesign.views.ButtonFloat;

/**
 * Created by ymjkMaster on 2015/6/15 0015.
 */
public class DetailActivityNewsroom extends Activity {
    private ImageView returnTv;
    private ButtonFloat detailButton;
    private TextView title;
    private TextView author;
    private TextView time;
    private TextView decs;
    private NetworkImageView img;
    private String DetailUrl;
    private String titleStr;
    private String authorStr;
    private String timeStr;
    private String decsStr;
    private MyScrollView scrollView;
    private ScrollView mScrollView;
    private MyImageLoader imageLoader;
    private String imgUrl;
//    private String tag = "===DetailActivityNewsroom===";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Intent intent = getIntent();
            titleStr = intent.getStringExtra("title");
            authorStr = intent.getStringExtra("author");
            timeStr = intent.getStringExtra("time");
            decsStr = intent.getStringExtra("decs");
            DetailUrl = intent.getStringExtra("detailUrl");
            imgUrl = intent.getStringExtra("imgUrl");
//        Log.e(tag, "decsStr" + decsStr);
            initView();


            title.setText(TitleFilter.titleFilter(titleStr));
            author.setText(authorStr);
            time.setText(timeStr);
            decs.setText(decsStr);
            imgUrl = GetUTF8Url.getUTF8Url(imgUrl);
            img.setDefaultImageResId(R.drawable.img_default);
            img.setErrorImageResId(R.drawable.load_error_b);
            img.setImageUrl(imgUrl, imageLoader);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(DetailActivityNewsroom.this, "解析失败", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
    public void initView(){
        setContentView(R.layout.detail_newsroom_layout);

        title = (TextView)findViewById(R.id.tv_title);
        author = (TextView)findViewById(R.id.tv_author);
        time = (TextView)findViewById(R.id.tv_date);
        decs = (TextView)findViewById(R.id.tv_decs);
        detailButton = (ButtonFloat)findViewById(R.id.btn_detail);
        img = (NetworkImageView)findViewById(R.id.img_main);
        returnTv = (ImageView)findViewById(R.id.return_image);

        scrollView = (MyScrollView)findViewById(R.id.mysrollview1);
        mScrollView = (ScrollView)findViewById(R.id.scrollView1);

        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("detailUrl",DetailUrl);
                Intent intent = new Intent(DetailActivityNewsroom.this, DetailActivity2.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        imageLoader = new MyImageLoader(SingleRequestQueue.getRequestQueue(getApplicationContext()), new MyImageCache2());
        scrollView.setOnScrollViewListener(new MyScrollViewListener());
        img.setOnClickListener(new MyNetworkImageViewOnClickListener(imgUrl));
        returnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public class MyNetworkImageViewOnClickListener implements View.OnClickListener{
        private String url;
        public MyNetworkImageViewOnClickListener(String Url){
            url = Url;
        }
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            Intent intent = new Intent(DetailActivityNewsroom.this, TouchImageViewActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public class  MyScrollViewListener implements  MyScrollView.ScrollViewListener
    {
        boolean outFlag = true;
        boolean inFlag = true;
        @Override
        public void onScroll(int x, int y, int oldx, int oldy) {
            if(y-oldy>10&&outFlag==true)
            {
                outFlag = false;
                inFlag = true;
                ObjectAnimator.ofFloat(mScrollView, "translationY", 0, -250).setDuration(200).start();
                ObjectAnimator.ofFloat(detailButton,"translationX",0,250).setDuration(200).start();
            }
            if(y-oldy<-10&&inFlag==true)
            {
                outFlag = true;
                inFlag = false;
                ObjectAnimator.ofFloat(mScrollView,"translationY",-250,0).setDuration(200).start();
                ObjectAnimator.ofFloat(detailButton,"translationX",250,0).setDuration(200).start();
            }
        }
    }
    @Override
    protected void onDestroy() {
        returnTv =null ;
        detailButton=null;
        title=null;
        author=null;
        time=null;
        decs=null;
        img=null;
        DetailUrl=null;
        titleStr=null;
        authorStr=null;
        timeStr=null;
        decsStr=null;
        scrollView=null;
        mScrollView=null;
        imageLoader=null;
        imgUrl=null;
        super.onDestroy();
    }

}
