package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

        import java.util.ArrayList;
        import java.util.List;

        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.cookie.Cookie;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.protocol.HTTP;
        import org.apache.http.util.EntityUtils;

        import android.util.Log;
/*
 * 用于首次登陆，实现
 * 		1.POST用户名 密码 验证码 以及imageHash（调用SET方法）
 *		2.保存 cookie的值 注意 要构造该类时要 传入context参数  ,之前必须先POST数据
 *		3.该类可返回 strHTML但暂时没用
 *		4.构造时，将用户名等参数传入，调用startLangding 方法 可以登录
 */
public class LandingBT {
    private String strCookie;
    private String strHTML;
    private boolean landSuccess;
    private List<NameValuePair> userInfo;
    //构造器 注意把CONTEXT传入！！！
    public  boolean isLandSuccess(){return landSuccess;}
    public  String getStrHTML(){return strHTML;}
    public  String getCookie(){return strCookie;}
    public  void setUserInfo(){
        //注意实例化的区别！！！！！！！~

    }
    public void StartLanding(String userName,String userPass, String picNum,String imageHash){
        userInfo = new ArrayList<NameValuePair>();
        userInfo.add(new BasicNameValuePair("username", userName));
        userInfo.add(new BasicNameValuePair("password", userPass));
        userInfo.add(new BasicNameValuePair("imagestring",picNum));
        userInfo.add(new BasicNameValuePair("imagehash", imageHash));
        try{
            String url = "http://bt.byr.cn/takelogin.php";
            HttpPost post= new HttpPost(url);
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0");
            //post.setHeader("Cookie", "Hm_lvt_9ea605df687f067779bde17347db8645=1430441564,1430479256,1430752170; c_secure_ssl=bm9wZQ%3D%3D; Hm_lpvt_9ea605df687f067779bde17347db8645=1430921346");
            post.setEntity(new UrlEncodedFormEntity(userInfo,HTTP.UTF_8));
            DefaultHttpClient aClient = new DefaultHttpClient();
            HttpResponse response = aClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200)
            {
                strHTML = EntityUtils.toString(response.getEntity());
                landSuccess = true;
                //利用Defalut类的方法获取 cookie
                //注意返回的cookie 是一个List<cookie>
                //填进post的header的时候要注意 以键值对的形式放入，不能直接tostring
                //因为原始信息包含了version等信息 需要getName getValue
                List<Cookie> cookie = aClient.getCookieStore().getCookies();
                String aPair = "";
                for(int i=0;i<cookie.size();i++)
                {
                    if(i==(cookie.size()-1))
                        aPair += cookie.get(i).getName()+"="+cookie.get(i).getValue();
                    else
                        aPair += cookie.get(i).getName()+"="+cookie.get(i).getValue()+"; ";
                }
                strCookie = aPair;
            }
            else
            {
                landSuccess = false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            landSuccess = false;
        }
    }
    //保存 cookie 注意 下次 用getSharedPreferences("cookie",XXX)即可获得相应的XML文件
    //用context 的静态函数构造SharedPreference
//    public void saveCookie(Context context){
//        SharedPreferences preference = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor =preference.edit();
//        editor.putString("cookie", strCookie);
//        editor.commit();
//        Log.e("===LOOK===","===GetCookie===");
//        preference.getString("cookie", null);
//        Log.e("===getSuccess===",preference.getString("cookie", null));
//    }
}

