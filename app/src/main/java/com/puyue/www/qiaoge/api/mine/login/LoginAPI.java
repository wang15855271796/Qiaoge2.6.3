package com.puyue.www.qiaoge.api.mine.login;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.login.LoginModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/18.
 */

public class LoginAPI {
    public interface LoginService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.LOGIN)
        Observable<LoginModel> setParams(@Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("verifyCode") String verifyCode,
                                         @Field("loginType") int loginType);
    }

    public static Observable<LoginModel> requestLogin(Context context, String phone, String password, String verifyCode, int loginType) {
        Observable<LoginModel> loginModelObservable = RestHelper.getBaseRetrofit(context).create(LoginService.class).setParams(phone, password, verifyCode, loginType);
        return loginModelObservable;
    }

    //登录后、更换手机号验证码验证
    public interface CheckYzmService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.Check_Yzm)
        Observable<BaseModel> setParams(@Field("phone") String phone,
                                        @Field("verifyCode") String verifyCode);
    }

    public static Observable<BaseModel> checkYzm(Context context, String phone, String verifyCode) {
        Observable<BaseModel> loginModelObservable = RestHelper.getBaseRetrofit(context).create(CheckYzmService.class).setParams(phone, verifyCode);
        return loginModelObservable;
    }

    //登录后、更换手机号获取验证码
    public interface GetYzmService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.Get_Yzm)
        Observable<BaseModel> setParams(@Field("phone") String phone);
    }

    public static Observable<BaseModel> getYzm(Context context, String phone) {
        Observable<BaseModel> loginModelObservable = RestHelper.getBaseRetrofit(context).create(GetYzmService.class).setParams(phone);
        return loginModelObservable;
    }

    // 登录后，更换手机号设置密码
    public interface SetSecretService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.Set_Secret)
        Observable<BaseModel> setParams(@Field("phone") String phone,@Field("password") String password);
    }

    public static Observable<BaseModel> setSecret(Context context, String phone, String password) {
        Observable<BaseModel> loginModelObservable = RestHelper.getBaseRetrofit(context).create(SetSecretService.class).setParams(phone,password);
        return loginModelObservable;
    }

    //验证登录密码
    public interface CheckSecretService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.Check_Secret)
        Observable<BaseModel> setParams(@Field("phone") String phone,@Field("password") String password);
    }

    public static Observable<BaseModel> checkSecret(Context context, String phone, String password) {
        Observable<BaseModel> loginModelObservable = RestHelper.getBaseRetrofit(context).create(CheckSecretService.class).setParams(phone,password);
        return loginModelObservable;
    }

    //验证是否第一次更换
    public interface CheckFirstService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.Check_First)
        Observable<BaseModel> setParams(@Field("phone") String phone);
    }

    public static Observable<BaseModel> checkFirst(Context context, String phone) {
        Observable<BaseModel> loginModelObservable = RestHelper.getBaseRetrofit(context).create(CheckFirstService.class).setParams(phone);
        return loginModelObservable;
    }
}
