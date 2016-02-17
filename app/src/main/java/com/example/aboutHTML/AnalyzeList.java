package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */


        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;


/*
 * 解析种子列表的HTML 返回一个 torrentlist
 * 构造器 需要 String HTML
 * getList 方法 可以返回 一个list<torrentItem>
 * setStartEnd 方法可以设置遍历某个URL时的起始和终止点
 * 注意分析 date 和 toNow的区别
 */
public class AnalyzeList {
    private static TorrentList mList;
    private static int startNum=1;
    private static int endNum=50;
    //开始分析
    public static void starAnalyze(String strHTML){
            mList = null;
            mList = new TorrentList();
            Elements name;
            Element name1;
            Element name2;
            Document doc = Jsoup.parse(strHTML);
            name = doc.getElementsByClass("torrents").first().child(2).children();
            for (int i = startNum; i <= endNum; i++) {
                try {
                    TorrentItem item = new TorrentItem();
                    name1 = name.get(i);
                    //种类，表的第一列
                    item.setCategory(name1.child(0).getElementsByTag("img").attr("title"));
                    //表的第二列
                    name2 = name1.child(1).getElementsByClass("embedded").first();
                    item.setTitle(name2.getElementsByTag("a").attr("title"));
                    item.setDetailUrl("http://bt.byr.cn/" + name2.getElementsByTag("a").attr("href"));
                    item.setDecs(name2.ownText());

                    //表的第四列，upToNow,date
                    item.setDate(name1.child(3).getElementsByTag("span").first().attr("title"));
                    item.setUpNum(name1.child(3).getElementsByTag("span").first().ownText());

                    //表的第五列，size
                    item.setSize(name1.child(4).ownText());

                    //表第六列，上传数
                    item.setUpNum(name1.child(5).text());

                    //表第八列，完成数
                    item.setFinishNum(name1.child(7).text());

                    //表第九咧，作者
                    item.setAuthor(name1.child(8).text());
                    mList.addTorrent(item);
                    item = null;

                }catch (Exception e){
                    e.printStackTrace();
                    TorrentItem aItem = new TorrentItem();
                    aItem.setTitle("");
                    mList.addTorrent(aItem);
                }
            }
        }

    //返回种子List
    public static TorrentList getList (){
//        TorrentList tempList = new TorrentList();
//        tempList = mTorrentList;
//        mTorrentList = null;
        return mList;
    }
    //设置起始，终止遍历的种子数
//    public static void setStartEnd(int start, int end){
//        startNum=start;
//        endNum=end;
//    }
    //用于保存最后一次更新的种子
//    public static void saveLatest(Context context){
//        SharedPreferences preferences =context.getSharedPreferences("timeStamp", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("listTimeStamp", mList.getLastItem().getDate());
//        editor.commit();
//        preferences.getString("listTimeStamp", "0000-00-00 00:00:00");
//        Log.e(TAG,preferences.getString("listTimeStamp", "0000-00-00 00:00:00"));
//    }
}



