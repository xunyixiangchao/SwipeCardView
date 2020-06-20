package com.lis.swipecardview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lis.swipecardview.adapter.UniversalAdapter;
import com.lis.swipecardview.adapter.ViewHolder;
import com.lis.swipecardview.view.cardview.CardConfig;
import com.lis.swipecardview.view.cardview.SwipeCardCallback;
import com.lis.swipecardview.view.cardview.SwipeCardLayoutManager;

import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView rv;
    private UniversalAdapter<SwipeCardBean> adapter;
    private List<SwipeCardBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card);
        CardConfig.initConfig(this);
        rv = findViewById(R.id.rv);

        mDatas = SwipeCardBean.initDatas();

        adapter = new UniversalAdapter<SwipeCardBean>(MainActivity.this, mDatas, R.layout.item_swipe_card) {
            @Override
            public void convert(ViewHolder ViewHolder, SwipeCardBean swipeCardBean) {
                ViewHolder.setText(R.id.tvName, swipeCardBean.getName());
                ViewHolder.setText(R.id.tvPrecent, swipeCardBean.getPostition() + "/" + mDatas.size());
                Glide.with(MainActivity.this)
                        .load(swipeCardBean.getUrl())
                        .into((ImageView) ViewHolder.getView(R.id.iv));
            }
        };
        rv.setLayoutManager(new SwipeCardLayoutManager());
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter<SwipeCardBean>(this);
        rv.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SwipeCardCallback<>(adapter, mDatas);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rv);

    }

}
