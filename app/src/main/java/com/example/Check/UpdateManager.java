package com.example.Check;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.aboutImage.SingleRequestQueue;
import com.example.getData.GetLocalData;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ymjkMaster on 2015/6/11 0011.
 */
public class UpdateManager {
    private int newVersionCode;
    private boolean isUpdate;
    private String updateUrl;
    private String updateMsg;
    private Context context;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
    private static final int BEGIN_UPDATE = 3;
    private int progress;
    private Thread downLoadThread;
    private boolean interceptFlag = false;
    private MaterialDialog downloadDialog;
    //下载路径
    private static final String savePath = "/sdcard/updatedemo/";
    private static final String saveFileName = savePath + "ByrBTa.apk";
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    downloadDialog.setProgress(progress);
                    break;
                case DOWN_OVER:
                    installApk();
                    break;
                case BEGIN_UPDATE:
                    new AlertDialogWrapper.Builder(context)
                            .setTitle("有新版本啦！")
                            .setMessage(updateMsg)
                            .setPositiveButton("更之", new noticeDialogPositiveOnClickListener())
                            .setNegativeButton("下次再说", new noticeDialogNegativetiveOnClickListener()).show();
                    break;
                default:
                    break;
            }
        };
    };
    public  UpdateManager(Context context){
        this.context = context;
    }

    private class noticeDialogPositiveOnClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            showProgressBar();
        }
    }
    private class noticeDialogNegativetiveOnClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }
    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(updateUrl);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                File file = new File(savePath);
                if(!file.exists()){
//                    Log.e(tag,"mkdir");
                    file.mkdir();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);

                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count = 0;
                byte buf[] = new byte[1024];

                do{
                    int numread = is.read(buf);
                    count += numread;
                    progress =(int)(((float)count / length) * 100);
                    //更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if(numread <= 0){
                        //下载完成通知安装
                        mHandler.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf,0,numread);
                }while(!interceptFlag);//点击取消就停止下载.

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }

        }
    };
    private void showProgressBar(){
        boolean showMinMax = true;
        downloadDialog = new MaterialDialog.Builder(context)
                .title("正在下载......")
                .progress(false, 100, showMinMax)
                .negativeText("取消")
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        interceptFlag = true;
                        downloadDialog.dismiss();
                    }
                })
                .show();
        downloadApk();
    }
    private void downloadApk(){
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }
    private void installApk(){
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        context.startActivity(i);
    }
    public  void getUpdateInfo() {
        final Message msg = new Message();
        RequestQueue queue = SingleRequestQueue.getRequestQueue(context);
        String url = "http://2.bupttestjson1.sinaapp.com/version.php";
        JsonObjectRequest objRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject obj) {
                        try {
                            newVersionCode = Integer.parseInt(obj.getString("version"));
//                            Log.e(tag, "newVersionCode" + newVersionCode);
                            updateMsg = obj.getString("message");
                            updateUrl = obj.getString("download");
                            if (isUpdate()) {
                                msg.what = BEGIN_UPDATE;
                                mHandler.sendEmptyMessage(msg.what);
                            }
                        } catch (Exception e) {
//                            Log.e(tag, "===failed===");
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(msg.what);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
        objRequest.setTag("obj");
        queue.add(objRequest);
    }
    public boolean isUpdate(){
        if(this.newVersionCode>GetLocalData.getVerCode(this.context)) {
            return true;
        }
        else {
            return false;
        }
    }
}
