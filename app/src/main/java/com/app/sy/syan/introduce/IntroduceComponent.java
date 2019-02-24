package com.app.sy.syan.introduce;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;


@ActivityScoped
@Component(dependencies = ApplicationComponent.class,modules = IntroduceModule.class)
public interface IntroduceComponent {
    void inject(IntroduceActivity activity);
}
