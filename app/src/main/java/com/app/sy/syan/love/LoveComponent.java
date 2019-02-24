package com.app.sy.syan.love;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = ApplicationComponent.class,modules = LoveModule.class)
public interface LoveComponent {
    void inject(LoveFragment loveFragment);
}
