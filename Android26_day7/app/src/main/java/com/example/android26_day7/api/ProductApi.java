package com.example.android26_day7.api;

import com.example.android26_day7.constants.ConstantApi;
import com.example.android26_day7.models.AllProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApi {
    @GET(ConstantApi.GET_ALL_PRODUCTS)
    Call<AllProductsResponse> getAllProducts();

    @GET(ConstantApi.GET_SINGLE_PRODUCTS)
    Call<AllProductsResponse> getProductById(@Path("id") int id);
}
