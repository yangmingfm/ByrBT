//package com.example.fragment2more;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.Check.ClearCookie;
//import com.example.aboutHTML.SentFavoriteInfo;
//import com.example.getData.GetFavoritesInfo;
//import com.example.ymjkmaster.byrbta.LandActivity;
//import com.example.ymjkmaster.byrbta.R;
//
///**
// * Created by ymjkMaster on 2015/5/26 0026.
// */
//public class Fragment5 extends Fragment {
//    private Button button;
//    private String strCookie = "c_secure_uid=MTUzNTEw; c_secure_pass=af1ab1eddd83474919eb13d8f5a54c27; c_secure_ssl=bm9wZQ%3D%3D; c_secure_tracker_ssl=bm9wZQ%3D%3D; c_secure_login=bm9wZQ%3D%3D";
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment5,container,false);
//        button = (Button)view.findViewById(R.id.button1);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(getActivity(), LandActivity.class);
////                startActivity(intent);
//                ClearCookie.clearCookie(getActivity());
//            }
//        });
//
//        return view;
//    }
//    public void runRefresh(){
//        new Thread(){
//            @Override
//            public void run() {
//                GetFavoritesInfo getFavoritesInfo = new GetFavoritesInfo();
//                SentFavoriteInfo sentFavoriteInfo = new SentFavoriteInfo();
//                getFavoritesInfo.startGet(strCookie);
//                sentFavoriteInfo.sendFavorite(strCookie,"178934");
//            }
//        }.start();
//    }
//}
