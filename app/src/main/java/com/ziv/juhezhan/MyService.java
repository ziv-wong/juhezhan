package com.ziv.juhezhan;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyService {

    @GET("/hupu")
    Call<ResponseBody> getHupu();

    @GET("/zhihu-daily")
    Call<ResponseBody> getZhihu();

    @GET("/douban")
    Call<ResponseBody> getDouban();

    @GET("/douyu-wzry")
    Call<ResponseBody> getDouyuWzry();

    @GET("/douyu-game")
    Call<ResponseBody> getDouyuGame();

    @GET("/weibo-realtimehot")
    Call<ResponseBody> getWeibo();

    @GET("/{path}/")
    Call<ResponseBody> getOther(@Path("path") String path);

    @GET("/{path}/")
    Call<ResponseBody> getData(@Path("path") String path);

}
