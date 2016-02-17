package com.example.aboutHTML;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.aboutHEX.String2MD5;
import com.example.aboutImage.SingleRequestQueue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ymjkMaster on 2015/6/13 0013.
 */
public class SendUserInfo {
//    private static String tag = "===SendUserInfo===";
    private static String userId;
    private static String torName;
    private static String torCategory;
    public static void setParams(String UserId,String TorName,String TorCategory){
        userId = UserId;
        torName = TorName;
        torCategory = TorCategory;
    }
    public static void  startSendUsrInfo(Context context){
        RequestQueue requestQueue = SingleRequestQueue.getRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://2.bupttestjson1.sinaapp.com/getInfo.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.e(tag, "response -> " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e(tag, error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId", String2MD5.string2md5(userId));
                map.put("torName", torName);
                map.put("torCategory", torCategory);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
