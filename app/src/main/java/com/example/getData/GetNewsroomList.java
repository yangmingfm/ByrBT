package com.example.getData;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.aboutHTML.TorrentItemB;
import com.example.aboutHTML.TorrentListB;
import com.example.aboutImage.SingleRequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ymjkMaster on 2015/6/15 0015.
 */
public class GetNewsroomList {
    private Context context;
    private Handler handler;
    private TorrentListB mList;
    public GetNewsroomList(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
        this.mList = new TorrentListB();
    }
    public void startGet() {
        RequestQueue queue = SingleRequestQueue.getRequestQueue(context);
        String url = "http://2.bupttestjson1.sinaapp.com/newsroom.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        Message msg = new Message();
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject aObject = (JSONObject) jsonArray.get(i);
                                TorrentItemB aItem = new TorrentItemB();
                                aItem.setTitle(aObject.getString("title"));
                                aItem.setImgUrl(aObject.getString("imgurl"));
                                aItem.setDecs(aObject.getString("decs"));
                                aItem.setAuthor(aObject.getString("author"));
                                aItem.setTime(aObject.getString("time"));
                                aItem.setDetailUrl(aObject.getString("detailUrl"));
                                Log.e("===GetNewsList===","detailUrl"+aItem.getDetailUrl());
                                mList.addTorrentItem(aItem);
                            }
                            msg.what = 0x888;
                            handler.sendEmptyMessage(msg.what);
                        } catch (Exception e){
                            e.printStackTrace();
                            msg.what = 0x887;
                            handler.sendEmptyMessage(msg.what);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Message msg = new Message();
                msg.what = 0x887;
                handler.sendEmptyMessage(msg.what);
            }
        });
        queue.add(jsonArrayRequest);
    }
    public TorrentListB getList(){return this.mList;}
}
