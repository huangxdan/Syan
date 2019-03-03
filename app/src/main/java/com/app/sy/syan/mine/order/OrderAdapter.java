package com.app.sy.syan.mine.order;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.app.sy.syan.data.MyOrderList;
import com.app.sy.syan.util.NumberUtil;
import com.app.sy.syan.util.RecyclerAdapterWithHF;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<MyOrderList> mList;

    public OrderAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    public void setData(List<MyOrderList> items) {
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

        final MyOrderList myOrderList = mList.get(position);

        holder.tv_state.setText(myOrderList.getOrderInfo());

        //单个订单的商品列表不需要滑动，所以在这里禁止掉item的滑动事件
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        holder.recycleview.setLayoutManager(linearLayoutManager);
        OrderItemAdapter orderItemAdapter = new OrderItemAdapter(context);
        holder.recycleview.setAdapter(new RecyclerAdapterWithHF(orderItemAdapter));
        //下面两句是防止刷新单个订单的recyclerView导致所有订单recyclerView也发生滑动
        holder.recycleview.setFocusableInTouchMode(false);
        holder.recycleview.requestFocus();

        orderItemAdapter.setData(myOrderList.getProductList());

        double totalMoney = 0;
        int totalCount = 0;
        //更新总价钱
        for (int i = 0; i < myOrderList.getProductList().size(); i++) {
            totalCount += myOrderList.getProductList().get(i).getGoodsNum();
            totalMoney += (myOrderList.getProductList().get(i).getProductPrice() * myOrderList.getProductList().get(i).getGoodsNum());
        }

        holder.tv_all_price.setText("¥ " + NumberUtil.getDoubleString(totalMoney));
        holder.tv_total_count.setText(totalCount + "");

        if (position == mList.size() - 1) {
            holder.divider_view.setVisibility(View.GONE);
        } else {
            holder.divider_view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_state, tv_total_count, tv_all_price;
        public RecyclerView recycleview;
        public View divider_view;

        public ViewHolder(View view) {
            super(view);
            tv_state = (TextView) view.findViewById(R.id.tv_state);
            tv_total_count = (TextView) view.findViewById(R.id.tv_total_count);
            tv_all_price = (TextView) view.findViewById(R.id.tv_all_price);
            recycleview = (RecyclerView) view.findViewById(R.id.recycleview);
            divider_view = (View) view.findViewById(R.id.divider_view);
        }

    }
}
