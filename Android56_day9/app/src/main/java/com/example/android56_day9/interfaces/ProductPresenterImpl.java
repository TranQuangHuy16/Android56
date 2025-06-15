package com.example.android56_day9.interfaces;

import com.example.android56_day9.models.Product;

import java.util.ArrayList;

public interface ProductPresenterImpl {
    void loadAllProducts();

    void saveProduct();

    void addProduct(Product product);

    void removeProduct(int id);

    void updateProduct(Product product);

    void loadLocalProducts();

    void updateProductTitle(int id, String title);

}
