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
public class AnalyzeDetail {
    private TorrentDetail aDetail;
    public AnalyzeDetail(){
        aDetail = new TorrentDetail();
    }
    public TorrentDetail getDetail(){
        return this.aDetail;
    }
    public void startAnalyze(String strHTML){
        Element name;
        Elements name1;
        Element name2;
        //	Elements name3;
        //	user aUser = new user();
        Document doc = Jsoup.parse(strHTML);
        //获取题目(这样获取的题目会有一个<b></b>的标签
        name=doc.getElementById("share");
        aDetail.setTitle(name.text());
        //获取种子上传时间(改进版)
        name = doc.getElementById("outer");
        name2 = name.child(1).child(0).child(0).child(1).child(3);
        //获取海报图片URL以及描述
        //先找到描述区
        name = doc.getElementById("outer").child(1).child(0).child(7);
        //获取各种图片
        name1 = name.getElementsByTag("img");
        //String temp = "img size";
        //temp = name1.size()+""+"img size";
        //注意 下列循环不用考虑是否有图 前两个图 必有 之后的才为themeURL decsURL
        for(int i=1;i<name1.size();i++)
        {
            name2 = name1.get(i);
            if(i==1)
            {
                aDetail.setThemeImgUrl(name2.attr("src"));
            }
            else
            {
                aDetail.addImgUrl(name2.attr("src"));
            }
        }
        //获取描述
        for(int i=0;i<name.child(1).getAllElements().size();i++)
        {
            String temp = name.child(1).getAllElements().get(i).ownText();
            this.aDetail.addDecs( name.child(1).getAllElements().get(i).ownText());
        }
        // 获取描述
//		name1 = name.children().not("img");
//		for(int i=0;i<name1.size();i++)
//		{
//				name2 = name1;
//				Log.e(TAG,"===DECS===");
//				Log.e(TAG,name.text());
//				aDetail.setDecs(name.text());
//		}
//		Log.e(TAG,"===Show Main===");
//		Log.e(TAG,doc.getElementById("outer").getElementsByClass("main").toString());
//		if(doc.getElementById("outer").hasClass("main"))
//		{
//			Log.e(TAG,"===has comment===");
//			name = doc.getElementById("outer").getElementsByClass("text").get(0);
//			for(int i=0;i<name.children().size();i+=2)
//			{
//			//int i=0;
//				//提取用户的用户名
//				name2 = name.child(i).getElementsByTag("b").get(0);//
//				Log.e(TAG,"===UsernName===");
//				Log.e(TAG,name2.text());
//				aUser.setUserName(name2.text());
//				//提取用户的NOTE
//				name2 = name.child(i).getElementsByTag("b").get(1);//
//				Log.e(TAG,"===UserNote===");
//				Log.e(TAG,name2.text());
//				aUser.setUserNote(name2.text());
//				//提取用户的评论
//				name2 = name.child(i+1).getElementsByClass("rowfollow").get(1);
//				Log.e(TAG,"===userComment===");
//				Log.e(TAG,name2.text());
//				aUser.setUserComment(name2.text());
//
//				//提取用户的头像
//				name2 = name.child(i+1).getElementsByClass("rowfollow").get(0);
//				name1 = name2.getElementsByTag("img");
//				Log.e(TAG,"===userPic===");
//				Log.e(TAG,name1.attr("src"));
//				aUser.setUserPic(name1.attr("src"));
//
//				//提取用户的评论时间
//				try
//				{
//					name2 = name.child(i).getElementsByTag("span").get(2);
//					Log.e(TAG,"===commnent time===");
//					Log.e(TAG,name2.text());
//					aUser.setCommentTime(name2.text());
//					aDetail.getUserList().addUser(aUser);
//				}
//				catch (IndexOutOfBoundsException ie)
//				{
//					name2 = name.child(i).getElementsByTag("span").get(1);
//					Log.e(TAG,"===commnent time===");
//					Log.e(TAG,name2.text());
//					aUser.setCommentTime(name2.text());
//					aDetail.getUserList().addUser(aUser);
//				}
//			}
//		}
    }
}
