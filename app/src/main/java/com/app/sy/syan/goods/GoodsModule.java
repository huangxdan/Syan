package com.app.sy.syan.goods;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.app.sy.syan.scope.ActivityScoped;
import com.app.sy.syan.util.RecyclerAdapterWithHF;

import dagger.Module;
import dagger.Provides;

@Module
public class GoodsModule {
    GoodsActivity goodsFragment;

    public GoodsModule(GoodsActivity goodsFragment) {
        this.goodsFragment = goodsFragment;
    }

    @ActivityScoped
    @Provides
    GoodsContract.View provideView() {
        return goodsFragment;
    }

    @Provides
    @ActivityScoped
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(goodsFragment);
    }

    @Provides
    @ActivityScoped
    GridLayoutManager provideGridLayoutManager() {
        return new GridLayoutManager(goodsFragment,2);
    }

    @Provides
    @ActivityScoped
    GoodsRecyclerViewAdapter providerGoodsRecyclerViewAdapter() {
        return new GoodsRecyclerViewAdapter(goodsFragment);
    }

    @Provides
    @ActivityScoped
    RecyclerAdapterWithHF providerRecyclerAdapterWithHF(GoodsRecyclerViewAdapter goodsRecyclerViewAdapter) {
        return new RecyclerAdapterWithHF(goodsRecyclerViewAdapter);
    }
}
