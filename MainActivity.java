package com.example.ykai.app_nul;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.ykai.app_nul.R;
import com.example.ykai.app_nul.study_widget.Message;
import com.example.ykai.app_nul.study_widget.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecycleViewAdapter adapter;
    private List<Message> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        rv = (RecyclerView) findViewById(R.id.rv);
        // 线性的
//        rv.setLayoutManager(new LinearLayoutManager(this));
        // 表格的
//        rv.setLayoutManager(new GridLayoutManager(this,2));
        // 瀑布流的
        rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        adapter = new RecycleViewAdapter(list);
        rv.setAdapter(adapter);
        helper.attachToRecyclerView(rv);

    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new Message("Hensen", "下午1:22", "老板：哈哈哈", R.mipmap.ic_launcher));
        list.add(new Message("流年不利", "早上10:31", "美女：呵呵哒", R.mipmap.ic_launcher));
        list.add(new Message("1402", "下午1:55", "嘻嘻哈哈", R.mipmap.ic_launcher));
        list.add(new Message("Unstoppable", "下午4:32", "美美哒", R.mipmap.ic_launcher));
        list.add(new Message("流年不利", "晚上7:22", "萌萌哒", R.mipmap.ic_launcher));
        list.add(new Message("Hensen", "下午1:22", "哈哈哈", R.mipmap.ic_launcher));
        list.add(new Message("Hensen", "下午1:22", "哈哈哈", R.mipmap.ic_launcher));
        list.add(new Message("Hensen", "下午1:22", "哈哈哈", R.mipmap.ic_launcher));
    }

    //为RecycleView绑定触摸事件
    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //首先回调的方法 返回int表示是否监听该方向
            int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;//拖拽
            int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;//侧滑删除
            return makeMovementFlags(dragFlags,swipeFlags);

            //首先回调的方法 返回int表示是否监听该方向
//            int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;//拖拽
//            int swipeFlags = 0;//侧滑删除
//            return makeMovementFlags(dragFlags,swipeFlags);

        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //滑动事件
            Collections.swap(list,viewHolder.getAdapterPosition(),target.getAdapterPosition());
            adapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //侧滑事件
            list.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }

        @Override
        public boolean isLongPressDragEnabled() {
            //是否可拖拽
            return true;
        }
    });

}
