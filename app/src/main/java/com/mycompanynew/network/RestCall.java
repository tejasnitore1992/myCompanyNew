package com.mycompanynew.network;


import com.mycompanynew.about_us.response.AboutUsResponse;
import com.mycompanynew.home.response.DashboardResponse;
import com.mycompanynew.life_at_my_company.response.ApplyOpeningResponse;
import com.mycompanynew.life_at_my_company.response.CurrentOpeningResponse;
import com.mycompanynew.our_services.response.OurServicesResponse;
import com.mycompanynew.our_services.response.ServiceDetailMoreResponse;
import com.mycompanynew.our_services.response.ServicesDetailsResponse;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Single;

public interface RestCall {

    @FormUrlEncoded
    @POST("front_dashboard_controller.php")
    Single<DashboardResponse> getDashboardData(
            @Field("getDashboardData") String getDashboardData,
            @Field("society_id") String society_id,
            @Field("language_id") String language_id);

    @FormUrlEncoded
    @POST("front_about_us_controller.php")
    Single<AboutUsResponse> getAboutUsData(
            @Field("getAboutUs") String getAboutUs,
            @Field("society_id") String society_id,
            @Field("language_id") String language_id);

    @FormUrlEncoded
    @POST("current_opening_controller.php")
    Single<CurrentOpeningResponse> getCurrentOpeningData(
            @Field("getCurrentOpening") String getCurrentOpening,
            @Field("society_id") String society_id,
            @Field("language_id") String language_id);


    @FormUrlEncoded
    @POST("our_service_controller.php")
    Single<OurServicesResponse> getOurServiceData(
            @Field("getOurServices") String getOurServices,
            @Field("society_id") String society_id,
            @Field("language_id") String language_id);

    @FormUrlEncoded
    @POST("our_service_controller.php")
    Single<ServicesDetailsResponse> getOurServiceDetailData(
            @Field("getOurServicesDetails") String getOurServicesDetails,
            @Field("society_id") String society_id,
            @Field("language_id") String language_id,
            @Field("company_service_id") String company_service_id);

    @FormUrlEncoded
    @POST("our_service_controller.php")
    Single<ServiceDetailMoreResponse> getOurServiceDetailMoreData(
            @Field("getOurServicesDetailsMore") String getOurServicesDetails,
            @Field("society_id") String society_id,
            @Field("language_id") String language_id,
            @Field("company_service_details_id") String company_service_details_id);

    @Multipart
    @POST("current_opening_controller.php")
    Single<ApplyOpeningResponse> applyOpening(
            @Part("applyOpening") String applyOpening,
            @Part("society_id") String society_id,
            @Part("language_id") String language_id,
            @Part("company_current_opening_id") String company_current_opening_id,
            @Part("company_current_opening_name") String company_current_opening_name,
            @Part("company_current_opening_dob") String company_current_opening_dob,
            @Part("company_current_opening_gender") String company_current_opening_gender,
            @Part("company_current_opening_state") String company_current_opening_state,
            @Part("company_current_opening_city") String company_current_opening_city,
            @Part("company_current_opening_contact_no") String a,
            @Part("company_current_opening_email_id") String company_current_opening_email_id,
            @Part("company_current_opening_education_info") String company_current_opening_education_info,
            @Part("company_current_opening_experience") String company_current_opening_experience,
            @Part("company_current_opening_resume") MultipartBody.Part company_current_opening_resume);

}
