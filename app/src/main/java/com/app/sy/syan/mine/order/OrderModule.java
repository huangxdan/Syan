package com.app.sy.syan.mine.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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
        return new LinearLayoutManager(orderActivity) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
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
