package com.app.sy.syan.goods.detail;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.CartGoodsCount;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.mine.car.CarActivity;
import com.app.sy.syan.mine.order.confirm.ConfirmActivity;
import com.app.sy.syan.util.ActivityManager;
import com.app.sy.syan.util.NumberUtil;
import com.app.sy.syan.view.BezierView;
import com.app.sy.syan.view.NavigationBar;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;

import java.io.Serializable;
import java.util.ArrayList;
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
    @BindView(R.id.rl_add_car)
    RelativeLayout rlAddCar;
    @BindView(R.id.iv_car_icon)
    ImageView ivCarIcon;
    @BindView(R.id.tv_detail_cart_num)
    TextView tvDetailCartNum;
    @BindView(R.id.tv_detail_add_to_shop_cart)
    TextView tvDetailAddToShopCart;
    @BindView(R.id.tv_detail_buy_now)
    TextView tvDetailBuyNow;

    private Context mContext;
    private String productId;
    private int cartCount;
    private GoodsInfo mGoodsInfo;

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
            goodsDetailPresenter.getCartCount(productId);
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
                        showAddCartSuccessAnim(++cartCount);
                        if (goodsDetailPresenter != null) {
                            goodsDetailPresenter.updateCartNum(productId);
                        }
                    }
                });

        //立即购买
        RxView.clicks(tvDetailBuyNow)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (mGoodsInfo != null) {
                            ArrayList<GoodsInfo> arrayList = new ArrayList<>();
                            mGoodsInfo.setGoodscount(1);
                            arrayList.add(mGoodsInfo);

                            Intent intent = new Intent(GoodsDetailActivity.this, ConfirmActivity.class);
                            intent.putExtra("list", (Serializable) arrayList);
                            intent.putExtra("totalPrice", NumberUtil.getDoubleString(mGoodsInfo.getProductPrice()));
                            startActivity(intent);
                        } else {
                            showToast("购买失败，请联系管理员");
                        }
                    }
                });

        //点击购物车
        RxView.clicks(rlAddCar)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        for (Activity activity : ActivityManager.instance().getActivities()) {
                            if (activity instanceof CarActivity) {
                                activity.finish();
                                break;
                            }
                        }
                        startActivity(new Intent(GoodsDetailActivity.this, CarActivity.class));
                    }
                });
    }

    @Override
    public void bindCartCount(CartGoodsCount cartGoodsCount) {
        cartCount = cartGoodsCount.getCartTotal();
        setCartNumb(cartGoodsCount.getCartTotal());
    }

    @Override
    public void bindData(GoodsInfo goodsInfo) {
        mGoodsInfo = goodsInfo;
        if (!TextUtils.isEmpty(goodsInfo.getProductImg())) {
            Glide.with(mContext).load(goodsInfo.getProductImg()).centerCrop().into(ivGoods);
        } else {
            ivGoods.setImageResource(R.drawable.pic_default);
            ivGoods.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        tvGoodsName.setText(goodsInfo.getProductName());
        tvGoodsPrice.setText(NumberUtil.getDoubleString(goodsInfo.getProductPrice()));
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

    /**
     * 加入购物车成功动画
     */
    public void showAddCartSuccessAnim(final int num) {
        int addCartAnimStartPosition[] = new int[2];//贝塞尔曲线起点
        int addCartAnimEndPosition[] = new int[2];//贝塞尔曲线终点
        BezierView addCartBezierView;//加入购物车贝塞尔曲线
        tvDetailAddToShopCart.getLocationInWindow(addCartAnimStartPosition);
        addCartAnimStartPosition[0] += tvDetailAddToShopCart.getWidth() / 2;
        rlAddCar.getLocationInWindow(addCartAnimEndPosition);
        addCartBezierView = new BezierView(this);
        addCartBezierView.setStartPosition(new Point(addCartAnimStartPosition[0], addCartAnimStartPosition[1]));
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        rootView.addView(addCartBezierView);
        addCartBezierView.setEndPosition(new Point(addCartAnimEndPosition[0], addCartAnimEndPosition[1]));
        addCartBezierView.startBeizerAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(ivCarIcon, "rotation", 0f, 50f, 0f);
                animator.setInterpolator(new OvershootInterpolator());
                animator.setDuration(600);
                animator.start();
                setCartNumb(num);
            }
        }, 600);
    }

    /**
     * 设置购物车数量
     *
     * @param num
     */
    public void setCartNumb(int num) {
        if (num > 0) {
            tvDetailCartNum.setVisibility(View.VISIBLE);
            if (num > 99) {
                tvDetailCartNum.setText("100");
            } else {
                tvDetailCartNum.setText(num + "");
            }
        } else {
            tvDetailCartNum.setVisibility(View.GONE);
        }
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
