package com.example.android56_day9.presenters;

import android.content.Context;

import com.example.android56_day9.databases.ProductDatabaseHelper;
import com.example.android56_day9.interfaces.ProductPresenterImpl;
import com.example.android56_day9.interfaces.ProductsView;
import com.example.android56_day9.interfaces.db_product_listener.InsertProductListener;
import com.example.android56_day9.models.Product;

import java.util.ArrayList;

public class ProductPresenter implements ProductPresenterImpl {
    private ProductsView mProductsView;
    private ProductDatabaseHelper mProductDatabaseHelper;

    public ProductPresenter(Context context, ProductsView mProductsView) {
        this.mProductsView = mProductsView;

        if (mProductDatabaseHelper == null) {
            mProductDatabaseHelper = new ProductDatabaseHelper(context);
        }
    }

    @Override
    public void loadAllProducts() {

    }

    @Override
    public void saveProduct() {

    }

    @Override
    public void addProduct(Product product) {
        mProductDatabaseHelper.insertProduct(product, new InsertProductListener() {
            @Override
            public void onInsertProductSuccess() {
                mProductsView.insertProductSuccess();
            }

            @Override
            public void onInsertProductFail(String message) {
                mProductsView.insertProductFail(message);
            }
        });
    }

    @Override
    public void removeProduct(int id) {
        mProductDatabaseHelper.removeProduct(id);
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void loadLocalProducts() {
        ArrayList<Product> listLocal = mProductDatabaseHelper.getAllProductFromLocal();
        mProductsView.loadAllProductLocal(listLocal);
    }

    @Override
    public void updateProductTitle(int id, String title) {
        mProductDatabaseHelper.updateProductTitle(id, title);
    }
}
