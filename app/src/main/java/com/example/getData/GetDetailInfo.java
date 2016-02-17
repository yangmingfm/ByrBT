package com.example.getData;

import com.example.aboutHTML.AnalyzeDetailA;
import com.example.aboutHTML.GetHtmlStr;
import com.example.aboutHTML.TorrentDetail;

/**
 * Created by ymjkMaster on 2015/5/26 0026.
 */
public class GetDetailInfo {
    private static TorrentDetail aDetail;
    private static boolean getSucess;
    private static String rawHTML;

    public static void startGet(String url,String cookie){
        GetHtmlStr.startGet(url,cookie);
        if(GetHtmlStr.isGetSuccess()) {
            aDetail  = null;
            aDetail = new TorrentDetail();
            String strHTML = GetHtmlStr.getStrHTML();
            AnalyzeDetailA.startAnalyze(strHTML);
            aDetail = AnalyzeDetailA.getDetail();
            getSucess = true;
            rawHTML = AnalyzeDetailA.getRawHTML();
        }
        else
        {
            getSucess =false;
        }
    }
    public static TorrentDetail getDetail(){return aDetail;}
    public static boolean isGetSucess(){return getSucess;}
    public static String getRawHTML(){return rawHTML;}
}
