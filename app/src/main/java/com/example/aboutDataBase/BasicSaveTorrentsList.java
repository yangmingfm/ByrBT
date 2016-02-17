//package com.example.aboutDataBase;
//
///**
// * Created by ymjkMaster on 2015/5/21 0021.
// */
//
//        import android.content.Context;
//        import android.database.Cursor;
//        import android.database.sqlite.SQLiteDatabase;
//        import android.database.sqlite.SQLiteException;
//        import android.util.Log;
//
//        import com.example.aboutHTML.TorrentItem;
//        import com.example.aboutHTML.TorrentList;
//
///*
// * 该类为基础类 用时必须继承之后 再 继承合适的接口用于初始化响应的sql语句,
// * 注意fetchItem方法一般需要重写
// * 输入为一个TorrentList、sqlCreate用于建立数据表 、sqlDelete用于删除后面的多余项
// * 输出也为一个torrentlList
// * 可以改造 用于继承 凡是输入TorrentList 都可以用它进行插入指定数据库
// * 插入一个Item的时候要注意判断是否库里是否已经存在若已存在则不用插入或者更新
// */
//public abstract  class BasicSaveTorrentsList{
//    private String TAG = "===Look===";
//    //用于保存去处的数据
//    private TorrentList aTorrentList;
//    //用于暂时保存暂时取出的Item 用于插入数据表
//    private TorrentItem aItem;
//    private SQLiteDatabase db;
//    //用于控制一次最多能够拿多少次数据
//    private String insertSql;
//    private String updateSql;
//    private String createTableSql;
//    private int defaulNum;
//    public BasicSaveTorrentsList(Context context){
//        db = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().toString()+"/my.db3",null);
//        aTorrentList = new TorrentList();
//        aItem = new TorrentItem();
//    }
//    public void startSave(TorrentList inputList ,String sqlCreate,String sqlDelete){
//        //尝试插入 若无法插入则 建立一个空表 并插入空白数据
//        try
//        {
//
//            for(int i=0;i<inputList.getSize();i++)
//            {
//                aItem = inputList.getItem(i);
//                upDateData(db,aItem);
//                Log.e(TAG,"===插入成功===");
//            }
//        }
//        catch(SQLiteException se)
//        {
//            Log.e(TAG, "插入失败");
//            createTable(sqlCreate);
//        }
//    }
//    public  void createTable(String sqlCreate){
//        db.execSQL(sqlCreate);
//        Log.e(TAG,"===创建数据库成功===");
//        for(int i=0;i<this.defaulNum;i++)
//        {
//            insertData(db,defaultItem);
//        }
//        Log.e(TAG,"===CreateSuccess===");
//    }
//    //插入TorrrentList数据表
//    public void insertData(SQLiteDatabase db,TorrentItem aTorrentItem){
//        db.execSQL("insert into test10 values(null,?,?,?,?,?,?,?,?)",
//                new String[]{aTorrentItem.getTitle(),
//                        aTorrentItem.getDecs(),
//                        aTorrentItem.getDate(),
//                        aTorrentItem.getFinishNum(),
//                        aTorrentItem.getDetailUrl(),
//                        aTorrentItem.getAuthor(),
//                        aTorrentItem.getSize(),
//                        aTorrentItem.getSize()});
//        Log.e(TAG,"===InsertSuccess===");
//    }
//    public void fetchItem(String sql){
//        Cursor cursor = db.rawQuery(sql, null);
//        Log.e("===Look===","===FetchData===");
//        cursor.moveToFirst();
//        int i=0;
//        do{
//            int columnIndex = cursor.getColumnIndex("title");
//            aItem.setTitle(cursor.getString(columnIndex));
//            columnIndex = cursor.getColumnIndex("decs");
//            aItem.setDecs(cursor.getString(columnIndex));
//            columnIndex = cursor.getColumnIndex("date");
//            aItem.setDate(cursor.getString(columnIndex));
//            columnIndex = cursor.getColumnIndex("finishnum");
//            aItem.setFinishNum(cursor.getString(columnIndex));
//            columnIndex = cursor.getColumnIndex("detailurl");
//            aItem.setDetailUrl(cursor.getString(columnIndex));
//            columnIndex = cursor.getColumnIndex("author");
//            aItem.setAuthor(cursor.getString(columnIndex));
//            columnIndex = cursor.getColumnIndex("size");
//            aItem.setSize(cursor.getString(columnIndex));
//            columnIndex = cursor.getColumnIndex("prize");
//            aItem.setPrize(cursor.getString(columnIndex));
//            columnIndex = cursor.getColumnIndex("id");
//            Log.e(TAG,""+cursor.getInt(columnIndex));
//            aTorrentList.addTorrent(aItem);
//            i++;
//        }while(cursor.moveToNext());
//        Log.e(TAG,"===total:"+i+"===");
//    }
//    abstract  public TorrentList getResultList();
//}
