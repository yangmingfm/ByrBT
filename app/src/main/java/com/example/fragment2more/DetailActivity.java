package com.example.fragment2more;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.aboutHTML.SendUserInfo;
import com.example.aboutHTML.SentFavoriteInfo;
import com.example.aboutHTML.TorrentDetail;
import com.example.aboutImage.MyImageCache2;
import com.example.aboutImage.MyImageLoader;
import com.example.aboutImage.SingleRequestQueue;
import com.example.aboutImage.TouchImageViewActivity;
import com.example.aboutStringFilter.CookieStr2Id;
import com.example.aboutStringFilter.GetTorrentId;
import com.example.aboutStringFilter.GetUTF8Url;
import com.example.aboutStringFilter.TitleFilter;
import com.example.aboutView.MyScrollView;
import com.example.getData.GetDetailInfo;
import com.example.getData.GetLocalData;
import com.example.ymjkmaster.byrbta.R;
import com.gc.materialdesign.views.ButtonFloat;

import java.util.List;



/**
 * Created by ymjkMaster on 2015/5/25 0025.
 */
public class DetailActivity  extends Activity {
    private boolean isCollected = false;
    private NetworkImageView decsImg1;
    private NetworkImageView decsImg2;
    private NetworkImageView decsImg3;
    private NetworkImageView decsImg4;
    private NetworkImageView decsImg5;
    private ImageView returnTv;
    private String docSize;
    private String upNum;
    private String finishNum;
    private ButtonFloat buttonFloat;
    private MyScrollView scrollView;
    private ScrollView mScrollView;
    private TextView sizeTv;
    private TextView upNumTv;
    private TextView finishNumTv;
    private TextView decs;
    private TextView author;
    private TextView date;
    private String rawHTML;
    private TorrentDetail mDetail;
    private TextView title;
    private MyImageLoader imageLoader;
    private NetworkImageView networkImageView;
    private String url;
    private String torrentId;
    private String strCookie;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x222:
                    try {
                        updateView();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(DetailActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
                case 0x223:
                    Toast.makeText(DetailActivity.this, "网络不良~~", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case 0x224:
                    if(!isCollected) {
                        isCollected = true;
                        Toast.makeText(DetailActivity.this, "收藏成功 :)", Toast.LENGTH_SHORT).show();
                    }else {
                        isCollected = false;
                        Toast.makeText(DetailActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 0x225:
                    Toast.makeText(DetailActivity.this, "收藏失败~~", Toast.LENGTH_SHORT).show();
                    break;
                case 0x888:
                    Toast.makeText(DetailActivity.this, "接受成功", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            initView();

            Intent intent = getIntent();
            url = intent.getStringExtra("detailUrl");
            docSize = intent.getStringExtra("size");
            upNum = intent.getStringExtra("upNum");
            finishNum = intent.getStringExtra("finishNum");
            torrentId = GetTorrentId.getTorrentId(url);

            runRefresh();
        }catch (Exception e){
            Toast.makeText(DetailActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        networkImageView = null;
        decsImg1 = null;
        decsImg2 = null;
        decsImg3 = null;
        decsImg4 = null;
        decsImg5 = null;
        returnTv = null;
        docSize = null;
        upNum = null;
        finishNum = null;
        buttonFloat = null;
        scrollView = null;
        mScrollView = null;
        sizeTv = null;
        upNumTv = null;
        finishNumTv = null;
        decs = null;
        author = null;
        date = null;
        rawHTML = null;
        mDetail = null;
        title = null;
        imageLoader = null;
        networkImageView = null;
        url = null;
        torrentId = null;
        strCookie = null;
        super.onDestroy();
    }

    public void runRefresh() {
        new Thread() {
            @Override
            public void run() {
                GetDetailInfo.startGet(url, strCookie);
                Message msg = new Message();
                if (GetDetailInfo.isGetSucess()) {
                    mDetail = GetDetailInfo.getDetail();
                    msg.what = 0x222;
                    rawHTML = GetDetailInfo.getRawHTML();
                    handler.sendEmptyMessage(msg.what);
                } else {
                    msg.what = 0x223;
                    handler.sendEmptyMessage(msg.what);
                }
            }
        }.start();
    }

    public void initView(){
        setContentView(R.layout.torrentlist_detail_layout);
//        frameLayoutTotal = (FrameLayout)findViewById(R.id.framelayout_total);
//        progressBarCircularIndeterminate = (ProgressBarCircularIndeterminate)findViewById(R.id.progressBarCircularIndeterminate);
//        scrollViewProgress = (ScrollView)findViewById(R.id.scrollView_progressbar);

        networkImageView = (NetworkImageView) findViewById(R.id.networkimageview1);
        decsImg1 = (NetworkImageView)findViewById(R.id.img_decs1);
        decsImg2 = (NetworkImageView)findViewById(R.id.img_decs2);
        decsImg3 = (NetworkImageView)findViewById(R.id.img_decs3);
        decsImg4 = (NetworkImageView)findViewById(R.id.img_decs4);
        decsImg5 = (NetworkImageView)findViewById(R.id.img_decs5);
        title = (TextView) findViewById(R.id.textview_title);
        decs = (TextView) findViewById(R.id.textview_decs);
        author = (TextView)findViewById(R.id.tv_author);
        date = (TextView)findViewById(R.id.tv_date);
        sizeTv = (TextView)findViewById(R.id.tv_size_top);
        upNumTv = (TextView)findViewById(R.id.textview_upnum);
        finishNumTv = (TextView)findViewById(R.id.textview_finishnum);
        returnTv = (ImageView)findViewById(R.id.imageview1);

        buttonFloat = (ButtonFloat)findViewById(R.id.floatbutton);
        scrollView = (MyScrollView)findViewById(R.id.mysrollview1);
        mScrollView = (ScrollView)findViewById(R.id.scrollView1);

        imageLoader = new MyImageLoader(SingleRequestQueue.getRequestQueue(getApplicationContext()), new MyImageCache2());
        scrollView.setOnScrollViewListener(new MyScrollViewListener());
        buttonFloat.setOnClickListener(new MyButtionFloatOnClickListener());
        returnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        strCookie = GetLocalData.getCookie(DetailActivity.this);
    }

    public void updateView(){
        List<String> decsImgUrl = mDetail.getUrlList();
        switch (decsImgUrl.size()){
            case 0:
                break;
            case 1:
                decsImg1.setImageUrl(decsImgUrl.get(0),imageLoader);
                decsImg1.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(0)));
                break;
            case 2:
                decsImg1.setImageUrl(decsImgUrl.get(0),imageLoader);
                decsImg2.setImageUrl(decsImgUrl.get(1),imageLoader);
                decsImg1.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(0)));
                decsImg2.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(1)));
                break;
            case 3:
                decsImg1.setImageUrl(decsImgUrl.get(0),imageLoader);
                decsImg2.setImageUrl(decsImgUrl.get(1),imageLoader);
                decsImg3.setImageUrl(decsImgUrl.get(2),imageLoader);
                decsImg1.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(0)));
                decsImg2.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(1)));
                decsImg3.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(2)));
                break;
            case 4:
                decsImg1.setImageUrl(decsImgUrl.get(0),imageLoader);
                decsImg2.setImageUrl(decsImgUrl.get(1),imageLoader);
                decsImg3.setImageUrl(decsImgUrl.get(2),imageLoader);
                decsImg4.setImageUrl(decsImgUrl.get(3), imageLoader);
                decsImg1.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(0)));
                decsImg2.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(1)));
                decsImg3.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(2)));
                decsImg4.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(3)));
                break;
            case 5:
                decsImg1.setImageUrl(decsImgUrl.get(0),imageLoader);
                decsImg2.setImageUrl(decsImgUrl.get(1),imageLoader);
                decsImg3.setImageUrl(decsImgUrl.get(2),imageLoader);
                decsImg4.setImageUrl(decsImgUrl.get(3), imageLoader);
                decsImg5.setImageUrl(decsImgUrl.get(4), imageLoader);
                decsImg1.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(0)));
                decsImg2.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(1)));
                decsImg3.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(2)));
                decsImg4.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(3)));
                decsImg5.setOnClickListener(new MyNetworkImageViewOnClickListener(decsImgUrl.get(4)));
                break;
            default:
                break;
        }
        url = mDetail.getThemeImgUrl();
        url = GetUTF8Url.getUTF8Url(url);
        networkImageView.setDefaultImageResId(R.drawable.img_default);
        networkImageView.setErrorImageResId(R.drawable.load_error_b);
        networkImageView.setImageUrl(url, imageLoader);
        networkImageView.setOnClickListener(new MyNetworkImageViewOnClickListener(url));
        author.setText("By " + mDetail.getAuthor());
        date.setText(mDetail.getUpTime());
        sizeTv.setText(docSize);
        upNumTv.setText(upNum);
        finishNumTv.setText(finishNum);
        title.setText(TitleFilter.titleFilter(mDetail.getTitle()));
        decs.setTextSize(15);
        decs.setText(Html.fromHtml(rawHTML));
        ObjectAnimator.ofFloat(title,"Alpha",0.5f,1.0f).setDuration(800).start();
        ObjectAnimator.ofFloat(author,"Alpha",0.5f,1.0f).setDuration(800).start();
        ObjectAnimator.ofFloat(date,"Alpha",0.5f,1.0f).setDuration(800).start();
        ObjectAnimator.ofFloat(decs,"Alpha",0.5f,1.0f).setDuration(800).start();
        SendUserInfo.setParams(CookieStr2Id.cookie2id(GetLocalData.getCookie(DetailActivity.this)), mDetail.getTitle(), "");
        SendUserInfo.startSendUsrInfo(DetailActivity.this);
//        GetNewsroomList getNewsroomList = new GetNewsroomList(DetailActivity.this,handler);
//        getNewsroomList.startGet();
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
                ObjectAnimator.ofFloat(mScrollView,"translationY",0,-250).setDuration(200).start();
                ObjectAnimator.ofFloat(buttonFloat,"translationX",0,250).setDuration(200).start();
            }
            if(y-oldy<-10&&inFlag==true)
            {
                outFlag = true;
                inFlag = false;
                ObjectAnimator.ofFloat(mScrollView,"translationY",-250,0).setDuration(200).start();
                ObjectAnimator.ofFloat(buttonFloat,"translationX",250,0).setDuration(200).start();
            }
        }
    }
    public class MyButtionFloatOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            new Thread(){
                @Override
                public void run() {

                    SentFavoriteInfo sentFavoriteInfo = new SentFavoriteInfo();
                    sentFavoriteInfo.sendFavorite(strCookie,torrentId);
                    Message msg = new Message();
                    if (sentFavoriteInfo.sendSucess)
                    {
                        msg.what = 0x224;
                    }
                    else
                    {
                        msg.what = 0x225;
                    }
                    handler.sendEmptyMessage(msg.what);
                }
            }.start();
        }
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
            Intent intent = new Intent(DetailActivity.this, TouchImageViewActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}