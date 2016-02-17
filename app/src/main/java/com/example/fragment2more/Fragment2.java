package com.example.fragment2more;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */

        import com.example.aboutDataBase.BasicSaveTorrentList;
        import com.example.aboutHTML.TorrentList;
        import com.example.aboutView.MyMainActivityScrollView;
        import com.example.cakes.DP_DX;
        import com.example.fragment2more.RecyclerViewAdapter.OnItemClickListener;
        import com.example.getData.GetLocalData;
        import com.example.getData.GetTotalListInternet;
        import com.example.ymjkmaster.byrbta.R;

        import android.animation.ObjectAnimator;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.widget.SwipeRefreshLayout;
        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;



public class Fragment2 extends Fragment {
    private LinearLayoutManager linearLayoutManager;
    private String headerStr;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TorrentList mData = null;
    private BasicSaveTorrentList torrentsListDB;
    private String strCookie;
    private String tableName;
    private String url;
    private MyMainActivityScrollView mScrollView;
    private int currentPage = 0;
    private int newPage = 0;
    //可以尝试调用一个setList,之后通知更新，感觉每次更新都要重绘比较麻烦,尤其是在加载下一页的时候
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x666) {
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
                torrentsListDB.startSave(mData);
            } else if (msg.what == 0x333) {
                Toast.makeText(getActivity(), "网络不良~~", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    };

    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
    }

    ;

    public void onCreate(Bundle savedInstanceState) {
        //从该方法拿上次取到的数据，若取不到，则从网络获取，并在handler更新
        //这里应该个一个空的item给torrentList 否则 接口适配器会出错
        this.mData = new TorrentList();
        torrentsListDB = new BasicSaveTorrentList(getActivity(), tableName);
        // TODO Auto-generated catch block
//        Log.e("===Fragment===", "===SuccessToGetList====");
        //初始化cookie
        strCookie = GetLocalData.getCookie(getActivity());
        mData = torrentsListDB.getTorrentList();
        super.onCreate(savedInstanceState);
    }

    ;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (this.mData.getItem(0).getTitle().equals(null) || this.mData.getItem(0).getTitle().equals("")) {
            runRefresh();
        }

        View view = inflater.inflate(R.layout.fragment2, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setProgressViewOffset(false,DP_DX.dip2px(getActivity(),80),DP_DX.dip2px(getActivity(),150));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                runRefresh();
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(0xff009486,0xff009486,0xff009486);
        //设置线性布局管理器
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setOnScrollListener(new MyScrollListener());
        //初始化适配器 注意context参数 以后研究传入fragment
        mAdapter = new RecyclerViewAdapter(getActivity(), this.mData,headerStr);
        mAdapter.setOnItemClickLitener(new MyItemClickListener());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


    public void runRefresh() {
        new Thread() {
            @Override
            public void run() {
                Message msg = new Message();
                GetTotalListInternet.startGet(url, strCookie, ""+newPage);
                if (GetTotalListInternet.isGetSuccess())
                {
                    TorrentList newData = GetTotalListInternet.getTorrentList();
                    addList2List(newData);
                    msg.what = 0x666;
                    handler.sendEmptyMessage(msg.what);
                } else {
                    msg.what = 0x333;
                    handler.sendEmptyMessage(msg.what);
                }
                super.run();
            }
        }.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        handler = null;
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        torrentsListDB = new BasicSaveTorrentList(getActivity(), tableName);
//        torrentsListDB.startSave(mData);
        super.onSaveInstanceState(outState);
    }
    public class MyItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(View view, int i) {
            // TODO Auto-generated method stub
            i=i-1;
            Bundle bundle = new Bundle();
            bundle.putString("detailUrl", mData.getItem(i).getDetailUrl());
            bundle.putString("upNum", mData.getItem(i).getUpNum());
            bundle.putString("finishNum", mData.getItem(i).getFinishNum());
            bundle.putString("size", mData.getItem(i).getSize());
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public class MyScrollListener extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;
        private boolean isIdle;
        private int mScrollY;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            isIdle = newState == RecyclerView.SCROLL_STATE_IDLE;
            if (isIdle) {
                mScrollY = 0;
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem +1 == mAdapter.getItemCount()) {
                mSwipeRefreshLayout.setRefreshing(true);
                newPage = currentPage+1;
                runRefresh();
                // 此处在现实项目中，请换成网络请求数据代码，sendRequest
               // handler.sendEmptyMessageDelayed(0, 3000);
            }
        }
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            mScrollY += dy;
            int offset = DP_DX.dip2px(getActivity(),55);
            if(dy>80&&mScrollView.getOut==true) {
                mScrollView.getOut = false;
                mScrollView.getIn = true;
                ObjectAnimator.ofFloat(mScrollView, "translationY", 0, -offset).setDuration(200).start();
            }
            if(dy<-80&&mScrollView.getIn==true) {
                mScrollView.getIn = false;
                mScrollView.getOut = true;
                ObjectAnimator.ofFloat(mScrollView, "translationY", -offset,0 ).setDuration(200).start();
            }
        }
    }

    public void setUrl(String url) {this.url = url;}

    public void setDBTableName(String tableName) {this.tableName = tableName;}

    public void setScrollView(MyMainActivityScrollView mScrollView){this.mScrollView = mScrollView;}

    public void setHeaderStr(String headerStr){this.headerStr = headerStr;}

    public void addList2List(TorrentList newData){
        if(currentPage>=newPage)
        {
            int j = 50*newPage;
            int m = 0;
            int n = newData.getSize();
            for (int i=j;i<j+50;i++){
                mData.updateItem(newData.getItem(m),i);
                m++;
            }
        } else{
            int n = newData.getSize();
            for (int i=0;i<n;i++){
                mData.addTorrent(newData.getItem(i));
            }
            currentPage = newPage;
        }
    }


}


