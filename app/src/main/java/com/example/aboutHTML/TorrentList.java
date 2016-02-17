package com.example.aboutHTML;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

        import java.util.ArrayList;
        import java.util.List;

public class TorrentList    {
    private List<TorrentItem> list;
    public TorrentList(){
        list = new ArrayList<TorrentItem>();
    }
    //添加种子
    public void addTorrent(TorrentItem item){
        list.add(item);
    }
    //返回第i个种子
    public TorrentItem getItem(int i){
        return list.get(i);
    }
    public List<TorrentItem> getList(){
        return this.list;
    }
    public TorrentItem getLastItem(){
        if(!list.isEmpty())
        {
            return this.getItem(list.size()-1);
        }
        else
            //返回一个空对象，不知道这样是否可行
            return null;
    }
    public int getSize(){
        return list.size();
    }

    public void updateItem(TorrentItem aItem,int position){
        list.set(position,aItem);
    }

    public void removeItem(int position){list.remove(position);}

}

