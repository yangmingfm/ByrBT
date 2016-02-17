package com.example.aboutStringFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ymjkMaster on 2015/6/8 0008.
 */
public class DateFilter {
    public static String dateFilter(String strDate){
        try {
            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher matcher = pattern.matcher(strDate);
            if (matcher.find()) {
                return matcher.group(0);
            } else {
                return strDate;
            }
        }
        catch(Exception e){
            return strDate;
        }
    }
}
