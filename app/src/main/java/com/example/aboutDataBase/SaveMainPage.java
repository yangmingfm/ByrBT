package com.example.aboutDataBase;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.aboutHTML.TorrentItem;
import com.example.aboutHTML.TorrentItemA;
import com.example.aboutHTML.TorrentList;
import com.example.aboutHTML.TorrentListA;

/*
 * 用于把种子列表存入数据库
 * 输入为一个torrentList
 * 输出也为一个torrentlList
 * 可以改造 用于继承 凡是输入torrentList 都可以用它进行插入指定数据库
 */
public class SaveMainPage {
    private String tableName;
    private String tableNameA;
    private String tag = "===SaveTorrentList===";
    private TorrentList aTorrentList;
    private TorrentListA aTorrentListA;
    private TorrentItem aItem;
    private SQLiteDatabase db;
    private int Num= 50;
    private boolean getSuccess = false;
    private boolean updateSuccess =false;
    public SaveMainPage(Context context,String tableName,String tableNameA){
        this.tableName = tableName;
        this.tableNameA  = tableNameA;
        db = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().toString()+"/my.db3",null);
        this.aTorrentList = new TorrentList();
        this.aItem = new TorrentItem();
    }
    public void startSave(TorrentList inputList ) {
        //尝试插入 若无法插入则 建立一个空表 并插入空白数据
        if(inputList!=null) {
            try {
                for (int i = 0; i <inputList.getSize(); i++) {
                    int j=i+1;
                    aItem = inputList.getItem(i);
                    updateData(db,
                            aItem.getTitle(),
                            aItem.getDecs(),
                            aItem.getDate(),
                            aItem.getFinishNum(),
                            aItem.getDetailUrl(),
                            aItem.getAuthor(),
                            aItem.getSize(),
                            aItem.getPrize(),
                            "" + j);
                }
            } catch (SQLiteException se) {
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
                "finishnum VARCHAR(10),"+
                "detailurl VARCHAR(100),"+
                "author VARCHAR(32),"+
                "size VARCHAR(10),"+
                "prize VARCHAR(20))";
        String sqlA = "create table "+tableNameA+"(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title VARCHAR(100)," +
                "decs VARCHAR(1024) ,"+
                "date VARCHAR(50),"+
                "finishnum VARCHAR(10),"+
                "detailurl VARCHAR(100),"+
                "author VARCHAR(32),"+
                "size VARCHAR(10),"+
                "prize VARCHAR(20))";
        try {
            db.execSQL(sql);
            db.execSQL(sqlA);
        }
        catch (Exception e){
        }
        for(int i=0;i<11;i++)
        {
            String title =""+i;
            insertData(db, "", "", "", "", "",
                    "", "", "");
        }
        for (int i=0;i<5;i++) {
            String title = "" + i;
            insertDataA(db, "", "", "", "", "",
                    "", "", "");
        }
    }
    //插入TorrrentList数据表
    public void updateData(SQLiteDatabase db,String title,String decs,String date,String finishnum,String detailurl,String author,String size,String prize,String id){
        try {
            db.execSQL("update "+tableName+" set title=?,decs=?,date=?,finishnum=?,detailurl=?,author=?,size=?,prize=? where id =?",
                    new String[]{title, decs, date, finishnum, detailurl, author, size, prize,id});
        }
        catch (SQLiteException se)
        {
        }
    }
    public void updateDataA(SQLiteDatabase db,String title,String decs,String date,String finishnum,String detailurl,String author,String size,String prize,String id){
        try {
            db.execSQL("update "+tableNameA+" set title=?,decs=?,date=?,finishnum=?,detailurl=?,author=?,size=?,prize=? where id =?",
                    new String[]{title, decs, date, finishnum, detailurl, author, size, prize,id});
        }
        catch (SQLiteException se)
        {
        }
    }
    public TorrentList getTorrentList(){
        Cursor cursor =null;
        try{
            cursor = db.rawQuery("select * from "+tableName, null);
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
                columnIndex = cursor.getColumnIndex("id");
                this.aTorrentList.addTorrent(mItem);
                i++;
            } while (cursor.moveToNext());
            return this.aTorrentList;
        }

        catch (SQLiteException se) {
            createTable();
            TorrentList mData;
            mData = new TorrentList();
            aItem = new TorrentItem();
            aItem.setTitle("......");
            mData.addTorrent(aItem);
            return  mData;
        }
    }
    public TorrentListA getTorrentListA(){
        Cursor cursor =null;
        try{
            cursor = db.rawQuery("select * from "+tableName, null);
            cursor.moveToFirst();
            int i = 0;
            do {
                TorrentItemA mItem = new TorrentItemA();
                int columnIndex = cursor.getColumnIndex("title");
                mItem.setTitle(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("decs");
                mItem.setDecs(cursor.getString(columnIndex));
                columnIndex = cursor.getColumnIndex("date");
                mItem.setDate(cursor.getString(columnIndex));
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
                columnIndex = cursor.getColumnIndex("id");
                this.aTorrentListA.addTorrent(mItem);
                i++;
            } while (cursor.moveToNext());
            return this.aTorrentListA;
        }

        catch (SQLiteException se) {
            createTable();
            TorrentListA mDataA;
            TorrentItemA aItemA = new TorrentItemA();
            mDataA = new TorrentListA();
            aItemA.setTitle("......");
            mDataA.addTorrent(aItemA);
            return  mDataA;
        }
    }


    //插入数据库
    public void insertData(SQLiteDatabase db,String title,String decs,String date,String finishnum,String detailurl,String author,String size,String prize){
        try {
            db.execSQL("insert into "+tableName+" values(null,?,?,?,?,?,?,?,?)",
                    new String[]{title, decs, date, finishnum, detailurl, author, size, prize});
        }
        catch (SQLiteException se)
        {
        }
    }
    public void insertDataA(SQLiteDatabase db,String title,String decs,String date,String finishnum,String detailurl,String author,String size,String prize){
        try {
            db.execSQL("insert into "+tableNameA+" values(null,?,?,?,?,?,?,?,?)",
                    new String[]{title, decs, date, finishnum, detailurl, author, size, prize});
        }
        catch (SQLiteException se)
        {
        }
    }
    public boolean isGetSuccess(){return this.getSuccess;}
    public boolean isUpdateSuccess(){return this.updateSuccess;}

}
