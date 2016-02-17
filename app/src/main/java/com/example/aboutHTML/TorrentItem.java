package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */


public class TorrentItem {
    //种子id
    private String torrentId;
    //种子题目
    private String title;
    //种类
    private String categories;
    //种子描述
    private String decs;
    //种子发布日期
    private String date;
    //种子发布日期距现在
    private String toNow;
    //种子完成数
    private String finishNum;
    //该种子细节的地址
    private String detailUrl;
    //种子上传者
    private String author;
    //种子文件大小
    private String Size;
    //种子做钟数
    private String UpNum;
    //种子折扣情况,免费、半价等等
    private String prize;
    public String getDate(){
        return this.date;
    }
    public String getPrize(){
        return this.prize;
    }
    public String getUpNum(){
        return this.UpNum;
    }
    public String getSize(){
        return this.Size;
    }
    public String getTitle(){
        return this.title;
    }
    public String getCategory(){
        return this.categories;
    }
    public String getDecs(){
        return this.decs;
    }
    public String getToNow(){
        return this.toNow;
    }
    public String getFinishNum(){
        return this.finishNum;
    }
    public String getDetailUrl(){
        return this.detailUrl;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setCategory(String category){
        this.categories=category;
    }
    public void setDecs(String Decs){
        if(Decs==null) {
            this.decs="";
        }
        else {
            this.decs = Decs;
        }
    }
    public void setToNow(String toNow){
        this.toNow=toNow;
    }
    public void setFinishNum(String num){
        if(num==null) {
            this.finishNum="0";
        }
        else{
            this.finishNum = num;
        }
    }
    public void setDetailUrl(String URL){
        this.detailUrl=URL;
    }
    public void setAuthor(String author){
        if(author==null)
        {
            this.author="匿名";
        }
        else {
            this.author = author;
        }
    }
    public void setSize(String Size){this.Size=Size;}
    public void setUpNum(String num){
        if(num==null)
        {
            this.UpNum="0";
        }
        else {
            this.UpNum = num;
        }
    }
    public void setPrize(String prize){
        this.prize = prize;
    }
    public void setDate(String date){
        this.date = date;
    }


    //半角转全角字符
//	public static String ToDBC(String input) {
//		   char[] c = input.toCharArray();
//		   for (int i = 0; i< c.length; i++) {
//		       if (c[i] == 12288) {
//		         c[i] = (char) 32;
//		         continue;
//		       }if (c[i]> 65280&& c[i]< 65375)
//		          c[i] = (char) (c[i] - 65248);
//		       }
//		   return new String(c);
//		}
}

