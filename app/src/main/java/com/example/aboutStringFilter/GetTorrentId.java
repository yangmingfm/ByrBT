package com.example.aboutStringFilter;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ymjkMaster on 2015/5/31 0031.
 */
public class GetTorrentId{
    public static String getTorrentId(String detailUrl){
        Pattern pattern = Pattern.compile("id=\\d*");
        try {
            Matcher matcher = pattern.matcher(detailUrl);
            if (matcher.find()) {
//                Log.e("===TorrentItemId===", matcher.group(0).replaceAll("id=", ""));
                return matcher.group(0).replaceAll("id=", "");
            }
        }catch (Exception e) {

        }
        return null;
    }
}
