package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;

        import android.util.Log;
/*
 * 该类用于分析登陆页面 输入为 登陆页面的HTML可以获取登陆页面的Image和 ImageHash
 * 输入为 strHTML
 *
 */
//开始分析 注意 先设置strHTML
public class AnalyzeLandingPage {
    private String picUrl;
    private String picHash;
    public String getPicUrl(){
        return "http://bt.byr.cn/"+picUrl;
    }
    //返回 pic的 HASH值
    public String getPicHash(){
        return picHash;
    }
    //开始分析
    public void startAnalyze(String strHTML){
        Element name;
        Document doc = Jsoup.parse(strHTML);
        name = doc.getElementsByTag("img").first();
        //获取picUrl
        picUrl = name.attr("src");
        //获取 PicHash，这里 imageHash 用&符号前面的内容隔开
        String[] a = picUrl.split("\\&");
        picHash= a[1];
        picHash = picHash.replaceAll("imagehash=","");
        //尝试用正则匹配，可用 但貌似效率不太高
//		Pattern pattern = Pattern.compile("imagehash=\\w*");
//		Matcher matcher = pattern.matcher(this.picUrl);
//		//不调用find()方法就会无脑报错！！！！！！！！
//		if(matcher.find())
//		{
//			Log.e(TAG,matcher.group(0));
//		}
    }
}

