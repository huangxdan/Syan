package com.app.sy.syan.mine;

import com.app.sy.syan.scope.FragmentScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class MineModule {
    MineFragment mineFragment;

    public MineModule(MineFragment mineFragment) {
        this.mineFragment = mineFragment;
    }

    @FragmentScoped
    @Provides
    MineContract.View provideView() {
        return mineFragment;
    }
}
