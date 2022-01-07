package com.mycompanynew.network;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Single;

public interface RestCall {

    @FormUrlEncoded
    @POST("kbg_controller.php")
    Single<Object> getDashboardData(
            @Field("getDashboardData") String getDashboardData,
            @Field("society_id") String society_id,
            @Field("language_id") String language_id);
}
