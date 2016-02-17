package com.example.ymjkmaster.byrbta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.Check.CheckMainPage;
import com.example.aboutHTML.LandingBT;
import com.example.getData.GetLandPageInfo;
import com.example.getData.GetLocalData;
import com.gc.materialdesign.views.ButtonFlat;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by ymjkMaster on 2015/5/31 0031.
 */
public class LandActivity extends Activity {
    private MaterialEditText usr;
    private MaterialEditText pwd;
    private NetworkImageView img;
    private RequestQueue mQueue;
    private ButtonFlat landBtn;
    private ButtonFlat refreshBtn;
    private ImageLoader imageLoader;
    private String picUrl;
    private String picHash;
    private MaterialEditText picNum;
    Handler hander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case 0x111:
                    img.setImageUrl(picUrl, imageLoader);
                    break;
                case 0x112:
                    Toast.makeText(LandActivity.this, "网络不良~~", Toast.LENGTH_SHORT).show();
                    break;
                case 0x113:
                    getInfo();
                    Toast.makeText(LandActivity.this, "登陆失败~~", Toast.LENGTH_SHORT).show();
                    break;
                case 0x114:
                    Toast.makeText(LandActivity.this, "登陆成功~~", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LandActivity.this,MainActivity.class);
                    LandActivity.this.startActivity(intent);
                    finish();
            }
            if (msg.what == 0x111) {

            } else if (msg.what == 112) {
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.land_layout);
        initView();
        getInfo();

    }
    public void getInfo (){
        new Thread(){
            @Override
            public void run() {
                GetLandPageInfo getLandPageInfo = new GetLandPageInfo();
                getLandPageInfo.startGet();
                Message msg = new Message();
                if(getLandPageInfo.isGetSucess())
                {
                    picUrl = getLandPageInfo.getPicUrl();
                    picHash = getLandPageInfo.getPicHash();
                    msg.what = 0x111;
                }
                else
                {
                    msg.what=0x112;
                }
                hander.sendEmptyMessage(msg.what);

            }
        }.start();
    }
    public void initView(){
        usr = (MaterialEditText)findViewById(R.id.edittext1);
        pwd = (MaterialEditText)findViewById(R.id.edittext2);
        picNum = (MaterialEditText)findViewById(R.id.edittext3);
        img =  (NetworkImageView)findViewById(R.id.networkimageview1);
        landBtn = (ButtonFlat)findViewById(R.id.button_land);
        refreshBtn = (ButtonFlat)findViewById(R.id.button_refresh);
        mQueue = Volley.newRequestQueue(LandActivity.this);
        img.setErrorImageResId(R.drawable.icon_angrybirds);
        img.setDefaultImageResId(R.drawable.icon_angrybirds);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });

        imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
            }
        });
        landBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        LandingBT landingBT = new LandingBT();
                        try {
                            landingBT.StartLanding(usr.getText().toString().trim(), pwd.getText().toString().trim(), picNum.getText().toString().trim(), picHash);
                        } catch (Exception e) {
                            msg.what = 0x113;
                        }
                        String temp = landingBT.getStrHTML();
                        if (landingBT.isLandSuccess() && CheckMainPage.isMainPage(temp)) {
                            msg.what = 0x114;
                            GetLocalData.saveCookie(LandActivity.this, landingBT.getCookie());
                        } else {
                            msg.what = 0x113;
                        }
                        hander.sendEmptyMessage(msg.what);
                    }
                }.start();
            }
        });
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });
    }
    @Override
    protected void onDestroy() {
        usr = null;
        pwd = null;
        img = null;
        landBtn =null;
        refreshBtn =null;
        imageLoader=null;
        picUrl=null;
        picHash=null;
        picNum=null;
        super.onDestroy();
    }
}
