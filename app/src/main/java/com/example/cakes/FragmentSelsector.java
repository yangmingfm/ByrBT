package com.example.cakes;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymjkMaster on 2015/6/4 0004.
 */
public class FragmentSelsector {
    private List<Integer> mList;
    public FragmentSelsector(){mList = new ArrayList<Integer>();}
    public void addBean(int i){mList.add(i);}
    public void removeBean(int m) {
        for(int i=(mList.size()-1);i>=0;i--){
            if(mList.get(i)==m) {
                mList.remove(i);
                break;
            }
        }
    }
    public List<Integer> getList(){return this.mList;}
    public void clearBeans(){mList.clear();}
}
