package com.example.aboutHTML;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by ymjkMaster on 2015/5/31 0031.
 */
public class SentFavoriteInfo {
    public boolean sendSucess = false;
    public void sendFavorite(String strCookie,String id){
//        String tag = "===SendFavorite===";
        try{
            String url="http://bt.byr.cn/bookmark.php?torrentid="+id;
            HttpGet get= new HttpGet(url);
            get.addHeader("Cookie", strCookie);
//            Log.d(tag, "===httpget===");
            HttpResponse response = new DefaultHttpClient().execute(get);
            String a=""+response.getStatusLine().getStatusCode();
//            Log.d(tag, a);
            if (response.getStatusLine().getStatusCode() == 200)
            {
//                Log.d(tag, "===SUCCESS===");
                sendSucess = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
//            Log.d(tag, "===FAILED===");
        }
    }
}
