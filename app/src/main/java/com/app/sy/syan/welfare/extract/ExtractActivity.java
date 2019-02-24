package com.app.sy.syan.welfare.extract;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.WelfareInfo;
import com.app.sy.syan.util.Constant;
import com.app.sy.syan.util.PreferenceUtils;
import com.app.sy.syan.view.NavigationBar;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 今日提成 收益
 */
public class ExtractActivity extends BaseActivity implements NavigationBar.NavigationBarInteface, ExtractContract.View {

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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_today_money)
    TextView tvTodayMoney;
    @BindView(R.id.rl_today_money)
    RelativeLayout rlTodayMoney;
    @BindView(R.id.tv_goods_money)
    TextView tvGoodsMoney;
    @BindView(R.id.rl_goods_money)
    RelativeLayout rlGoodsMoney;
    @BindView(R.id.tv_invoice_money)
    TextView tvInvoiceMoney;
    @BindView(R.id.rl_invoice_money)
    RelativeLayout rlInvoiceMoney;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.rl_total_money)
    RelativeLayout rlTotalMoney;

    @Inject
    ExtractPresenter extractPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extract);
        ButterKnife.bind(this);
        DaggerExtractComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .extractModule(new ExtractModule(this))
                .build().inject(this);

        if (extractPresenter != null) {
            extractPresenter.getData(PreferenceUtils.getPrefString(ExtractActivity.this, Constant.STAFF_NUMBER, ""));
        }
        initView();
    }

    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("今日提成");
        navigationBar.setCenterItemIconHidden();
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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ExtractActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindDate(WelfareInfo welfareInfo) {
        if (welfareInfo != null) {
            tvName.setText(welfareInfo.getStaffName());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = new Date(System.currentTimeMillis());
            tvData.setText(simpleDateFormat.format(date));
            tvTodayMoney.setText(welfareInfo.getTodayServiceFee() + "");
            tvGoodsMoney.setText(welfareInfo.getGoodsServiceFee() + "");
            tvInvoiceMoney.setText(welfareInfo.getInvoiceServiceFee() + "");
            tvTotalMoney.setText(welfareInfo.getWeekTotalFee() + "");
        }
    }
}
