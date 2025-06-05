package com.example.android26_day7.api;

import com.example.android26_day7.constants.ConstantApi;
import com.example.android26_day7.models.AllProductsResponse;
import com.example.android26_day7.models.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApi {
    @GET(ConstantApi.GET_ALL_PRODUCTS)
    Call<AllProductsResponse> getAllProducts();

    @GET(ConstantApi.GET_ALL_PRODUCTS)
    Call<AllProductsResponse> get100Products(@Query("limit") int limit);

    @GET(ConstantApi.GET_SINGLE_PRODUCTS)
    Call<Product> getProductById(@Path("id") int id);

    @GET(ConstantApi.SEARCH_PRODUCT)
    Call<AllProductsResponse> searchProduct(@Query("q") String productName);
}
