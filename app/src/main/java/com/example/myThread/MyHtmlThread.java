//package com.example.myThread;
//
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//
//import com.example.aboutHTML.GetHtmlStr;
//
//
///**
// * Created by ymjkMaster on 2015/5/22 0022.
// * 该类用于 新开线程实现获取给定url的htmlStr 以及消息接受的处理，判断是否接受成功
// * 输入为 url ,strCookie，
// */
//public  class MyHtmlThread extends Thread  {
////    private Handler mHandler = new Handler(){
////        @Override
////        public void handleMessage(Message msg) {
////            super.handleMessage(msg);
////        }
////    }
//    private GetHtmlStr mGetHtmlStr;
//    private  boolean getSuccess;
//    private String strHTML;
//    private String tag = "===MyHtmlThread===";
//    public MyHtmlThread(String  url,String cookie )
//    {
//        mGetHtmlStr = new GetHtmlStr(url, cookie);
//    }
//
//    @Override
//    public void run() {
//        Log.e(tag, "===StartRun===");
//        GetHtmlStr.startGet(url);
//                if(GetHtmlStr.isGetSuccess()) {
//                    this.strHTML = GetHtmlStr.getStrHTML();
//                    getSuccess = true;
//                }
//                else{
//                    getSuccess = false;
//                }
//            }
//    public boolean isGetSuccess(){return this.getSuccess;}
//    public String getStrHTML(){return this.strHTML;}
//}
