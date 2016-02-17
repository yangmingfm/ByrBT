package com.example.getData;


import com.example.aboutHTML.AnalyzeLandingPage;
import com.example.aboutHTML.GetHtmlStr;

/**
 * Created by ymjkMaster on 2015/6/1 0001.
 */
public class GetLandPageInfo {
    private String picUrl = null;
    private String picHash = null;
    private boolean getSucess = false;
    public boolean isGetSucess(){return getSucess;}
    public void startGet(){
        GetHtmlStr.startGet("http://bt.byr.cn/login.php",null);
        if(GetHtmlStr.isGetSuccess()){
            String strHTML = GetHtmlStr.getStrHTML();
            AnalyzeLandingPage analyzeLandingPage = new AnalyzeLandingPage();
            analyzeLandingPage.startAnalyze(strHTML);
            picUrl = analyzeLandingPage.getPicUrl();
            picHash = analyzeLandingPage.getPicHash();
            getSucess = true;
        }
        else
            getSucess = false;
    }
    public String getPicUrl(){return this.picUrl;}
    public String getPicHash(){return this.picHash;}
}
