package com.app.sy.syan.mine.car;

import android.support.v7.widget.LinearLayoutManager;

import com.app.sy.syan.scope.ActivityScoped;
import com.app.sy.syan.util.RecyclerAdapterWithHF;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModule {
    CarActivity carActivity;

    public CarModule(CarActivity carActivity) {
        this.carActivity = carActivity;
    }

    @ActivityScoped
    @Provides
    CarContract.View provide() {
        return carActivity;
    }

    @ActivityScoped
    @Provides
    CarGoodsAdapter provideAdapter(){
        return new CarGoodsAdapter(carActivity);
    }

    @Provides
    @ActivityScoped
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(carActivity);
    }

    @Provides
    @ActivityScoped
    RecyclerAdapterWithHF providerRecyclerAdapterWithHF(CarGoodsAdapter carGoodsAdapter) {
        return new RecyclerAdapterWithHF(carGoodsAdapter);
    }
}
