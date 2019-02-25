package com.app.sy.syan.mine.order.confirm;

import android.support.v7.widget.LinearLayoutManager;

import com.app.sy.syan.scope.ActivityScoped;
import com.app.sy.syan.util.RecyclerAdapterWithHF;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfirmModule {
    ConfirmActivity confirmActivity;

    public ConfirmModule(ConfirmActivity confirmActivity) {
        this.confirmActivity = confirmActivity;
    }

    @ActivityScoped
    @Provides
    ConfirmContract.View provide() {
        return confirmActivity;
    }

    @Provides
    @ActivityScoped
    ConfirmGoodsAdapter provideAdapter() {
        return new ConfirmGoodsAdapter(confirmActivity);
    }

    @Provides
    @ActivityScoped
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(confirmActivity);
    }

    @Provides
    @ActivityScoped
    RecyclerAdapterWithHF providerRecyclerAdapterWithHF(ConfirmGoodsAdapter carGoodsAdapter) {
        return new RecyclerAdapterWithHF(carGoodsAdapter);
    }
}
