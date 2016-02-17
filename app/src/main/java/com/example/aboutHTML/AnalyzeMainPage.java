package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;


/*
 * 分析BT首页 输入为首页HTML
 * 提取 11个热门种子、竞价置顶种子"图片"、"标题"、“细节地址” 还有 编辑部推荐种子的“图片”和 “描述”
 *注意竞价种子是没有decs的。
 */
public class AnalyzeMainPage {
    private TorrentListA hotTorrents;
    //编辑部种子列表
    private TorrentListA editorRec;
    private TorrentList priceTorrents;
    public TorrentListA getHot(){return hotTorrents;}
    public TorrentListA getEditor(){
        return editorRec;
    }
    public TorrentList getPriceTorrents(){
        return priceTorrents;
    }
    public void startAnaylze(String strHTML){
        hotTorrents = new TorrentListA();
        editorRec = new TorrentListA();
        priceTorrents = new TorrentList();
        Element name;
        Elements  name1;
        Document doc = Jsoup.parse(strHTML);
        //name1 位热门种子列表
        name1= doc.getElementsByClass("mainouter").get(0).child(0).child(1).getElementsByClass("embedded").get(0).child(3).child(0).child(0).child(0).children();
        for(int i=0;i<name1.size();i++)
        {
            try {
                TorrentItemA aItem = new TorrentItemA();
                //设置海报URL
                name = name1.get(i);
                aItem.setThemeImg("http://bt.byr.cn" + name.getElementsByTag("img").first().attr("src"));
                //设置detailUrl
                aItem.setDetailUrl("http://bt.byr.cn/" + name.attr("href"));
                String STRname = name.attr("onmouseover").toString();
                Document temp = Jsoup.parse(STRname);
                STRname = temp.getElementsByTag("b").text();
                aItem.setTitle(STRname);
                STRname = "";
                for (int j = 1; j < temp.getElementsByTag("font").size(); j++) {
                    STRname += temp.getElementsByTag("font").get(j).ownText();
                }
                aItem.setDecs(STRname);
                hotTorrents.addTorrent(aItem);
            }catch (Exception e){
                TorrentItemA aItem = new TorrentItemA();
                aItem.setTitle("");
                hotTorrents.addTorrent(aItem);
            }
        }

        //获取竞价种子列表,注意竞价种子的数量不一定是5个
        //name1为竞价种子列表
        //注意该列没有提取种子的日期和tonow 注意该类没有decs!!!!!!!

        name1= doc.getElementById("top").child(4).child(0).child(1).getElementsByClass("embedded").get(0).child(8).child(1).children();
        for(int i=1;i<name1.size();i++) {
            name = name1.get(i);
            TorrentItem aItem1 = new TorrentItem();
            if (name.childNodeSize() > 5) {
                //获取类型
                try {
                    aItem1.setCategory(name.child(0).getElementsByTag("img").first().attr("title"));
                } catch (Exception e) {
                    e.printStackTrace();
                    aItem1.setCategory("");
                }
                //获取detail url 和 标题
                try {
                    aItem1.setDetailUrl("http://bt.byr.cn/" + name.child(1).getElementsByTag("a").first().attr("href"));
                    aItem1.setTitle(name.child(1).getElementsByTag("a").first().attr("title"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //获取文件大小
                try {
                    aItem1.setSize(name.child(3).text());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //获取上传人数
                try {
                    aItem1.setUpNum(name.child(4).getElementsByTag("a").first().text());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //获取完成数
                //注意完成人数为零的时候，会发生错误！！！
                try {
                    aItem1.setFinishNum(name.child(6).getElementsByTag("a").first().text());
                }
                catch (Exception e){
                    aItem1.setFinishNum("0");
                }
                //获取作者
                aItem1.setAuthor(name.child(7).getElementsByTag("a").first().text());
                priceTorrents.addTorrent(aItem1);
            }
        }
    }
}
