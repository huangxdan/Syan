package com.app.sy.syan.mine.order;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.goods.detail.GoodsDetailActivity;
import com.app.sy.syan.util.RecyclerAdapterWithHF;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<GoodsInfo> mList;

    public OrderAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    public void setData(List<GoodsInfo> items) {
        this.mList.clear();
        this.mList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),
                R.layout.order_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ViewHolder holder = (ViewHolder) viewHolder;

        final GoodsInfo goodsInfo = mList.get(position);

        holder.recycleview.setLayoutManager(new LinearLayoutManager(context));
        holder.recycleview.setAdapter(new RecyclerAdapterWithHF(new OrderItemAdapter(context)));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_state;
        public RecyclerView recycleview;

        public ViewHolder(View view) {
            super(view);
            tv_state = (TextView) view.findViewById(R.id.tv_state);
            recycleview = (RecyclerView) view.findViewById(R.id.recycleview);
        }

    }
}
