package com.boping.widgetDemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 项目名称：WidgetDemo
 * 晶凌达科技有限公司所有，
 * 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 *
 * @creator boping
 * @create-time 2017/2/22 11:02
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    public static final String TAG = "RecyclerAdapter";
    private ArrayList<String> mItems;
    private Context mContext;
    private LayoutInflater mInflater;


    public RecyclerAdapter(ArrayList<String> items, Context context) {
        Log.d(TAG, "RecyclerAdapter");
        mItems = items;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");

        View view = mInflater.inflate(R.layout.layout_item, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public MyHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.recycler_item_num);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder");
        MyHolder myHolder = (MyHolder) holder;
        myHolder.mTextView.setText(mItems.get(position));
        myHolder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view,position);
                }
            }
        });
        myHolder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemLongClick(view,position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount:" + mItems.size());
        return mItems.size();
    }

    public void addItem(String str,int position){
        mItems.add(position,str);
        notifyItemInserted(position);//更新单个位置的数据，并伴有动画
        notifyItemRangeChanged(position,mItems.size());//更新单个位置的数据
//        notifyItemChanged(position);//更新单个位置的数据
//        notifyDataSetChanged();
    }
    public void removeItem(int position){
        mItems.remove(position);
//        notifyItemChanged(position);//更新单个位置的数据
        notifyItemRemoved(position);//更新单个位置的数据，并伴有动画
        notifyItemRangeChanged(position,mItems.size());//更新单个位置的数据
//        notifyDataSetChanged();
    }
    public void MovedToTop(int formPosition,int toPosition){

        if(formPosition>=mItems.size()||toPosition>=mItems.size())
            throw new IllegalArgumentException();
        //数据移动
        String item = mItems.get(formPosition);
        mItems.remove(formPosition);
        mItems.add(toPosition,item);
        //移动动画
        notifyItemMoved(formPosition,toPosition);//
        //更新界面
        notifyItemRangeChanged(formPosition,mItems.size());

//        notifyItemRangeInserted(formPosition,toPosition);
//        notifyDataSetChanged();
//        mHandler.sendEmptyMessageDelayed(1,500);
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            notifyDataSetChanged();
        }
    };
    OnItemClickListener mOnItemClickListener;
    /**
     * 点击监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
