package com.app.sy.syan.data.remote;

import com.app.sy.syan.data.request.CharacterBody;
import com.app.sy.syan.data.request.LoginBody;
import com.app.sy.syan.data.request.ModifyAddressBody;
import com.app.sy.syan.data.request.ModifyPwdBody;
import com.app.sy.syan.data.request.NoteBody;
import com.app.sy.syan.data.request.ProductDetailBody;
import com.app.sy.syan.data.request.UpdateCartNumBody;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface SyanServiceApi {
    //留言
    @POST("app/addOrder")
    Observable<ResponseBody> note(@Body NoteBody noteBody);

    //登陆
    @POST("app/applogin")
    Observable<ResponseBody> login(@Body LoginBody loginBody);

    //修改密码
    @POST("app/updatePwd")
    Observable<ResponseBody> modifyPwd(@Body ModifyPwdBody modifyPwdBody);

    //修改收货地址
    @POST("app/updateAddress")
    Observable<ResponseBody> modifyAddress(@Body ModifyAddressBody modifyAddressBody);

    //人物关系
    @POST("app/personRelation")
    Observable<ResponseBody> character(@Body CharacterBody characterBody);

    //今日福利
    @POST("app/todayWelfare")
    Observable<ResponseBody> todayWelfare(@Body CharacterBody characterBody);

    //我的
    @POST("app/getStaffInfo")
    Observable<ResponseBody> mine(@Body CharacterBody characterBody);

    //商品信息
    @POST("app/getProductList")
    Observable<ResponseBody> productList();

    //商品详情
    @POST("app/getProductByProductId")
    Observable<ResponseBody> productDetail(@Body ProductDetailBody productDetailBody);

    //商品详情页 购物车商品数量
    @POST("app/getCartNumTotal")
    Observable<ResponseBody> productDetailCartCount(@Body CharacterBody characterBody);

    //商品详情页 加入购物车操作
    @POST("app/getCartNumTotal")
    Observable<ResponseBody> updateCartNum(@Body UpdateCartNumBody updateCartNumBody);

    //获取购物车所有商品
    @POST("app/getCartlist")
    Observable<ResponseBody> getCartList(@Body CharacterBody characterBody);

    //公益
    @POST("app/getLoveFee")
    Observable<ResponseBody> getLoveFee();

    //公司简介
    @POST("app/getLoveFee")
    Observable<ResponseBody> getIntroduce();

}
