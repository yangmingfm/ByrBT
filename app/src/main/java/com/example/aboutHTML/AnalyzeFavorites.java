package com.example.aboutHTML;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by ymjkMaster on 2015/5/31 0031.
 */
public class AnalyzeFavorites {
    public TorrentList strartAnalyze(String strHTML){
        TorrentList mList = new TorrentList();
        Elements name;
        Element name1;
        Element name2;
        Document doc = Jsoup.parse(strHTML);
        try {
            name = doc.getElementsByClass("torrents").first().child(2).children();
            for (int i = 1; i < name.size() && i < 50; i++) {
                //提取种类
                TorrentItem mItem = new TorrentItem();
                name1 = name.get(i);
                mItem.setCategory(name1.child(0).getElementsByTag("img").attr("title"));
                name2 = name1.child(1).getElementsByClass("embedded").first().getElementsByTag("a").first();

                mItem.setTitle(name2.attr("title"));
                mItem.setDecs(name1.child(1).getElementsByClass("embedded").first().ownText());
                mItem.setDetailUrl("http://bt.byr.cn/" + name2.attr("href"));

                name2 = name1.child(3).getElementsByTag("span").first();
                mItem.setDate(name2.attr("title"));
                mItem.setToNow(name2.ownText());

                mItem.setSize(name1.child(4).ownText());


                mItem.setUpNum(name1.child(5).getElementsByTag("a").text());

                mItem.setFinishNum(name1.child(7).getElementsByTag("b").text());

                mItem.setAuthor(name1.child(8).getElementsByTag("i").text());

                mList.addTorrent(mItem);
            }
        }
        catch (Exception e){
            TorrentItem aItem = new TorrentItem();
            aItem.setTitle("");
            mList.addTorrent(aItem);
        }
        return mList;
    }
}
