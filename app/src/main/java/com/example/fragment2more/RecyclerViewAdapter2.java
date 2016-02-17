package com.example.fragment2more;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.example.aboutHTML.TorrentList;
import com.example.aboutHTML.TorrentListA;
import com.example.aboutImage.MyImageCache;
import com.example.aboutImage.SingleRequestQueue;
import com.example.aboutStringFilter.TitleFilter;
import com.example.ymjkmaster.byrbta.R;
import com.android.volley.toolbox.NetworkImageView;


public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>{
    private String headerStr;
    private TorrentList mData;
    private TorrentListA mDataA;
    private LayoutInflater  mInflater;
    private RequestQueue mQueue;
    private ImageLoader imageLoader;
    public RecyclerViewAdapter2(Context context,TorrentList mData,TorrentListA mDataA,String headerStr){
        this.mInflater = LayoutInflater.from(context);
        this.headerStr = headerStr;
        this.mData = mData;
        this.mDataA = mDataA;
        mQueue = SingleRequestQueue.getRequestQueue(context.getApplicationContext());
        imageLoader = new ImageLoader(mQueue, new MyImageCache());
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
		if(position==0) {
            return 0;
        }
        else if(position<mDataA.getSize()+1)
        {
            return 1;
        }
        else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        // TODO Auto-generated method stub
        int type = viewHolder.getItemViewType();
        switch(type) {
            case 0:
                viewHolder.title.setText(headerStr);
                break;
            case 1:
                viewHolder.title.setText(TitleFilter.titleFilter(mDataA.getItem(position - 1).getTitle()));
                viewHolder.img.setDefaultImageResId(R.drawable.img_default);
                viewHolder.img.setErrorImageResId(R.drawable.load_error_b);
                viewHolder.img.setImageUrl(mDataA.getItem(position - 1).getThemeImgUrl(), imageLoader);
                ObjectAnimator.ofFloat(viewHolder.itemView,"scaleX", 0.9F, 1.0F).setDuration(300).start();
                ObjectAnimator.ofFloat(viewHolder.itemView,"scaleY", 0.9F, 1.0F).setDuration(300).start();
                break;
            case 2:
                viewHolder.title.setText(TitleFilter.titleFilter(mData.getItem(position - mDataA.getSize()-1).getTitle()));
                ObjectAnimator.ofFloat(viewHolder.itemView,"scaleX", 0.9F, 1.0F).setDuration(300).start();
                ObjectAnimator.ofFloat(viewHolder.itemView,"scaleY", 0.9F, 1.0F).setDuration(300).start();;
                break;
        }
        if(mOnItemClickListener!=null)
        {
            viewHolder.itemView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    mOnItemClickListener.onItemClick(viewHolder.itemView, position);

                }
            });
        }
    }
    //返回某个item对应的view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup ViewGroup, int itemType) {
        // TODO Auto-generated method stub
        View itemView =null;
        ViewHolder viewHolder = null;
        switch(itemType)
        {
            case 0:
                itemView = mInflater.inflate(R.layout.fragment_header_layout,ViewGroup, false);
                viewHolder = new ViewHolder(itemView);
                viewHolder.title = (TextView)itemView.findViewById(R.id.header_tv);
                break;
            case 1:
                itemView = mInflater.inflate(R.layout.fragment3_recycler_item2,ViewGroup, false);
                viewHolder = new ViewHolder(itemView);
                viewHolder.title = (TextView)itemView.findViewById(R.id.textview1);
                viewHolder.img = (NetworkImageView)itemView.findViewById(R.id.networkimageview1);
                break;
            case 2:
                itemView = mInflater.inflate(R.layout.fragment3_recycler_item1,  ViewGroup, false);
                viewHolder = new ViewHolder(itemView);
                viewHolder.title = (TextView)itemView.findViewById(R.id.textview1);
                break;
        }
        return viewHolder;
    }
    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return mData.getSize()+mDataA.getSize()+1;
    }
    //定义viewHolder用于保存布局控件相对位置
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private NetworkImageView img;
        public ViewHolder(View itemView){
            super(itemView);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int i);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener)
    {
        this.mOnItemClickListener = mOnItemClickLitener;
    }
    public void clearData(){
        this.mData = null;
        this.mDataA = null;
        this.headerStr = null;
        this.imageLoader = null;
    }
}