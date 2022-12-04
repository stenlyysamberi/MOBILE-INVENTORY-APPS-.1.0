package com.example.rcgnwhrsinventory.Internet;

import com.example.rcgnwhrsinventory.Model.Main;
import com.example.rcgnwhrsinventory.Model.Mdetail;
import com.example.rcgnwhrsinventory.Model.ResponseJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<ResponseJson> store(
            @Field("material_name") String material_name,
            @Field("material_number") String material_number,
            @Field("container") String container,
            @Field("oum") String oum,
            @Field("file") String file);

    @DELETE("stock/del")
    Call<ResponseJson> deleted(
            @Query("material_number") String material_number);

    @FormUrlEncoded
    @POST("stock/add")
    Call<ResponseJson> added(
            @Field("id_material") String id_material,
            @Field("id_employee") String id_employee,
            @Field("remark") String remark ,
            @Field("total") String total,
            @Field("status") String status);

    @GET("beranda/all")
    Call<Main> beranda();

    @GET("stock/all")
    Call<Main> viewAll();

    @FormUrlEncoded
    @POST("stock/search/only")
    Call<Mdetail> details(
            @Field("id_material") String id_material);

}
