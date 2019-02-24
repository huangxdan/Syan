package com.app.sy.syan.mine;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.FragmentScoped;

import dagger.Component;

/**
 * date 2018/5/8
 * version
 * describe
 *
 * @author hxd
 */
@FragmentScoped
@Component(dependencies = ApplicationComponent.class, modules = MineModule.class)
public interface MineComponent {
    void inject(MineFragment mineFragment);
}
