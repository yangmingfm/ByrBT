package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/6/15 0015.
 */
public class TorrentItemB {
    private String detailUrl;
    private String title;
    private String imgUrl;
    private String decs;
    private String author;
    private String time;
    public void setTitle(String title){this.title = title;}
    public void setImgUrl(String imgUrl){this.imgUrl = imgUrl;}
    public void setDecs(String decs){this.decs = decs;}
    public void setAuthor(String author){this.author = author;}
    public void setTime(String time){this.time = time;}
    public void setDetailUrl(String detailUrl){this.detailUrl = detailUrl;}
    public String getTitle(){return this.title;}
    public String getImgUrl(){return this.imgUrl;}
    public String getAuthor(){return this.author;}
    public String getTime(){return this.time;}
    public String getDecs(){return this.decs;}
    public String getDetailUrl(){return this.detailUrl;}
}
