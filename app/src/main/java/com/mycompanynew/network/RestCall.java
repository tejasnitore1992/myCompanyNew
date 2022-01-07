package com.mycompanynew.network;


import com.mycompanynew.home.response.DashboardResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Single;

public interface RestCall {

    @FormUrlEncoded
    @POST("front_dashboard_controller.php")
    Single<DashboardResponse> getDashboardData(
            @Field("getDashboardData") String getDashboardData,
            @Field("society_id") String society_id,
            @Field("language_id") String language_id);
}