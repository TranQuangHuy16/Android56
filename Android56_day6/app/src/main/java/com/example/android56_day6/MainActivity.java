package com.example.android56_day6;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android56_day6.adapters.ProductAdapter;
import com.example.android56_day6.models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDemo;
    private ArrayList<Product> mListProduct;
    private ProductAdapter mProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }
    private void initData() {
        mListProduct = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Product product = new Product();
            product.setId(i+"");
            product.setProductName("Product " + i);
            product.setProductThump("https://static.wikia.nocookie.net/makeine/images/9/96/BasoriLN8.png/revision/latest?cb=20250404124936");

            mListProduct.add(product);
        }

    }
    private void initView() {
        rvDemo = findViewById(R.id.rvDemo);

        mProductAdapter = new ProductAdapter(mListProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDemo.setLayoutManager(linearLayoutManager);
        rvDemo.setAdapter(mProductAdapter);
    }


}