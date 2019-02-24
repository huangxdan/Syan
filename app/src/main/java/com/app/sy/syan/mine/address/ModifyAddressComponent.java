package com.app.sy.syan.mine.address;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = ModifyAddressModule.class)
public interface ModifyAddressComponent {
    void inject(ModifyAddressActivity addressActivity);
}
