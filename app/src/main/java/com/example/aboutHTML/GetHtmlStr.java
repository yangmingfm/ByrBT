package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

        import org.apache.http.HttpResponse;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.util.EntityUtils;


/*
 * 得到某个URL的HTML的字符串 方便以后解析
 * 输入 为URL 输出 为该URL对应的HTML
 */
public class GetHtmlStr {
    private static String strHTML;
    private static boolean getSuccess;
    public static void startGet(String url,String strCookie){
        try{
            HttpGet get= new HttpGet(url);
            get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0");
            if(strCookie==null){
                get.addHeader("Cookie","Hm_lvt_9ea605df687f067779bde17347db8645=1432624813,1432738817,1432824312,1432999547; c_secure_ssl=bm9wZQ%3D%3D; byrbta=0; byrbta1=0; Hm_lpvt_9ea605df687f067779bde17347db8645=1433166304");
            }
            else {
                get.addHeader("Cookie", strCookie);
            }
            HttpResponse response = new DefaultHttpClient().execute(get);
            String a=""+response.getStatusLine().getStatusCode();
            if (response.getStatusLine().getStatusCode() == 200)
            {
                strHTML = EntityUtils.toString(response.getEntity());
                getSuccess = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            getSuccess = false;
        }
    }
    public static boolean isGetSuccess(){ return getSuccess;}
    public static String getStrHTML(){return strHTML;}

}

