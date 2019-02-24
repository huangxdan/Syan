package com.app.sy.syan.goods.detail;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class GoodsDetailModule {
    GoodsDetailActivity activity;

    public GoodsDetailModule(GoodsDetailActivity activity) {
        this.activity = activity;
    }

    @ActivityScoped
    @Provides
    GoodsDetailContract.View provideView(){return activity;}
}
