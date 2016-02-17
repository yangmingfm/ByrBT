package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

        import java.util.ArrayList;
        import java.util.List;
/*
 * 用于 保存 种子详细信息
 * 信息包括 ： upTime decs themeImg title userList
 * userList暂时不可用
 * 后期可以添加 豆瓣信息
 */
public class TorrentDetail {
    private List<String> imgUrlList;
    private String author;
    private String upTime;
    private List<String> decs;//描述为一个字符串列表
    private String themeImgUrl;
    private List<String> decsImgUrl;//描述图片 为字符串列表
    private String title;
    private UserList userList;
    private String subTitle;
    private String size;
    public TorrentDetail(){
        userList = new UserList();
        decs = new ArrayList<String>();
        decsImgUrl =  new ArrayList<String>();
        imgUrlList = new ArrayList<String>();
    }
    public String getUpTime(){
        return this.upTime;
    }

//    public List<String> getDecs(){
//        return this.decs;
//    }
    public String getThemeImgUrl(){
        return this.themeImgUrl;
    }
//    public List<String> getDecsImgUrl(){
//        return this.decsImgUrl;
//    }
    public String getTitle(){return this.title;}
    public String getAuthor(){return this.author;}
    public String getSubTitle(){return this.subTitle;}
    public String getSize(){return this.size;}
    public List<String> getUrlList(){return this.imgUrlList;}
    public UserList getUserList(){
        return this.userList;
    }
    public void addDecs(String decs){
        if(decs!=null)
        {
            this.decs.add(decs);
        }
    }
    public void setThemeImgUrl(String url){
        if(url!=null)
        {
            this.themeImgUrl=url;
        }
    }
//    public void addDecsImgUrl(String url){
//        if(url!=null)
//        {
//            this.decsImgUrl.add(url);
//        }
//    }
    public void setAuthor(String author){
        if(!author.equals(null)) {
            this.author = author;
        }else{
            this.author = "匿名";
        }
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setUserlist(UserList aUserList){
        this.userList=aUserList;
    }
    public void setUpTime(String time){
        this.upTime=time;
    }
    public void setSubTitle(String subTitle){this.subTitle =subTitle;}
    public void addImgUrl(String url){this.imgUrlList.add(url);}
    public void setSize(String size){this.size = size;}
}
