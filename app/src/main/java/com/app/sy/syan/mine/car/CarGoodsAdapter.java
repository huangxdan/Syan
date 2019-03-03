package com.app.sy.syan.mine.car;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.goods.detail.GoodsDetailActivity;
import com.app.sy.syan.util.DataTransfer;
import com.app.sy.syan.util.NumberUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CarGoodsAdapter extends RecyclerView.Adapter {
    private Context context;
    private CarPresenter mPresenter;
    private UpDateTotalPrice mUpDateTotalPrice;
    private List<GoodsInfo> mSelectGoods;

    public CarGoodsAdapter(Context context) {
        this.context = context;
        mSelectGoods = new ArrayList<>();
    }

    public List<GoodsInfo> getSelectGoods() {
        return mSelectGoods;
    }

    public void setPresenter(CarPresenter presenter) {
        this.mPresenter = presenter;
    }

    public void setUpDateTotalPrice(UpDateTotalPrice upDateTotalPrice) {
        this.mUpDateTotalPrice = upDateTotalPrice;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),
                R.layout.car_goods_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ViewHolder holder = (ViewHolder) viewHolder;

        GoodsInfo goodsInfo = DataTransfer.getInstance().cartGoods.get(position);
        if (!TextUtils.isEmpty(goodsInfo.getProductImg())) {
            Glide.with(context).load(goodsInfo.getProductImg()).into(holder.ivGoods);
        } else {
            holder.ivGoods.setImageResource(R.drawable.pic_default);
        }
        holder.tv_goods_name.setText(goodsInfo.getProductName());
        holder.tv_one_price.setText("￥" + NumberUtil.getDoubleString(goodsInfo.getProductPrice()));
        holder.tv_count.setText(goodsInfo.getGoodscount() + "");

        if (position == DataTransfer.getInstance().cartGoods.size() - 1) {
            holder.divider_view.setVisibility(View.GONE);
        } else {
            holder.divider_view.setVisibility(View.VISIBLE);
        }

        //添加监听事件
        bindListener(holder, goodsInfo);

    }

    private void bindListener(final ViewHolder holder, final GoodsInfo goodsInfo) {
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

        holder.tv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //减
                if (goodsInfo.getGoodscount() > 1) {
                    goodsInfo.setGoodscount(goodsInfo.getGoodscount() - 1);
                    holder.tv_count.setText(goodsInfo.getGoodscount() + "");
                    mPresenter.updateCartNum(goodsInfo.getProductId(), "-1");
                    if (mUpDateTotalPrice != null) {
                        mUpDateTotalPrice.upDate(mSelectGoods);
                    }
                }
            }
        });

        holder.tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加
                if (goodsInfo.getGoodscount() < 99) {
                    goodsInfo.setGoodscount(goodsInfo.getGoodscount() + 1);
                    holder.tv_count.setText(goodsInfo.getGoodscount() + "");
                    mPresenter.updateCartNum(goodsInfo.getProductId(), "1");
                    if (mUpDateTotalPrice != null) {
                        mUpDateTotalPrice.upDate(mSelectGoods);
                    }
                }
            }
        });

        holder.iv_dustbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除商品
                mPresenter.updateCartNum(goodsInfo.getProductId(), "-2");
                DataTransfer.getInstance().cartGoods.remove(goodsInfo);
                if (mSelectGoods.contains(goodsInfo)) {
                    mSelectGoods.remove(goodsInfo);
                    if (mUpDateTotalPrice != null) {
                        mUpDateTotalPrice.upDate(mSelectGoods);
                    }
                }
                notifyDataSetChanged();
            }
        });

        //选择商品
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!mSelectGoods.contains(goodsInfo)) {
                        mSelectGoods.add(goodsInfo);
                    }
                } else {
                    if (mSelectGoods.contains(goodsInfo)) {
                        mSelectGoods.remove(goodsInfo);
                    }
                }
                if (mUpDateTotalPrice != null) {
                    mUpDateTotalPrice.upDate(mSelectGoods);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return DataTransfer.getInstance().cartGoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView ivGoods, iv_dustbin;
        public TextView tv_goods_name, tv_one_price, tv_reduce, tv_count, tv_add;
        public CheckBox checkbox;
        public View divider_view;

        public ViewHolder(View view) {
            super(view);
            ivGoods = (ImageView) view.findViewById(R.id.iv_goods);
            iv_dustbin = (ImageView) view.findViewById(R.id.iv_dustbin);
            tv_goods_name = (TextView) view.findViewById(R.id.tv_goods_name);
            tv_one_price = (TextView) view.findViewById(R.id.tv_one_price);
            tv_reduce = (TextView) view.findViewById(R.id.tv_reduce);
            tv_count = (TextView) view.findViewById(R.id.tv_count);
            tv_add = (TextView) view.findViewById(R.id.tv_add);
            checkbox = (CheckBox) view.findViewById(R.id.checkbox);
            divider_view = (View) view.findViewById(R.id.divider_view);
        }

    }

    public interface UpDateTotalPrice {
        void upDate(List<GoodsInfo> selectGoods);
    }
}
