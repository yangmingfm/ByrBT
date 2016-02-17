//package com.example.Check;
//
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.util.Config;
//import android.util.Log;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.getData.GetLocalData;
//
//import org.json.JSONObject;
//
//
///**
// * Created by ymjkMaster on 2015/6/11 0011.
// */
//public class CheckUpdate {
//    private String updateUrl;
//    private String updateMsg;
//    private Context context;
//    public CheckUpdate(Context context){this.context = context;}
//    private int newVersionCode =1;
//    public  void getUpdateInfo(){
//        RequestQueue queue = Volley.newRequestQueue(context);
//        String url = "http://2.bupttestjson1.sinaapp.com/version.php";
//        JsonObjectRequest objRequest = new JsonObjectRequest(url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject obj) {
//                        String tag = "===checkUpdate===";
//                        try {
//                            newVersionCode = Integer.parseInt(obj.getString("version"));
//                            Log.e(tag,"newVersionCode"+newVersionCode);
//                            updateMsg = obj.getString("message");
//                            updateUrl = obj.getString("download");
//                        } catch (Exception e) {
//                            Log.e(tag,"===failed===");
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.getMessage();
//            }
//        }) ;
//        //设置发送报文的http的header
////        {
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                HashMap<String,String> hashMap = new HashMap<String,String>();
////                hashMap.put("apikey"," 03343921d1761baf6151fc657d002240");
////                return hashMap;
////            }
////        };
//        objRequest.setTag("obj");
//        queue.add(objRequest);
//    }
//    public boolean isUpdate(){
//        Log.e("===CheckUpdate","localVerCode"+GetLocalData.getVerCode(context));
//        if(this.newVersionCode>GetLocalData.getVerCode(this.context)) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//
//    public String getUpdateMsg(){return this.updateMsg;}
//
//    public String getUpdateUrl(){return this.updateUrl;}
//}
//
