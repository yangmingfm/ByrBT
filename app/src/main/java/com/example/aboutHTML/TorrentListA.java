package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */
/*
 * 该类继承torretItem
 * 特点是 这个ITEM不仅带有题目，描述等BT列表的页面信息也包括了海报的URL
 */

        import java.util.ArrayList;
        import java.util.List;

/*
 * 该类与torrentList类似  感觉可以 将两个用泛型的方式改进这个类
 * 特点是 能够 建立torrentItemA 对象列表
 */
public class TorrentListA {
    private List<TorrentItemA> list;
    public TorrentListA(){
        list = new ArrayList<TorrentItemA>();
    }
    //添加种子
    public void addTorrent(TorrentItemA item){
        list.add(item);
    }
    //返回第i个种子
    public TorrentItemA getItem(int i){
        return list.get(i);
    }
    public List<TorrentItemA> getList(){
        return this.list;
    }
    public TorrentItemA getLastItem(){
        if(!list.isEmpty())
        {
            return list.get(list.size()-1);
        }
        else
            return null;
    }
    public int getSize(){
        return list.size();
    }
}


