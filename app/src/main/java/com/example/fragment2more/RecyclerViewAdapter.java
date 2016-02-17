package com.example.fragment2more;

        import android.animation.ObjectAnimator;
        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.example.aboutHTML.TorrentList;
        import com.example.aboutStringFilter.DateFilter;
        import com.example.aboutStringFilter.TitleFilter;
        import com.example.ymjkmaster.byrbta.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private TorrentList mData;
    private String headerStr;
    private LayoutInflater  mInflater;
    public RecyclerViewAdapter(Context context,TorrentList mData,String headerStr){
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.headerStr = headerStr;
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
                viewHolder.upNum.setText(mData.getItem(position-1).getUpNum());
                viewHolder.finishNum.setText(mData.getItem(position-1).getFinishNum());
                viewHolder.date.setText(DateFilter.dateFilter(mData.getItem(position - 1).getDate()));
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
                itemView = mInflater.inflate(R.layout.fragment2_recycle_item1,  ViewGroup, false);
                viewHolder = new ViewHolder(itemView);
                viewHolder.title = (TextView)itemView.findViewById(R.id.textview1);
                viewHolder.desc = (TextView)itemView.findViewById(R.id.textview2);
                viewHolder.upNum = (TextView)itemView.findViewById(R.id.tv_upnum);
                viewHolder.finishNum = (TextView)itemView.findViewById(R.id.tv_finishnum);
                viewHolder.date = (TextView)itemView.findViewById(R.id.tv_date);
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
        private TextView title;
        private TextView desc;
        private TextView upNum;
        private TextView finishNum;
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
}