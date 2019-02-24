package com.app.sy.syan.goods;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class,modules = GoodsModule.class)
public interface GoodsComponent {
    void inject(GoodsActivity goodsFragment);
}
