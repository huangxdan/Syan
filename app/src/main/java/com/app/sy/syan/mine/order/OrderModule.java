package com.app.sy.syan.mine.order;

import android.support.v7.widget.LinearLayoutManager;

import com.app.sy.syan.scope.ActivityScoped;
import com.app.sy.syan.util.RecyclerAdapterWithHF;

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
    OrderAdapter provideOrderAdapter() {
        return new OrderAdapter(orderActivity);
    }

    @Provides
    @ActivityScoped
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(orderActivity);
    }

    @Provides
    @ActivityScoped
    RecyclerAdapterWithHF providerRecyclerAdapterWithHF(OrderAdapter orderAdapter) {
        return new RecyclerAdapterWithHF(orderAdapter);
    }

    @ActivityScoped
    @Provides
    OrderContract.View provide() {
        return orderActivity;
    }
}
