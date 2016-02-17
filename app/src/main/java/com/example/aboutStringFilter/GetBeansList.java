package com.example.aboutStringFilter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymjkMaster on 2015/6/5 0005.
 */
public class GetBeansList {
    public static List<Integer> convertStr2List(String mStr) {
        String[] temp1 = mStr.split("\\&");
        List<Integer> mList = new ArrayList<Integer>();
        for (int i = 1; i < temp1.length; i++) {
            int m = Integer.parseInt(temp1[i]);
            mList.add(m);
        }
        return  mList;
    }
}
