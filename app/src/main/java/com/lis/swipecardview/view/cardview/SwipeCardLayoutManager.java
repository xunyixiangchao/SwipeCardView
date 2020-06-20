package com.lis.swipecardview.view.cardview;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class SwipeCardLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
        //仿造LinearLayoutManager回收复用
        //回收
        detachAndScrapAttachedViews(recycler);

        //bottomPosition获取开始的位置.
        //todo 这里一定要用getItemCount,别用getChildCount()，否则显示不出来。
        int itemCount = getItemCount();
        int bottomPosition;
        if (itemCount < CardConfig.MAX_SHOW_COUNT) {
            bottomPosition = 0;
        } else {
            bottomPosition = itemCount - CardConfig.MAX_SHOW_COUNT;
        }
        for (int i = bottomPosition; i < itemCount; i++) {
            //复用
            View view = recycler.getViewForPosition(i);
            addView(view);

            //measure view
            measureChildWithMargins(view, 0, 0);

            int viewWidth = getDecoratedMeasuredWidth(view);
            int viewHeight = getDecoratedMeasuredHeight(view);
            //求padding的总大小
            int widthSpace = getWidth() - viewWidth;
            int heightSpace = getHeight() - viewHeight;

            //布局-非子View
            layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + viewWidth, heightSpace / 2 + viewHeight
            );

            //子view位置偏移
            int level = itemCount - 1 - i;
            if (level > 0) {
                //非最底层的view
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                    //Y偏移,即下面露出后面的View
                    view.setTranslationY(CardConfig.TRANS_Y_GAP * level);
                    //缩放
                    view.setScaleX(1-CardConfig.SCALE_GAP * level);
                    view.setScaleY(1-CardConfig.SCALE_GAP * level);
                } else {
                    //0,最底层一个，和上一层一样显示
                    //Y偏移,即下面露出后面的View
                    view.setTranslationY(CardConfig.TRANS_Y_GAP * (level - 1));
                    //缩放
                    view.setScaleX(1-CardConfig.SCALE_GAP * (level - 1));
                    view.setScaleY(1-CardConfig.SCALE_GAP * (level - 1));
                }
            }
        }
    }
}
