package com.boping.widgetDemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    private RecyclerView mRecyclerView;
    private ArrayList<String> mItems;
    private static final int ADD_ITEM = 0x01;
    private int num = 0;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int what = msg.what;
            switch (what) {
                case ADD_ITEM:
                    mItems.add("" + num++);
                    mAdapter.notifyDataSetChanged();
                    if (num < 100)
                        mHandler.sendEmptyMessageDelayed(ADD_ITEM, 1000);
                    break;
            }
        }
    };
    private RecyclerAdapter mAdapter;
    int addNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        //设置布局管理器
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置为横向布局
        //linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //设置为纵向布局，系统默认
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mAdapter = new RecyclerAdapter(mItems, this);
        //设置适配器
        mRecyclerView.setAdapter(mAdapter);
        //设置增加或者删除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置item分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,OrientationHelper.VERTICAL));
//        mHandler.sendEmptyMessageDelayed(ADD_ITEM, 1000);
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "onItemClick: "+position);
                mAdapter.MovedToTop(position,0);
                mRecyclerView.scrollToPosition(0);
//                    mAdapter.addItem("add:"+addNum++,position);
//                Toast.makeText(ListActivity.this,"click:"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                mAdapter.removeItem(position);
                Log.d(TAG, "onItemLongClick: "+position);
                Toast.makeText(ListActivity.this,"long:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mItems = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mItems.add("" + i);
        }
    }

}
