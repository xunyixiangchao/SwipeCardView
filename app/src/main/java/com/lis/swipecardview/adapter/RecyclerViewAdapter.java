package com.lis.swipecardview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lis.swipecardview.R;
import com.lis.swipecardview.SwipeCardBean;

import java.util.List;

public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    List<T> mData;
    Context mContext;
    protected ViewGroup mRv;

    public RecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<T> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.get(this.mContext, (View) null, parent, R.layout.item_swipe_card);
        if (null == this.mRv) {
            this.mRv = parent;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SwipeCardBean swipeCardBean = (SwipeCardBean) mData.get(position);
        holder.setText(R.id.tvName, swipeCardBean.getName());
        holder.setText(R.id.tvPrecent, swipeCardBean.getPostition() + "/" + mData.size());
                Glide.with(mContext)
                        .load(swipeCardBean.getUrl())
                        .into((ImageView) holder.getView(R.id.iv));

    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
