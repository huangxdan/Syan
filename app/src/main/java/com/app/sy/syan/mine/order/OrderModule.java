package com.app.sy.syan.mine.order;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class OrderModule {
    OrderActivity orderActivity;

    public OrderModule(OrderActivity orderActivity) {
        this.orderActivity = orderActivity;
    }

    @ActivityScoped
    @Provides
    OrderContract.View provide() {
        return orderActivity;
    }
}
