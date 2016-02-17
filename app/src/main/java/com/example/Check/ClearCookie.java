package com.example.Check;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ymjkMaster on 2015/6/3 0003.
 */
public class ClearCookie {
    public static void clearCookie(Context context) {
        SharedPreferences preference = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =preference.edit();
        editor.putString("cookie", "getFalse");
        editor.commit();
    }
}
