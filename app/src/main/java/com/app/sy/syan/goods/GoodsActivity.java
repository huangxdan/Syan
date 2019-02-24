package com.app.sy.syan.goods;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.util.RecyclerAdapterWithHF;
import com.app.sy.syan.view.LoadingDialog;
import com.app.sy.syan.view.NavigationBar;
import com.app.sy.syan.view.RecycleViewItemDecoration;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class GoodsActivity extends BaseActivity implements GoodsContract.View, NavigationBar.NavigationBarInteface {
    private static final String TAG = GoodsActivity.class.getSimpleName();
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_no_net)
    LinearLayout ll404;
    @BindView(R.id.btn_reload)
    Button btnReload;

    @Inject
    GoodsPresenter goodsPresenter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    GridLayoutManager gridLayoutManager;
    @Inject
    RecyclerAdapterWithHF recyclerAdapterWithHF;
    @Inject
    GoodsRecyclerViewAdapter mAdapter;

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_goods);

        ButterKnife.bind(this);

        DaggerGoodsComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .goodsModule(new GoodsModule(this))
                .build()
                .inject(this);
        initView();

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new RecycleViewItemDecoration((int) getResources().getDimension(R.dimen.default_7dp_hf)));
        mRecyclerView.setAdapter(recyclerAdapterWithHF);

        if (goodsPresenter != null) {
            showLoading();
            goodsPresenter.getDate();
        }

        RxView.clicks(btnReload).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (goodsPresenter != null) {
                            ll404.setVisibility(View.GONE);
                            showLoading();
                            goodsPresenter.getDate();
                        }
                    }
                });

    }

    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("精选商品");
        navigationBar.setCenterItemIconHidden();
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(GoodsActivity.this);
        }
        mLoadingDialog.showDialog();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.closeDialog();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(GoodsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(List<GoodsInfo> goodsInfos) {
        mRecyclerView.setVisibility(View.VISIBLE);
        ll404.setVisibility(View.GONE);

        mAdapter.setData(goodsInfos);
    }

    @Override
    public void showNoNet() {
        mRecyclerView.setVisibility(View.GONE);
        ll404.setVisibility(View.VISIBLE);
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
