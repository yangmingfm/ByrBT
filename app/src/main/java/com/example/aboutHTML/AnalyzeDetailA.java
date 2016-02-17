package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * 该类能分析BT的种子那个页面返回一个torrentDetail类，注意torrentDetail类包含了UserList类
 * 该类还未实现 分析 豆瓣信息 表单的功能
 *
 */
//开始分析
public class AnalyzeDetailA {
    private static TorrentDetail mDetail;
    private static String rawHTML;
    public static TorrentDetail getDetail(){return mDetail;}
    public static void startAnalyze(String strHTML){
        mDetail = null;
        mDetail = new TorrentDetail();
        String temp = "";
        Element name;
        Elements name1;
        Element name2;
        Document doc = Jsoup.parse(strHTML);
        //获取题目(这样获取的题目会有一个<b></b>的标签
        try {
            name = doc.getElementById("share");
            mDetail.setTitle(name.text());
        }catch (Exception e){
            return;
        }
//        Log.e(tag,"===Title===");
//        Log.e(tag,name.text());
        //获取种子上传时间(改进版)
//        name = doc.getElementById("outer");
//        name2 = name.child(1).child(0).child(0).child(1).child(3);
//        Log.e(tag,"===upTime===");
//        Log.e(tag,name2.text());
        //获取作者
        try {
                name = doc.getElementById("outer").child(1).child(0).child(0).child(1);
                try {
                    mDetail.setAuthor(name.getElementsByTag("b").first().text());
                } catch (Exception a) {
                    mDetail.setAuthor("匿名");
                }
                try {
                    mDetail.setUpTime(name.getElementsByTag("span").attr("title"));
                } catch (Exception b) {
                    mDetail.setUpTime("");
                }
                try {
                    name = doc.getElementById("outer").child(1).child(0).child(7).getElementById("kdescr");
                    name1 = name.getElementsByTag("img");
                    for (int i = 0; i < name1.size(); i++) {
                        name2 = name1.get(i);
                        if (i == 0) {
                            mDetail.setThemeImgUrl(name2.attr("src"));
                        } else {
                            mDetail.addImgUrl(name2.attr("src"));
                        }
                    }
                    mDetail.setSubTitle(doc.getElementById("outer").child(1).child(0).child(1).child(1).text());
                }
                catch (Exception c)
                {

                }
                //这个地方用replaceAll("^<img[\w\W]>$","")并没有用！！！！！怎么回事
                //java 中的正则匹配^表示一行的开头
                for(int i=7;i<11;i++) {
                    try {
                        if (doc.getElementById("outer").child(1).child(0).child(i).getElementsByTag("p").first().text().contains("豆瓣")) {
                            temp = doc.getElementById("outer").child(1).child(0).child(i).child(1).toString();
                            break;
                        }
                    } catch(Exception d){
                    }
                }
        } catch (Exception e) {
            name = doc.getElementById("outer").child(5).child(0).child(0).child(1);
            try {
                mDetail.setAuthor(name.getElementsByTag("b").first().text());
            } catch (Exception f) {
                mDetail.setAuthor("匿名");
            }
            try {
                mDetail.setUpTime(name.getElementsByTag("span").attr("title"));
            } catch (Exception g) {
                mDetail.setUpTime("");
            }
            try {
                name = doc.getElementById("outer").child(5).child(0).child(7).getElementById("kdescr");
                name1 = name.getElementsByTag("img");
                for (int i = 0; i < name1.size(); i++) {
                    name2 = name1.get(i);
                    if (i == 0) {
                        mDetail.setThemeImgUrl(name2.attr("src"));
                    } else {
                        mDetail.addImgUrl(name2.attr("src"));
                    }
                }
                mDetail.setSubTitle(doc.getElementById("outer").child(5).child(0).child(1).child(1).text());
            }
            catch (Exception h)
            {

            }
            for(int i=7;i<11;i++) {
                try {
                    if (doc.getElementById("outer").child(5).child(0).child(i).getElementsByTag("p").first().text().contains("豆瓣")) {
                        temp = doc.getElementById("outer").child(5).child(0).child(i).child(1).toString();
                        break;
                    }
                } catch(Exception j){
                }
            }
        }
        //获取海报图片URL以及描述
        //先找到描述区



        try {
            rawHTML = temp+name.toString().replaceAll("img","data");
        } catch (Exception e) {
            rawHTML = temp+name.toString();
        }
    }
    public static String getRawHTML(){return rawHTML;}
}
