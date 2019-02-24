package com.app.sy.syan.mine.car;

import com.app.sy.syan.scope.ActivityScoped;

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
}
