package com.example.ymjkmaster.byrbta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import android.support.v7.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.astuetz.PagerSlidingTabStrip;
import com.example.Check.UpdateManager;
import com.example.aboutImage.SingleRequestQueue;
import com.example.aboutStringFilter.GetBeansList;
import com.example.aboutToolbar.MyToolbarItemOnClickListener;
import com.example.aboutView.MyMainActivityScrollView;
import com.example.fragment1.Fragment1;
import com.example.fragment2more.Fragment2;
import com.example.fragment2more.Fragment4;
import com.example.fragment2more.FragmentNewsroom;
import com.example.getData.GetLocalData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private MyViewPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private final String[] defaultTitles = { "首页", "编辑部","全部", "收藏","电影","剧集","动漫","音乐","综艺","游戏","软件","资料","体育","记录",};
    private String[] titles;
    private List<Integer> fragmentIndex;
    private PagerSlidingTabStrip mTabStrip;
    private MyMainActivityScrollView mScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UpdateManager updateManager = new UpdateManager(MainActivity.this);
        updateManager.getUpdateInfo();
                super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mScrollView = (MyMainActivityScrollView)findViewById(R.id.scroView_top);
        //对toolbar进行初始化
        mToolbar= (Toolbar)findViewById(R.id.toolbar1);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(new MyToolbarItemOnClickListener(MainActivity.this));
        mToolbar.setBackgroundColor(0xff009688);

        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mTabStrip =(PagerSlidingTabStrip )findViewById(R.id.tabstrip);

        GetLocalData getLocalData = new GetLocalData();
        if(getLocalData.getBeans(MainActivity.this)!="getFalse") {
            fragmentIndex=GetBeansList.convertStr2List(getLocalData.getBeans(MainActivity.this));
            initViewManager(fragmentIndex);
        }
        else {
            initView();
        }
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),titles,mFragments);
        mViewPager.setAdapter(mAdapter);


        mTabStrip.setShouldExpand(true);
        mTabStrip.setViewPager(mViewPager);
        mTabStrip.setTextColor(0xffffffff);
        mTabStrip.setIndicatorColor(0xffffffff);
        mTabStrip.setBackgroundColor(0xff009688);
//
//        MyPageChangeListener myPageChangeListener = new MyPageChangeListener(mTabStrip,mToolbar,mScrollView);
//        mTabStrip.setOnPageChangeListener(myPageChangeListener);


    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
//        Log.e(tag,"===OnStart===");
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
//        Log.e(tag,"===OnStop===");
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
//        Log.e(tag,"===OnResume===");
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
//        Log.e(tag,"===OnPause===");
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//        Log.e(tag,"===OnDestroy===");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void initView(){
        mQueue = SingleRequestQueue.getRequestQueue(getApplicationContext());
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment2 fragment3 = new Fragment2();
        titles = new String[]{"首页","编辑部","全部","电影","收藏"};
        fragment1.setScrollView(mScrollView);
        fragment1.setHeaderStr("首页");
        fragment2.setScrollView(mScrollView);
        FragmentNewsroom fragmentNewsroom = new FragmentNewsroom();
        fragmentNewsroom.setScrollView(mScrollView);
        fragmentNewsroom.setHeaderStr("编辑部");
        fragment2.setHeaderStr("全部");
        fragment2.setDBTableName("testTotal3");
        fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&page=");
        fragment3.setHeaderStr("电影");
        fragment3.setScrollView(mScrollView);
        fragment3.setDBTableName("testMovie3");
        fragment3.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=408&page=");
        Fragment4 fragment4 = new Fragment4();
        fragment4.setScrollView(mScrollView);
        fragment4.setHeaderStr("收藏");

        mFragments.add(fragment1);
        mFragments.add(fragmentNewsroom);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        mFragments.add(fragment4);

    }
    public void initViewManager(List<Integer> mList){
    //给title赋值
        switch (mList.size()){
            case 3:
                titles = new String[]{"","",""};
                break;
            case 4:
                titles = new String[]{"","","",""};
                break;
            case 5:
                titles = new String[]{"","","","",""};
                break;
            case 6:
                titles = new String[]{"","","","","",""};
                break;
        }
        Fragment2 fragment2;
        for(int i=0;i<mList.size();i++){
            switch(mList.get(i)){
                case 0:
                    titles[i] = defaultTitles[0];
                    Fragment1 fragment1 = new Fragment1();
                    fragment1.setScrollView(mScrollView);
                    fragment1.setHeaderStr("首页");
                    mFragments.add(fragment1);
                    break;
                case 1:
                    titles[i] = defaultTitles[1];
                    FragmentNewsroom fragmentNewsroom = new FragmentNewsroom();
                    fragmentNewsroom.setScrollView(mScrollView);
                    fragmentNewsroom.setHeaderStr("编辑部");
                    mFragments.add(fragmentNewsroom);


                    break;
                case 2:
                    titles[i] = defaultTitles[2];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setDBTableName("testTotal3");
                    fragment2.setHeaderStr("全部");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&page=");
                    mFragments.add(fragment2);


                    break;
                case 3:
                    titles[i] = defaultTitles[3];
                    Fragment4 fragment4 = new Fragment4();
                    fragment4.setScrollView(mScrollView);
                    fragment4.setHeaderStr("收藏");
                    mFragments.add(fragment4);


                    break;
                case 4:
                    titles[i] = defaultTitles[4];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setHeaderStr("电影");
                    fragment2.setDBTableName("testMovie3");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=408&page=");
                    mFragments.add(fragment2);


                    break;
                case 5:
                    titles[i] = defaultTitles[5];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setDBTableName("testSeries3");
                    fragment2.setHeaderStr("剧集");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=401&page=");
                    mFragments.add(fragment2);




                    break;
                case 6:
                    titles[i] = defaultTitles[6];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setDBTableName("testCartoon3");
                    fragment2.setHeaderStr("动漫");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=404&page=");
                    mFragments.add(fragment2);




                    break;
                case 7:
                    titles[i] = defaultTitles[7];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setDBTableName("testMusic3");
                    fragment2.setHeaderStr("音乐");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=402&page=");
                    mFragments.add(fragment2);


                    break;
                case 8:
                    titles[i] = defaultTitles[8];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setDBTableName("testZongyi3");
                    fragment2.setHeaderStr("综艺");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=405&page=");
                    mFragments.add(fragment2);



                    break;
                case 9:
                    titles[i] = defaultTitles[9];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setHeaderStr("游戏");
                    fragment2.setDBTableName("testGame3");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=403&page=");
                    mFragments.add(fragment2);


                    break;
                case 10:
                    titles[i] = defaultTitles[10];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setHeaderStr("软件");
                    fragment2.setDBTableName("testSoftware3");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=406&page=");
                    mFragments.add(fragment2);


                    break;
                case 11:
                    titles[i] = defaultTitles[11];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setDBTableName("testDocument3");
                    fragment2.setHeaderStr("资料");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=407&page=");
                    mFragments.add(fragment2);

                    break;
                case 12:
                    titles[i] = defaultTitles[12];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setDBTableName("testSport3");
                    fragment2.setHeaderStr("体育");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=409&page=");
                    mFragments.add(fragment2);
                    break;
                case 13:
                    titles[i] = defaultTitles[13];
                    fragment2 = new Fragment2();
                    fragment2.setScrollView(mScrollView);
                    fragment2.setHeaderStr("记录");
                    fragment2.setDBTableName("testRecord3");
                    fragment2.setUrl("http://bt.byr.cn/torrents.php?inclbookmarked=0&incldead=0&spstate=0&cat=410&page=");
                    mFragments.add(fragment2);
                    break;
            }
        }
    }
}
