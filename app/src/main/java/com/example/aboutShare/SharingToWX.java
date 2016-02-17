package com.example.aboutShare;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.ymjkmaster.byrbta.R;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
/**
 * Created by ymjkMaster on 2015/6/16 0016.
 */
public class SharingToWX {
    private Context context;
    private static final  String APP_ID ="wx4341033531a14a4f";
    private IWXAPI api;
    public SharingToWX(Context context){
        this.context = context;
    }
    public void setReq(){
        api  = WXAPIFactory.createWXAPI(context,APP_ID,true);
        api.registerApp(APP_ID);
//        WXWebpageObject webpage = new WXWebpageObject();
//        webpage.webpageUrl = "http://2.bupttestjson1.sinaapp.com/godown.html";


//        WXTextObject textObject = new WXTextObject();
//        textObject.text = "http://2.bupttestjson1.sinaapp.com/godown.html";
//        WXMediaMessage msg = new WXMediaMessage(textObject);
//        msg.title = "testTitle";
//        msg.description = "testDesc";
        //这里替换一张自己工程里的图片资源
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://smartisian.club/introduction.html";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "论如何正确地使用北邮人BT。试试BT夹子for android，一个实用的小工具 :)";
        msg.description = "testDesc";
        //这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.drawable.btjz_b_for_wx_pengyouquan);
        msg.setThumbImage(thumb);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene =SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
