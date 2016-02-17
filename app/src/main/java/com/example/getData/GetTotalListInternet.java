package com.example.getData;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */


        import com.example.aboutHTML.AnalyzeList;
        import com.example.aboutHTML.GetHtmlStr;
        import com.example.aboutHTML.TorrentList;


public class GetTotalListInternet {
    private  static TorrentList mList;
    private static boolean getSuccess;
    public static void startGet(String url,String strCookie,String pageNum){
        url+=url+pageNum;
        GetHtmlStr.startGet(url,strCookie);
        if(GetHtmlStr.isGetSuccess())
        {
            mList = null;
            mList = new TorrentList();
            String strHTML = GetHtmlStr.getStrHTML();
            AnalyzeList.starAnalyze(strHTML);
            mList = AnalyzeList.getList();
            getSuccess = true;
        }
        else {
            getSuccess = false;
        }
    }
    public static TorrentList getTorrentList(){
        return mList;
    }
    public static boolean isGetSuccess(){return getSuccess;}
}

