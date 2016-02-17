package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */
/*
 * 该类继承torretItem
 * 特点是 这个ITEM不仅带有题目，描述等BT列表的页面信息也包括了海报的URL
 */
public class TorrentItemA extends TorrentItem{
    private String themeImg;
    public void setThemeImg(String themeImgUrl){
        this.themeImg = themeImgUrl;
    }
    public String getThemeImgUrl(){
        return this.themeImg;
    }
}

