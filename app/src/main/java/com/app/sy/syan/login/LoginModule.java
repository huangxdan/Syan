package com.app.sy.syan.login;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

/**
 * date 2018/5/8
 * version
 * describe
 *
 * @author hxd
 */
@Module
public class LoginModule {
    LoginActivity loginActivity;

    public LoginModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @ActivityScoped
    @Provides
    LoginContract.View provideView() {
        return loginActivity;
    }
}
