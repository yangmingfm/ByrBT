package com.example.Check;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by ymjkMaster on 2015/5/31 0031.
 */
public class CheckLandSucess {
    public static boolean isLandSucess(String strHTML){
        Element name;
        Elements name1;
        Element name2;
        Document doc = Jsoup.parse(strHTML);
        try {
            if(doc.getElementsByClass("mainouter").first().getElementsByTag("a").first().attr("href")=="login.php")
            {
                return false;
            }
            else
            {
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }
}
