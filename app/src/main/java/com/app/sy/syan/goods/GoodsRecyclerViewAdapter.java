package com.app.sy.syan.goods;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.data.remote.RemoteDataManager;
import com.app.sy.syan.goods.detail.GoodsDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class GoodsRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<GoodsInfo> mList;

    public GoodsRecyclerViewAdapter(Context context) {
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
                R.layout.goods_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ViewHolder holder = (ViewHolder) viewHolder;

        final GoodsInfo goodsInfo = mList.get(position);
        if (!TextUtils.isEmpty(goodsInfo.getProductImg())) {
            Glide.with(context).load(goodsInfo.getProductImg()).into(holder.ivGoods);
        } else {
            holder.ivGoods.setImageResource(R.drawable.pic_default);
        }
        holder.tv_goods_name.setText(goodsInfo.getProductName());
        holder.tv_goods_price.setText("￥" + goodsInfo.getProductPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {
                    Intent intent = new Intent(context, GoodsDetailActivity.class);
                    intent.putExtra(GoodsDetailActivity.PRODUCT_ID, goodsInfo.getProductId());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView ivGoods;
        public TextView tv_goods_name, tv_goods_price;

        public ViewHolder(View view) {
            super(view);
            ivGoods = (ImageView) view.findViewById(R.id.iv_goods);
            tv_goods_name = (TextView) view.findViewById(R.id.tv_goods_name);
            tv_goods_price = (TextView) view.findViewById(R.id.tv_goods_price);
        }

    }
}
