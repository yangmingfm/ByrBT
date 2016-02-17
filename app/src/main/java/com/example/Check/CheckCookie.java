package com.example.Check;

import android.content.Context;

import com.example.getData.GetLocalData;

/**
 * Created by ymjkMaster on 2015/6/2 0002.
 */
public class CheckCookie {
    public static boolean checkCookie(Context context) {
        if(GetLocalData.getCookie(context)!="getFalse"&&GetLocalData.getCookie(context).contains("c_secure_pass"))
        {
            return true;
        }
        else
            return false;
    }

}
