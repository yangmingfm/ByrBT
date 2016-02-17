package com.example.aboutStringFilter;

/**
 * Created by ymjkMaster on 2015/6/7 0007.
 */
public class TitleFilter {
    //textview 的显示要注意数字与中文标点后面的汉字,否则后面的汉字会和前面视为一体
    //用正则匹配给所有“ ]” 后面加上空格即可
    public static String titleFilter(String str){
            try{
                return str.replaceAll("\\]", "]  ");
            }catch (Exception e){
                return str;
            }
        }
}
