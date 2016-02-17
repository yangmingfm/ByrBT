package com.example.aboutStringFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ymjkMaster on 2015/6/13 0013.
 */
public class CookieStr2Id {
    public static  String cookie2id(String strCookie){
        try {
            Pattern pattern = Pattern.compile("c_secure_uid\\=[a-zA-Z]*\\;");
            Matcher matcher = pattern.matcher(strCookie);
            if (matcher.find()) {
                return matcher.group(0);
            } else {
                return strCookie;
            }
        }
        catch(Exception e){
            return strCookie;
        }
    }
}
