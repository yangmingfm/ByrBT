package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */
/*
 * 保存一个用户的信息
 */
public class User {
    private String userName;
    private String userComment;
    private String commentTime;
    private String userPicUrl;
    private String userNote;
    public void setUserNote(String note){
        this.userNote=note;
    }
    public void setUserPic(String userPicUrl){
        this.userPicUrl=userPicUrl;
    }
    public void setUserName(String name){
        this.userName=name;
    }
    public void setUserComment(String comment){
        this.userComment=comment;
    }
    public void setCommentTime(String time){
        this.commentTime= time;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getUserComment(){
        return this.userComment;
    }
    public String getCommnetTime(){
        return this.commentTime;
    }
    public String getUserNote(){
        return this.userNote;
    }
    public String getUserPicUrl(){
        return this.userPicUrl;
    }
}
