package com.example.android26_day7;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android26_day7.api.ProductApi;
import com.example.android26_day7.interfaces.ProductViewImpl;
import com.example.android26_day7.models.AllProductsResponse;
import com.example.android26_day7.models.Product;
import com.example.android26_day7.presenters.ProductPresenter;
import com.example.android26_day7.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ProductViewImpl {
    private static final String TAG = "MainActivity";
    private ProductPresenter mProductPresenter;
    private EditText edtSearch;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initView();

        mProductPresenter = new ProductPresenter(this);

        mProductPresenter.getAllProducts();
    }

    private void initView() {
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(v -> {
            String keyword = edtSearch.getText().toString();
//            searchProduct(keyword);
        });
    }



    @Override
    public void getAllProductSuccess(AllProductsResponse response) {
        Log.d(TAG, "getAllProductSuccess: " + response.getProducts().size());
    }

    @Override
    public void getAllProductFailed(String code, String message) {
        Log.d(TAG, "getAllProductFailed: " + message);
    }

    @Override
    public void getProductByIdSuccess(Product product) {

    }

    @Override
    public void getProductByIdFailed(String code, String message) {

    }

    @Override
    public void searchProductSuccess(AllProductsResponse response) {

    }

    @Override
    public void searchProductFailed(String code, String message) {

    }
}