package com.example.aboutHTML;

import android.content.Context;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.aboutImage.SingleRequestQueue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ymjkMaster on 2015/6/14 0014.
 */
public class SendSuggestion {
    private String userId;
    private String suggestionStr;
    private MaterialDialog dialog;
    public SendSuggestion(String UserId,String SuggestionStr,MaterialDialog Dialog){
        userId = UserId;
        suggestionStr = SuggestionStr;
        dialog = Dialog;
    }
    public void  startSendSuggestion(final Context context){
        RequestQueue requestQueue = SingleRequestQueue.getRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://2.bupttestjson1.sinaapp.com/getSuggestion.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,"发送成功，谢谢您的宝贵建议 :)",Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"发送失败 :(",Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId", userId);
                map.put("Suggestion", suggestionStr);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}