package com.app.sy.syan.mine.address;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class ModifyAddressModule {
    ModifyAddressActivity addressActivity;

    public ModifyAddressModule(ModifyAddressActivity addressActivity) {
        this.addressActivity = addressActivity;
    }

    @ActivityScoped
    @Provides
    ModifyAddressContract.View provide() {
        return addressActivity;
    }
}
