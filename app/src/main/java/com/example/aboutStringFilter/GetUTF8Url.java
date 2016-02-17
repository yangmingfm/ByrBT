package com.example.aboutStringFilter;


import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ymjkMaster on 2015/5/31 0031.
 */
public class GetUTF8Url {
    public static String getUTF8Url(String rawUrl) {
        if (rawUrl != null) {
            Pattern pattern = Pattern.compile("images.*");
            Matcher matcher = pattern.matcher(rawUrl);
            if (matcher.find()) {
                //得到url的尾部
//                Log.e("===getTail===", matcher.group(0).replaceAll("images/", ""));
                String temp = matcher.group(0).replaceAll("images/", "");
                //将可能含有特殊字符的尾部转为utf-8
                if (!(temp.contains("%")&&temp.contains("(")&&temp.contains(")"))) {
                    try {
                        temp = URLEncoder.encode(temp, "UTF-8");
                        return "http://bt.byr.cn/ckfinder/userfiles/images/" + temp;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    return rawUrl;
                }
            }
            return rawUrl;
        }
        else
        {
            return null;
        }
    }
}
