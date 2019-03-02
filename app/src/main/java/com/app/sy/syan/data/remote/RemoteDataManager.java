package com.app.sy.syan.data.remote;

import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.util.Constant;
import com.app.sy.syan.util.NetUtil;
import com.app.sy.syan.util.PreferenceUtils;
import com.app.sy.syan.util.SystemUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RemoteDataManager {
    private SyanApplication application;

    @Inject
    public RemoteDataManager(SyanApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SyanServiceApi provideServiceAPi() {
        //log打印
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.header("User-Agent", UserAgent.instance(application));
                        builder.addHeader("Content-Type", "application/json;charset=UTF-8")
                                .addHeader("authinfo", "" + PreferenceUtils.getPrefString(application, Constant.KEY_PASSWD, ""))
                                .addHeader("buriedPoint", "{\"channelNo\":\""
                                        + PreferenceUtils.getPrefString(application, Constant.KEY_CHANNEL, "")
                                        + "\",\"deviceType\":\"android\",\"deviceModel\":\"" + SystemUtil.getSystemModel()
                                        + "\",\"uid\":\"" + PreferenceUtils.getPrefString(application, Constant.KEY_UID, "")
                                        + "\",\"deviceToken\":\"" + PreferenceUtils.getPrefString(application, Constant.ONLY_DEVICE_TOKEN, "")
                                        + "\",\"appVer\":\"" + PreferenceUtils.getPrefString(application, Constant.KEY_APP_VERSION_NAME, "")
                                        + "\",\"devImei\":\"" + PreferenceUtils.getPrefString(application, Constant.KEY_APP_DEVICE_ID, "")
                                        + "\",\"sysVersion\":\"" + SystemUtil.getSystemVersion()
                                        + "\",\"netType\":\"" + NetUtil.getNetworkType(application)
                                        + "\",\"clp\":\"" + NetUtil.getIP(application)
                                        + "\",\"loc\":\"" + PreferenceUtils.getPrefString(application, Constant.KEY_APP_LOCATION, "")
                                        + "\",\"appCode\":\"" + PreferenceUtils.getPrefString(application, Constant.KEY_APP_VERSION_CODE, "") + "\"}");


                        return chain.proceed(builder.build());
                    }
                })
                .addNetworkInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HostAddress.BASE_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RXJavaAdapter
                .client(client)
                .build();
        return retrofit.create(SyanServiceApi.class);
    }

    public static class HostAddress {

//        public static String BASE_ADDRESS = "http://zhaoheng0711.picp.io:39264/";
//        public static String BASE_ADDRESS = "http://39.105.28.53:8775/";
        public static String BASE_ADDRESS = "http://47.93.7.28:8775/";



    }
}
