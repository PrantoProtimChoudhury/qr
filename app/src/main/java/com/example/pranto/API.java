package com.example.pranto;


import androidx.annotation.NonNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface API {

    @NonNull
    @FormUrlEncoded
    @POST("get-user-info")
    Call<ResponseBody> check(
            @Field("ticket_id") @NonNull String id
    );


}
