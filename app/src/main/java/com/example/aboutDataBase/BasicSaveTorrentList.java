package com.example.aboutDataBase;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.aboutHTML.TorrentItem;
import com.example.aboutHTML.TorrentList;

/*
 * 用于把种子列表存入数据库
 * 输入为一个torrentList
 * 输出也为一个torrentlList
 * 可以改造 用于继承 凡是输入torrentList 都可以用它进行插入指定数据库
 */
public class BasicSaveTorrentList {
    private String tableName;
//    private String tag = "===SaveTorrentList===";
    private TorrentList aTorrentList;
    private TorrentItem aItem;
    private SQLiteDatabase db;
    private int Num= 50;
    private boolean getSuccess = false;
    private boolean updateSuccess =false;
    public BasicSaveTorrentList(Context context,String tableName){
        this.tableName = tableName;
        db = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().toString()+"/my.db3",null);
        this.aTorrentList = new TorrentList();
        this.aItem = new TorrentItem();
    }
    public void startSave(TorrentList inputList ) {
        //尝试插入 若无法插入则 建立一个空表 并插入空白数据
        if(inputList!=null) {
            try {
                for (int i = 0; i <50; i++) {
                    int j=i+1;
                    aItem = inputList.getItem(i);
                    updateData(db,
                            aItem.getTitle(),
                            aItem.getDecs(),
                            aItem.getDate(),
                            aItem.getUpNum(),
                            aItem.getFinishNum(),
                            aItem.getDetailUrl(),
                            aItem.getAuthor(),
                            aItem.getSize(),
                            aItem.getPrize(),
                            "" + j);
                }
//                Log.e(tag, "===SaveSucessfully===");
            } catch (SQLiteException se) {
//                Log.e(tag, "插入失败");
                createTable();
                this.startSave(inputList);
            }
        }
    }
    //每次插入NUM个数据 删除后面NUM个数据

    public void createTable(){
        String sql = "create table "+tableName+"(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title VARCHAR(100)," +
                "decs VARCHAR(1024) ,"+
                "date VARCHAR(50),"+
                "upnum VARCHAR(10),"+
                "finishnum VARCHAR(10),"+
                "detailurl VARCHAR(100),"+
                "author VARCHAR(32),"+
                "size VARCHAR(10),"+
                "prize VARCHAR(20))";
        try {
            db.execSQL(sql);
        }
        catch (Exception e){
//            Log.e(tag,"===DataBaseCreateFailure===");
        }
//        Log.e(tag, "===创建数据库成功===");
        for(int i=0;i<50;i++)
        {
            String title =""+i;
            insertData(db, "", "", "", "", "","",
                    "", "", "");
        }
//        Log.e(tag,"===CreateSuccess===");
    }
    //插入TorrrentList数据表
    public void updateData(SQLiteDatabase db,String title,String decs,String date,String upnum,String finishnum,String detailurl,String author,String size,String prize,String id){
        try {
            db.execSQL("update "+tableName+" set title=?,decs=?,date=?,upnum=?,finishnum=?,detailurl=?,author=?,size=?,prize=? where id =?",
                    new String[]{title, decs, date, upnum,finishnum, detailurl, author, size, prize,id});
        }
        catch (SQLiteException se)
        {
//            Log.e(tag,"===InsertFail===");
        }
    }
    public TorrentList getTorrentList(){
        Cursor cursor =null;
        try{
            cursor = db.rawQuery("select * from "+tableName, null);
//            Log.e(tag, "===BeginFetchData===");
            cursor.moveToFirst();
            int i = 0;
            do {
                TorrentItem mItem = new TorrentItem();
                int columnIndex = cursor.getColumnIndex("title");
                mItem.setTitle(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("decs");
                mItem.setDecs(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("date");
                mItem.setDate(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("upnum");
                mItem.setUpNum(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("finishnum");
                mItem.setFinishNum(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("detailurl");
                mItem.setDetailUrl(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("author");
                mItem.setAuthor(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("size");
                mItem.setSize(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("prize");
                mItem.setPrize(cursor.getString(columnIndex));
                this.aTorrentList.addTorrent(mItem);
                i++;
            } while (cursor.moveToNext()&&i<50);
            cursor.close();
            return this.aTorrentList;
        }
        catch (Exception se) {
//            Log.e(tag,"===FetchItemFail===");
            createTable();
            TorrentList mData;
            mData = new TorrentList();
            aItem = new TorrentItem();
            aItem.setTitle("");
            for(int i=0;i<50;i++)
            {
                mData.addTorrent(aItem);
            }
            return  mData;
        }
    }


    //插入数据库
    public void insertData(SQLiteDatabase db,String title,String decs,String date,String upnum,String finishnum,String detailurl,String author,String size,String prize){
        try {
            db.execSQL("insert into "+tableName+" values(null,?,?,?,?,?,?,?,?,?)",
                    new String[]{title, decs, date, upnum,finishnum, detailurl, author, size, prize});
        }
        catch (SQLiteException se)
        {
//            Log.e(tag,"===InsertFail===");
        }
    }
    public boolean isGetSuccess(){return this.getSuccess;}
    public boolean isUpdateSuccess(){return this.updateSuccess;}

}
