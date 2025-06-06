package com.example.android26_day7.presenters;

import com.example.android26_day7.api.ProductApi;
import com.example.android26_day7.interfaces.ProductPresenterImpl;
import com.example.android26_day7.interfaces.ProductViewImpl;
import com.example.android26_day7.models.AllProductsResponse;
import com.example.android26_day7.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter implements ProductPresenterImpl {

    private ProductViewImpl mProductView;
    private ProductApi mProductApi;

    public  ProductPresenter(ProductViewImpl productView) {
        this.mProductView = productView;

        if (mProductApi == null) {
            mProductApi = RetrofitClient.getProductApi();
        }
    }

    private Callback<AllProductsResponse> getAllProductsCallback = new Callback<AllProductsResponse>() {
        @Override
        public void onResponse(Call<AllProductsResponse> call, Response<AllProductsResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                if (response.code() == 200) {
                    mProductView.getAllProductSuccess(response.body());
                } else {
                    mProductView.getAllProductFailed(response.code() + "", response.message());
                }
            } else {
                mProductView.getAllProductFailed(response.code() + "", response.message());
            }
        }

        @Override
        public void onFailure(Call<AllProductsResponse> call, Throwable t) {
            mProductView.getAllProductFailed("500", t.getMessage());
        }
    };

    @Override
    public void getAllProducts() {
        mProductApi.getAllProducts().enqueue(getAllProductsCallback);
    }

    @Override
    public void getAllProducts(int limit) {

    }

    @Override
    public void getProductById(int id) {

    }

    @Override
    public void searchProduct(String productName) {

    }

    @Override
    public void getAllCategories() {

    }
}
