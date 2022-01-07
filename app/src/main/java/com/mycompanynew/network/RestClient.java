package com.mycompanynew.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static <S> S createService(Class<S> serviceClass, String baseurl, String key) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient(key,key,key))
                .addConverterFactory(GsonConverterFactory.create(gson))
/*
                .addConverterFactory(ScalarsConverterFactory.create())
*/
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());


        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }


    public static <S> S createService(Class<S> serviceClass, String baseurl, String key, String userName, String password) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient(key,userName,password))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
