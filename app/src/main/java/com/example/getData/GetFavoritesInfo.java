package com.example.getData;


import com.example.aboutHTML.AnalyzeFavorites;
import com.example.aboutHTML.GetHtmlStr;
import com.example.aboutHTML.TorrentList;

/**
 * Created by ymjkMaster on 2015/5/31 0031.
 */
public class GetFavoritesInfo {
    private TorrentList mList;
    private boolean getSuccess;
    private String strCookie;
    public void startGet(String strCookie){
        String url ="http://bt.byr.cn/torrents.php?inclbookmarked=1&allsec=1&incldead=0";
        GetHtmlStr.startGet(url,strCookie);
        if(GetHtmlStr.isGetSuccess())
        {
            mList = new TorrentList();
            String strHTML = GetHtmlStr.getStrHTML();
            AnalyzeFavorites analyzeFavorites = new AnalyzeFavorites();
            mList = analyzeFavorites.strartAnalyze(strHTML);
            getSuccess = true;
        }
        else
        {
            getSuccess = false;
        }
    }
    public boolean isGetSuccess(){return getSuccess;}
    public TorrentList getFavoriteList(){return mList;}
}
