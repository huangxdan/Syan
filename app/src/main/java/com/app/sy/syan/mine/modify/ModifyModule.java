package com.app.sy.syan.mine.modify;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class ModifyModule {
    ModifyActivity modifyActivity;

    public ModifyModule(ModifyActivity modifyActivity) {
        this.modifyActivity = modifyActivity;
    }

    @ActivityScoped
    @Provides
    ModifyContract.View provideView(){
        return modifyActivity;
    }
}
