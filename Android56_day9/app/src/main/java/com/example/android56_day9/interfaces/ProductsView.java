package com.example.android56_day9.interfaces;

import com.example.android56_day9.models.AllProductsResponse;
import com.example.android56_day9.models.Product;

import java.util.ArrayList;

public interface ProductsView {
    void loadAllProductSuccess(AllProductsResponse allProductsResponse);

    void loadAllProductFail(String message);

    void loadAllProductLocal(ArrayList<Product> products);

    void insertProductSuccess();

    void insertProductFail(String message);

}
