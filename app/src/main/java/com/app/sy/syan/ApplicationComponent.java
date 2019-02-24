package com.app.sy.syan;

import android.content.Context;

import com.app.sy.syan.data.local.LocalDataManager;
import com.app.sy.syan.data.remote.RemoteDataManager;
import com.app.sy.syan.data.remote.SyanServiceApi;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, LocalDataManager.class, RemoteDataManager.class})
public interface ApplicationComponent {
    Context getApplication();

//    DaoSession getDaoSession();

    SyanServiceApi getSyanServiceApi();

    //建立数据库
//    SlopeDataBaseHelper getSlopeDataBaseHelper();


}
