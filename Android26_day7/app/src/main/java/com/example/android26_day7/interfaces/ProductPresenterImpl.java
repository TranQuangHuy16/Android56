package com.example.android26_day7.interfaces;

public interface ProductPresenterImpl {
    void getAllProducts();

   void getAllProducts(int limit);

    void getProductById(int id);

    void searchProduct(String productName);
    void getAllCategories();
}
