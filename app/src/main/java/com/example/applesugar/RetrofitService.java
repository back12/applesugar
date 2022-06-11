package com.example.applesugar;

import androidx.room.Query;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("api/user/add")
    Call<ResponseBody> signIn(@Field("username") int username, @Field("password") int password);

    @FormUrlEncoded
    @POST("api/user/add")
    Call<ResponseBody> updateAvatar(@Field("id") int id, @Field("avatarurl") int avatarUrl);


    @GET("api/auth/login")
    Call<ResponseBody> login(@Query("username") String username, @Query("password") String password);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://127.0.0.1:9999")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
