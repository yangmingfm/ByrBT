package com.example.getData;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import com.example.ymjkmaster.byrbta.R;

import java.util.List;

/**
 * Created by ymjkMaster on 2015/5/31 0031.
 */
public class GetLocalData {
    public static String getCookie(Context context) {
        SharedPreferences preference = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        return preference.getString("cookie", "getFalse");
    }

    public static void saveCookie(Context context, String strCookie) {
        SharedPreferences preference = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("cookie", strCookie);
        editor.commit();
    }

    public static void saveSelectorList(List<Integer> aList, Context context) {
        String temp = "";
        for (int i = 0; i < aList.size(); i++) {
            temp = temp + "&" + aList.get(i);
        }
        SharedPreferences preference = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("listFragmentBeans", temp);
        editor.commit();
    }

    public String getBeans(Context context) {
        SharedPreferences preference = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        return preference.getString("listFragmentBeans", "getFalse");

    }
    public static int getVerCode(Context context){
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    "com.example.ymjkmaster.byrbta", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    "com.example.ymjkmaster.byrbta", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verName;
    }
    public static String getAppName(Context context) {
        String verName = context.getResources()
                .getText(R.string.app_name).toString();
        return verName;
    }
}

