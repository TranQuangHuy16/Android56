package com.example.android56_day9.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android56_day9.R;
import com.example.android56_day9.interfaces.ProductsView;
import com.example.android56_day9.models.AllProductsResponse;
import com.example.android56_day9.models.Product;
import com.example.android56_day9.presenters.ProductPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProductsView {
    private Button btnAddProduct;
    private Button btnUpdateProduct;
    private Button btnRemoveProduct;
    private TextView tvContent;
    private ProductPresenter mProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        loadAllProductFromLocal();
    }

    private void loadAllProductFromLocal() {
        mProductPresenter.loadLocalProducts();
    }

    private void initData() {
        mProductPresenter = new ProductPresenter(this, this);
        Product product = new Product();
        product.setId(1);
        product.setTitle("Product 1");
        product.setPrice(100.0);
        product.setDescription("This is product 1");
        product.setCategory("Category 1");
        product.setThumbnail("https://example.com/product1.jpg");

        mProductPresenter.addProduct(product);
    }

    private void initView() {
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnUpdateProduct = findViewById(R.id.btnUpdateProduct);
        btnRemoveProduct = findViewById(R.id.btnRemoveProduct);
        tvContent = findViewById(R.id.tvContent);
        btnAddProduct.setOnClickListener(v -> {
            addProudct();
        });

        btnUpdateProduct.setOnClickListener(v -> {
            btnUpdateProduct();
        });

        btnRemoveProduct.setOnClickListener(v -> {
            removeProduct();
        });
    }

    private void removeProduct() {
        int productId = 1;
        mProductPresenter.removeProduct(productId);
    }

    private void btnUpdateProduct() {
        String newTitle = "Updated Product Title";
        int productId = 1; // Assuming you want to update the product with ID 1
        mProductPresenter.updateProductTitle(productId, newTitle);
    }

    private void addProudct() {
    }

    @Override
    public void loadAllProductSuccess(AllProductsResponse allProductsResponse) {
        
    }

    @Override
    public void loadAllProductFail(String message) {

    }

    @Override
    public void loadAllProductLocal(ArrayList<Product> products) {
        if (products == null || products.isEmpty()) {
            tvContent.setText("No products found in local database.");
            return;
        }

        StringBuilder allProductLocal = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int id = product.getId();
            String title = product.getTitle();
            String description = product.getDescription();
            String category = product.getCategory();
            double price = product.getPrice();
            String thumbnail = product.getThumbnail();
            String data = id + "|" + title + "|" + description + "|" + category + "|" + price + "|" + thumbnail + "\n";

            allProductLocal.append(data);
        }

        tvContent.setText(allProductLocal.toString());
    }

    @Override
    public void insertProductSuccess() {
        Toast.makeText(this, "insertProductSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void insertProductFail(String message) {
        Toast.makeText(this, "insertProductSuccess", Toast.LENGTH_SHORT).show();
    }

}