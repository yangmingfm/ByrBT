//package com.example.fragment2more;
//
///**
// * Created by ymjkMaster on 2015/5/21 0021.
// */
//
//import com.example.aboutDataBase.BasicSaveTorrentList;
//import com.example.aboutHTML.TorrentItem;
//import com.example.aboutHTML.TorrentList;
//import com.example.fragment2more.RecyclerViewAdapter.OnItemClickListener;
//import com.example.getData.GetLocalData;
//import com.example.getData.GetTotalListInternet;
//import com.example.aboutDataBase.SaveTorrentsList;
//import com.example.ymjkmaster.byrbta.R;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import static java.lang.Thread.sleep;
//
//
//public class Fragment6 extends Fragment {
//    private RecyclerView  mRecyclerView;
//    private RecyclerViewAdapter mAdapter;
//    private SwipeRefreshLayout mSwipeRefreshLayout;
//    private TorrentList mData  =null;
//    private BasicSaveTorrentList torrentsListDB;
//    private String tag = "===Fragment2===";
//    private String strCookie;
//    //可以尝试调用一个setList,之后通知更新，感觉每次更新都要重绘比较麻烦,尤其是在加载下一页的时候
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg)
//        {
//            if(msg.what==0x666)
//            {
//                mAdapter = new RecyclerViewAdapter(getActivity(), mData,"");
//                mRecyclerView.setAdapter(mAdapter);
//                mSwipeRefreshLayout.setRefreshing(false);
//                mAdapter.setOnItemClickLitener(new MyItemClickListener());
//                torrentsListDB.startSave(mData);
//                Log.e(tag,"===StartSaveData===");
//                for(int i=0;i<mData.getSize();i++)
//                {
//                    Log.e(tag,"===position:"+i+" Data: "+mData.getItem(i).getTitle());
//                }
//            }
//            else if(msg.what==0x333)
//            {
//                Toast.makeText(getActivity(),"网络不良~~",Toast.LENGTH_SHORT).show();
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        }
//    };
//    public void onAttach(android.app.Activity activity) {
//        super.onAttach(activity);
//    };
//    public void onCreate(Bundle savedInstanceState) {
//        //从该方法拿上次取到的数据，若取不到，则从网络获取，并在handler更新
//        //这里应该个一个空的item给torrentList 否则 接口适配器会出错
//        this.mData = new TorrentList();
//        torrentsListDB = new BasicSaveTorrentList(getActivity(),"testMovie");
//        TorrentItem aItem = new TorrentItem();
//        this.mData.addTorrent(aItem);
//        this.mData = torrentsListDB.getTorrentList();
//        // TODO Auto-generated catch block
////            Log.e("===Fragment===", "===FailedToGetList====");
////            Log.e("===Fragment===","===BeginToGetFromInternet====");
//        if (this.mData.getItem(0).getTitle().equals(null))
//        {
//            runRefresh();
//        }
//        Log.e("===Fragment===","===SuccessToGetList====");
//
//        //初始化cookie
//        strCookie = GetLocalData.getCookie(getActivity());
//        super.onCreate(savedInstanceState);
//    };
//
//    public View onCreateView(LayoutInflater inflater,
//                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//
//
//        View view = inflater.inflate(R.layout.fragment2, container,false);
//        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
//        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // TODO Auto-generated method stub
//                runRefresh();
//            }
//        });
//
//        //设置线性布局管理器
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        //初始化适配器 注意context参数 以后研究传入fragment
//        mAdapter = new RecyclerViewAdapter(getActivity(), this.mData,"");
//        mAdapter.setOnItemClickLitener(new MyItemClickListener());
//        mRecyclerView.setAdapter(mAdapter);
//
//        return view;
//    }
//
//
//    public void runRefresh() {
//
//        new Thread() {
//            @Override
//            public void run() {
//                Message msg = new Message();
//                Log.e(tag, "===RunRefresh===");
//                GetTotalListInternet.startGet("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=408&page=",strCookie,"0");
//                if(GetTotalListInternet.isGetSuccess())
//
//                {
//                    mData = GetTotalListInternet.getTorrentList();
//                    msg.what = 0x666;
//                    handler.sendEmptyMessage(msg.what);
//                }
//                else
//                {
//                    msg.what = 0x333;
//                    handler.sendEmptyMessage(msg.what);
//                }
//                super.run();
//            }
//        }.start();
//    }
//    @Override
//    public void onDestroyView() {
//        Log.e(tag,"===OnDestroyView===");
//        super.onDestroyView();
//    }
//
//    @Override
//    public void onDestroy() {
//        Log.e(tag,"===OnDestroy===");
//        super.onDestroy();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
////        torrentsListDB = new BasicSaveTorrentList(getActivity(),"testMoview");
////        torrentsListDB.startSave(mData);
//        super.onSaveInstanceState(outState);
//    }
//    public class MyItemClickListener implements OnItemClickListener
//    {
//        @Override
//        public void onItemClick (View view,int i){
//            // TODO Auto-generated method stub
//            Bundle bundle = new Bundle();
//            bundle.putString("detailUrl", mData.getItem(i).getDetailUrl());
//            Intent intent = new Intent(getActivity(), DetailActivity.class);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        }
//    }
//}
//
//
