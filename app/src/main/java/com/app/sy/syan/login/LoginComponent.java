package com.app.sy.syan.login;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

/**
 * date 2018/5/8
 * version
 * describe
 *
 * @author hxd
 */
@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
