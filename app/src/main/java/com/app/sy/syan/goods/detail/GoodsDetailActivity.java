package com.app.sy.syan.goods.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.view.NavigationBar;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailContract.View, NavigationBar.NavigationBarInteface {
    public static final String PRODUCT_ID = "productId";

    @BindView(R.id.pub_navi_left_item_icon)
    ImageView pubNaviLeftItemIcon;
    @BindView(R.id.pub_navi_left_item_title)
    TextView pubNaviLeftItemTitle;
    @BindView(R.id.pub_navi_left_item)
    RelativeLayout pubNaviLeftItem;
    @BindView(R.id.pub_navi_center_item_title)
    TextView pubNaviCenterItemTitle;
    @BindView(R.id.pub_navi_center_item_icon)
    ImageView pubNaviCenterItemIcon;
    @BindView(R.id.pub_navi_center_item)
    RelativeLayout pubNaviCenterItem;
    @BindView(R.id.pub_navi_right_item_icon)
    ImageView pubNaviRightItemIcon;
    @BindView(R.id.pub_navi_right_item_title)
    TextView pubNaviRightItemTitle;
    @BindView(R.id.pub_navi_right_item)
    RelativeLayout pubNaviRightItem;
    @BindView(R.id.ll_navi_root)
    LinearLayout llNaviRoot;
    @BindView(R.id.iv_goods)
    ImageView ivGoods;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_goods_info_title)
    TextView tvGoodsInfoTitle;
    //    @BindView(R.id.tv_goods_info)
//    TextView tvGoodsInfo;
    @BindView(R.id.tv_goods_desc_title)
    TextView tvGoodsDescTitle;
    @BindView(R.id.tv_desc_info)
    TextView tvDescInfo;

    @Inject
    GoodsDetailPresenter goodsDetailPresenter;
    @BindView(R.id.tv_chengfen)
    TextView tvChengfen;
    @BindView(R.id.rl_chengfen)
    RelativeLayout rlChengfen;
    @BindView(R.id.tv_tedian)
    TextView tvTedian;
    @BindView(R.id.rl_tedian)
    RelativeLayout rlTedian;
    @BindView(R.id.tv_fangfa)
    TextView tvFangfa;
    @BindView(R.id.rl_fangfa)
    RelativeLayout rlFangfa;
    @BindView(R.id.tv_detail_cart_num)
    TextView tvDetailCartNum;
    @BindView(R.id.tv_detail_add_to_shop_cart)
    TextView tvDetailAddToShopCart;
    @BindView(R.id.tv_detail_buy_now)
    TextView tvDetailBuyNow;

    private Context mContext;
    private String productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);

        mContext = this;
        DaggerGoodsDetailComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .goodsDetailModule(new GoodsDetailModule(this))
                .build().inject(this);

        initView();
        bindListener();

        Intent intent = getIntent();
        productId = intent.getStringExtra(PRODUCT_ID);

        if (goodsDetailPresenter != null) {
            goodsDetailPresenter.getData(productId);
        }

    }

    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("商品详情");
        navigationBar.setCenterItemIconHidden();
    }

    private void bindListener() {
        //加入购物车
        RxView.clicks(tvDetailAddToShopCart)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });

        //立即购买
        RxView.clicks(tvDetailBuyNow)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });
    }

    @Override
    public void bindData(GoodsInfo goodsInfo) {
        if (!TextUtils.isEmpty(goodsInfo.getProductImg())) {
            Glide.with(mContext).load(goodsInfo.getProductImg()).centerCrop().into(ivGoods);
        } else {
            ivGoods.setImageResource(R.drawable.pic_default);
            ivGoods.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        tvGoodsName.setText(goodsInfo.getProductName());
        tvGoodsPrice.setText(goodsInfo.getProductPrice());
//        tvGoodsInfo.setText(goodsInfo.getProductInfo());
        if (!TextUtils.isEmpty(goodsInfo.getChengfen())) {
            rlChengfen.setVisibility(View.VISIBLE);
            tvChengfen.setText("\t\t\t\t\t\t" + goodsInfo.getChengfen());
        }
        if (!TextUtils.isEmpty(goodsInfo.getTedian())) {
            rlTedian.setVisibility(View.VISIBLE);
            tvTedian.setText("\t\t\t\t\t\t" + goodsInfo.getTedian());
        }
        if (!TextUtils.isEmpty(goodsInfo.getYongfa())) {
            rlFangfa.setVisibility(View.VISIBLE);
            tvFangfa.setText("\t\t\t\t\t\t\t\t\t\t" + goodsInfo.getYongfa());
        }

        tvDescInfo.setText(goodsInfo.getProductDescription());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onNavigationLeftItemClick() {
        finish();
    }

    @Override
    public void onNavigationRightItemClick() {

    }

    @Override
    public void onNavigationCenterItemClick() {

    }
}
