package com.example.fragment1;

/**
 * Created by ymjkMaster on 2015/5/21 0021.
 */


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aboutHTML.TorrentItem;
import com.example.aboutHTML.TorrentItemA;
import com.example.aboutHTML.TorrentList;
import com.example.aboutHTML.TorrentListA;
import com.example.aboutView.MyMainActivityScrollView;
import com.example.cakes.DP_DX;
import com.example.fragment2more.DetailActivity2;
import com.example.fragment2more.RecyclerViewAdapter2;
import com.example.getData.GetLocalData;
import com.example.getData.GetMainPageInfo;
import com.example.ymjkmaster.byrbta.R;

public class Fragment1 extends Fragment {
    private String headerStr;
    private TorrentList mData;
    private TorrentListA mDataA;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter2 mAdapter;
    private String strCookie;
    private MyMainActivityScrollView mScrollView;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what==0x666)
            {
                mAdapter = new RecyclerViewAdapter2(getActivity(), mData,mDataA,headerStr);
                mRecyclerView.setAdapter(mAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setOnItemClickLitener(new MyItemClickListener());
            }
            else if(msg.what==0x333)
            {
                Toast.makeText(getActivity(),"网络不良~~",Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    };
    public void setScrollView(MyMainActivityScrollView mScrollView){this.mScrollView = mScrollView;}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        TorrentItem aItem = new TorrentItem();
        TorrentItemA aItemA = new TorrentItemA();
        aItem.setTitle("");
        aItemA.setTitle("");
        mData = new TorrentList();
        mDataA = new TorrentListA();
        mData.addTorrent(aItem);
        mDataA.addTorrent(aItemA);

        strCookie = GetLocalData.getCookie(getActivity());
        runRefresh();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment3, container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setOnScrollListener(new MyScrollListener());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //初始化适配器 注意context参数 以后研究传入fragment
        mAdapter = new RecyclerViewAdapter2(getActivity(), this.mData,this.mDataA,headerStr);
        mAdapter.setOnItemClickLitener(new MyItemClickListener());
        mRecyclerView.setAdapter(mAdapter);
        return view;

    }
    public void runRefresh() {
        new Thread() {
            @Override
            public void run() {
                Message msg = new Message();
                GetMainPageInfo.startGet(strCookie);
                if(GetMainPageInfo.isGetSuccess())
                {
                    mData = null;
                    mDataA = null;
                    mAdapter.clearData();
                    mData = GetMainPageInfo.getPrize();
                    mDataA = GetMainPageInfo.getHot();
                    msg.what = 0x666;
                    handler.sendEmptyMessage(msg.what);
                }
                else
                {
                    msg.what = 0x333;
                    handler.sendEmptyMessage(msg.what);
                }
                super.run();
            }
        }.start();
    }
    public class MyItemClickListener implements RecyclerViewAdapter2.OnItemClickListener {
        @Override
        public void onItemClick(View view, int i) {
            i=i-1;
            // TODO Auto-generated method stub
            Bundle bundle = new Bundle();
            if(i<11) {
                bundle.putString("detailUrl", mDataA.getItem(i).getDetailUrl());
                bundle.putString("upNum", mDataA.getItem(i).getUpNum());
                bundle.putString("finishNum", mDataA.getItem(i).getFinishNum());
                bundle.putString("size", mDataA.getItem(i).getSize());
            }else{
                bundle.putString("detailUrl", mData.getItem(i-11).getDetailUrl());
                bundle.putString("upNum", mData.getItem(i-11).getUpNum());
                bundle.putString("finishNum", mData.getItem(i-11).getFinishNum());
                bundle.putString("size", mData.getItem(i-11).getSize());
            }
            Intent intent = new Intent(getActivity(), DetailActivity2.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public class MyScrollListener extends RecyclerView.OnScrollListener {
        boolean isIdle;
        int mScrollY;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            isIdle = newState == RecyclerView.SCROLL_STATE_IDLE;
            if (isIdle) {
                mScrollY = 0;
            }
        }
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mScrollY += dy;
            int offset = DP_DX.dip2px(getActivity(), 55);
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
    public void setHeaderStr(String headerStr){this.headerStr = headerStr;}
}
