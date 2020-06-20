package com.lis.swipecardview.view.cardview;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * touch回调，这里继承 ItemTouchHelper.SimpleCallbac
 * @param <T>
 */
public class SwipeCardCallback<T> extends ItemTouchHelper.SimpleCallback {

    private List<T> data;
    RecyclerView.Adapter adapter;

    public SwipeCardCallback(RecyclerView.Adapter adapter, List<T> list) {
        //方向,或者使用super(0,15)
        super(0,ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
        this.data = list;
    }

    //drag
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    //item 滑出后的回调
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        T item = data.remove(viewHolder.getLayoutPosition());
        data.add(0, item);//放到第一个，无限循环
        adapter.notifyDataSetChanged();
    }

    //onDraw
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        double maxDistance = recyclerView.getWidth() / 2;
        double distance = Math.sqrt(dX * dX + dY * dY); //对角线
        //比例
        double fraction = distance / maxDistance;
        if (fraction > 1) fraction = 1;

        //显示的个数
        int itemCount = recyclerView.getChildCount();

        for (int i = 0; i < itemCount; i++) {
            View view = recyclerView.getChildAt(i);

            //权重
            int level = itemCount - 1 - i;
            if (level > 0) {
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                    //Y偏移,即下面露出后面的View
                    view.setTranslationY((float) (CardConfig.TRANS_Y_GAP * level - fraction * CardConfig.TRANS_Y_GAP));
                    //缩放
                    view.setScaleX((float) (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP));
                    view.setScaleY((float) (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP));
                }
            }
        }
    }

    //测出时的时间设置
    @Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
    }

    //滑出多少比例时移除，默认0.5f
    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.3f;
    }
}
