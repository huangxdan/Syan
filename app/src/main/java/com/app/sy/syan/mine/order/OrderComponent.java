package com.app.sy.syan.mine.order;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = OrderModule.class)
public interface OrderComponent {
    void inject(OrderActivity orderActivity);
}
