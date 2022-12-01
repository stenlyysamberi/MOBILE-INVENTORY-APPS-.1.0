package com.example.rcgnwhrsinventory.Internet;

import com.example.rcgnwhrsinventory.Model.Main;
import com.example.rcgnwhrsinventory.Model.ResponseJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Endpoints {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseJson> login(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseJson> registerd(
            @Field("nama") String nama,
            @Field("contact_company") String contact_company,
            @Field("email") String email,
            @Field("company") String company
    );

    @FormUrlEncoded
    @POST("register/verify")
    Call<ResponseJson> verify(
            @Field("email") String email,
            @Field("token") String password);

    @FormUrlEncoded
    @POST("store")
    Call<ResponseJson> created(
            @Field("material_name") String material_name,
            @Field("material_number") String material_number,
            @Field("container") String container,
            @Field("oum") String oum,
            @Field("file") String file

    );


    @GET("stock")
    Call<Main> beranda();


}
