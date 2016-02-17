package com.example.getData;

import com.example.aboutHTML.AnalyzeMainPage;
import com.example.aboutHTML.GetHtmlStr;
import com.example.aboutHTML.TorrentList;
import com.example.aboutHTML.TorrentListA;

/**
 * Created by ymjkMaster on 2015/5/24 0024.
 */
public class GetMainPageInfo {
    private static TorrentList mData;
    private static TorrentListA mDataA;
    private static boolean getSuccess;
    public static void startGet(String strCookie){
        GetHtmlStr.startGet("http://bt.byr.cn/index.php",strCookie);
        if(GetHtmlStr.isGetSuccess()){
            mData = null;
            mDataA = null;
            mData = new TorrentList();
            mDataA = new TorrentListA();
            String strHTML = GetHtmlStr.getStrHTML();
            AnalyzeMainPage analyzeMainPage = new AnalyzeMainPage();
            analyzeMainPage.startAnaylze(strHTML);
            mData = analyzeMainPage.getPriceTorrents();
            mDataA = analyzeMainPage.getHot();
            getSuccess = true;
        }
        else
            getSuccess = false;
    }
    public static boolean isGetSuccess(){return getSuccess;}
    public static TorrentList getPrize(){return mData;}
    public static TorrentListA getHot(){return mDataA;}
}
