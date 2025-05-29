package com.example.android56_day6;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android56_day6.adapters.ProductAdapter;
import com.example.android56_day6.interfaces.ProductClickListener;
import com.example.android56_day6.models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView rvDemo;
    private ArrayList<Product> mListProduct;
    private ProductAdapter mProductAdapter;
    private Button btnAdd;
    private Button btnRemove;
    private Button btnUpdate;
    private EditText edtPosition;
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
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtPosition = findViewById(R.id.edtPosition);


        mProductAdapter = new ProductAdapter(mListProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDemo.setLayoutManager(linearLayoutManager);
        rvDemo.setHasFixedSize(true);
        rvDemo.setAdapter(mProductAdapter);

        mProductAdapter.setmProductClickListener(new ProductClickListener() {
            @Override
            public void onItemClickListener(int position, Product product) {
                Log.d(TAG, "onItemClickListener: " + position);
            }
        });

        btnAdd.setOnClickListener(v -> {
            String positionText = edtPosition.getText().toString();
            int position = Integer.parseInt(positionText);
            if (isValidPosition(position)) {
                addProduct(position);
            }
        });

        btnRemove.setOnClickListener(v -> {
            String positionText = edtPosition.getText().toString();
            int position = Integer.parseInt(positionText);
            if (isValidPosition(position)) {
                removeProduct(position);
            }
        });

        btnUpdate.setOnClickListener(v -> {
            String positionText = edtPosition.getText().toString();
            int position = Integer.parseInt(positionText);
            if (isValidPosition(position)) {
                editProduct(position);
            }
        });
    }

    private void editProduct(int position) {
        Product product = mListProduct.get(position);
        product.setProductName("Updated Product " + position);
        product.setProductThump("https://static.wikia.nocookie.net/makeine/images/9/96/BasoriLN8.png/revision/latest?cb=20250404124936");
        mProductAdapter.notifyItemChanged(position);
    }

    private void removeProduct(int position) {
        mListProduct.remove(position);
        mProductAdapter.notifyItemRemoved(position);
    }

    private void addProduct(int position) {
        Product product = new Product();
        product.setId(position+"");
        product.setProductName("New Product " + position);
        product.setProductThump("https://static.wikia.nocookie.net/makeine/images/9/96/BasoriLN8.png/revision/latest?cb=20250404124936");

        mListProduct.add(position, product);
        mProductAdapter.notifyItemInserted(position);
        rvDemo.smoothScrollToPosition(position);
    }

    private boolean isValidPosition(int position) {
        return position >= 0 && position < mListProduct.size();
    }
}