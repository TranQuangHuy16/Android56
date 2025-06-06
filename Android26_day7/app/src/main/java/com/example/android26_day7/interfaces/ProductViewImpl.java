package com.example.android26_day7.interfaces;

import com.example.android26_day7.models.AllProductsResponse;
import com.example.android26_day7.models.Product;

public interface ProductViewImpl {
    void getAllProductSuccess(AllProductsResponse response);

    void getAllProductFailed(String code, String message);

    void getProductByIdSuccess(Product product);

    void getProductByIdFailed(String code, String message);

    void searchProductSuccess(AllProductsResponse response);

    void searchProductFailed(String code, String message);
}
