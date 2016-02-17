package com.example.fragment2more;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aboutHTML.SentFavoriteInfo;
import com.example.aboutHTML.TorrentList;
import com.example.aboutStringFilter.DateFilter;
import com.example.aboutStringFilter.GetTorrentId;
import com.example.aboutStringFilter.TitleFilter;
import com.example.ymjkmaster.byrbta.R;



public class RecyclerViewAdapterFavorite extends RecyclerView.Adapter<RecyclerViewAdapterFavorite.ViewHolder>{
    private TorrentList mData;
    private String headerStr;
    private LayoutInflater  mInflater;
    private String strCookie;
    private Context context;
    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x996) {
                Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
            else if(msg.what == 0x997)
            {
                Toast.makeText(context,"取消失败，请检查网络",Toast.LENGTH_SHORT).show();
            }
        }
    };
    public RecyclerViewAdapterFavorite(Context context,TorrentList mData,String headerStr,String strCookie){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = mData;
        this.headerStr = headerStr;
        this.strCookie = strCookie;
    }
    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        if(position==0){
            return 0;
        }else{
            return 1;
        }
    }
    //把数据绑定至ViewHolder 可以在此设置item监听？
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        // TODO Auto-generated method stub
        int type = viewHolder.getItemViewType();
        switch(type)
        {
            case 0:
                viewHolder.title.setText(headerStr);
                break;
            case 1:
                viewHolder.title.setText((TitleFilter.titleFilter(mData.getItem(position-1).getTitle())));
                viewHolder.desc.setText(mData.getItem(position-1).getDecs());
                viewHolder.date.setText(DateFilter.dateFilter(mData.getItem(position - 1).getDate()));
                viewHolder.btnCancel.setOnClickListener(new View.OnClickListener(){
                                                            @Override
                                                            public void onClick(View v) {
                                                                new Thread(){
                                                                    @Override
                                                                    public void run() {
                                                                        Message msg = new Message();
                                                                        SentFavoriteInfo sentFavoriteInfo = new SentFavoriteInfo();
                                                                        sentFavoriteInfo.sendFavorite(strCookie, GetTorrentId.getTorrentId(mData.getItem(position-1).getDetailUrl()));
                                                                        if (sentFavoriteInfo.sendSucess)
                                                                        {
                                                                            msg.what = 0x996;
                                                                            mData.removeItem(position-1);
                                                                            handler.sendEmptyMessage(msg.what);
                                                                        }
                                                                        else
                                                                        {
                                                                            msg.what = 0x997;
                                                                            handler.sendEmptyMessage(msg.what);
                                                                        }
                                                                    }
                                                                }.start();
                                                            }
                                                        }
                );
                ObjectAnimator.ofFloat(viewHolder.itemView,"scaleX", 0.9F, 1.0F).setDuration(300).start();
                ObjectAnimator.ofFloat(viewHolder.itemView,"scaleY", 0.9F, 1.0F).setDuration(300).start();
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
        View itemView;
        ViewHolder viewHolder = null;
        switch(itemType)
        {
            case 0:
                itemView = mInflater.inflate(R.layout.fragment_header_layout,  ViewGroup, false);
                viewHolder = new ViewHolder(itemView);
                viewHolder.title = (TextView)itemView.findViewById(R.id.header_tv);
                break;
            case 1:
                itemView = mInflater.inflate(R.layout.favoritelist_item_layout,  ViewGroup, false);
                viewHolder = new ViewHolder(itemView);
                viewHolder.title = (TextView)itemView.findViewById(R.id.textview1);
                viewHolder.desc = (TextView)itemView.findViewById(R.id.textview2);
                viewHolder.date = (TextView)itemView.findViewById(R.id.tv_date);
                viewHolder.btnCancel = (Button)itemView.findViewById(R.id.btn_cancel);
                break;
        }
        return viewHolder;
    }
    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return mData.getSize()+1;
    }
    //定义viewHolder用于保存布局控件相对位置
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private Button  btnCancel;
        private TextView title;
        private TextView desc;
        private TextView date;
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
//    //半角及全角字符转全角字符
//    public static String ToDBC(String input) {
//        char[] c = input.toCharArray();
//        for (int i = 0; i< c.length; i++) {
//            if (c[i] == 12288) {
//                c[i] = (char) 32;
//                continue;
//            }if (c[i]> 65280&& c[i]< 65375)
//                c[i] = (char) (c[i] - 65248);
//        }
//        return new String(c);
//    }
    //textview 的显示要注意数字与中文标点后面的汉字,否则后面的汉字会和前面视为一体
    //用正则匹配给所有“ ]” 后面加上空格即可
//    public static String StringFilter(String str){
//        return str.replaceAll("\\]", "]  ");

    //}
    //去掉特殊字符以及中文符号的函数
//	public static String StringFilter(String str) throws PatternSyntaxException{
//	    str=str.replaceAll("【","[").replaceAll("】","]").replaceAll("！","!");//替换中文标号
//	    String regEx="[『』]"; // 清除掉特殊字符
//	    Pattern p = Pattern.compile(regEx);
//	    Matcher m = p.matcher(str);
//	 return m.replaceAll("").trim();
//	}
}