package com.example.pranto;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCl {
    private static  final String BASE_URL = "http://157.245.231.80/api/test/android/";

    private static RetrofitCl mInstance;

    private Retrofit retrofit;

    private RetrofitCl() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    static synchronized RetrofitCl getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitCl();
        }
        return mInstance;
    }

    API getApi(){
        return retrofit.create(API.class);
    }


}
