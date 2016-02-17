package com.example.Check;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by ymjkMaster on 2015/6/2 0002.
 */
public class CheckMainPage {
    public static boolean isMainPage(String strHTML){
        Document doc = Jsoup.parse(strHTML);
        try {
            if(doc.getElementsByTag("title").first().text().contains("首页"))
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

