package com.app.sy.syan.goods.detail;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class,modules = GoodsDetailModule.class)
public interface GoodsDetailComponent {
    void inject(GoodsDetailActivity activity);
}
