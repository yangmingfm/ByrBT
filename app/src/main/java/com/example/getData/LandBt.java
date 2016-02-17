package com.example.getData;

import android.content.Context;

import com.example.aboutHTML.LandingBT;

/**
 * Created by ymjkMaster on 2015/6/1 0001.
 */
public class LandBt {
    private boolean landSuccess;
    public void startLand(String userName,String pwd,String picNum,String picHash,Context context){
        LandingBT land = new LandingBT();
        land.StartLanding(userName,pwd,picNum,picHash);
        if(land.isLandSuccess()) {
            GetLocalData.saveCookie(context,land.getCookie());
            landSuccess = true;
        }
        else {
            landSuccess = false;
        }
    }
    public boolean isLandSuccess(){return this.landSuccess;}
}
