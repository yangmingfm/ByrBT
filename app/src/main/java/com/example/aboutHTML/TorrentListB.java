package com.example.aboutHTML;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymjkMaster on 2015/6/15 0015.
 */
public class TorrentListB {
    private List<TorrentItemB> mList;
    public TorrentListB(){
        mList = new ArrayList<TorrentItemB>();
    }
    public void addTorrentItem(TorrentItemB aItem){
        mList.add(aItem);
    }
    public int size(){
        return mList.size();
    }
    public TorrentItemB getItem(int position){
        return mList.get(position);
    }
    public List<TorrentItemB> getList(){
        return mList;
    }
}
