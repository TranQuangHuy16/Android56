package com.example.android26_day7;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android26_day7.api.ProductApi;
import com.example.android26_day7.models.AllProductsResponse;
import com.example.android26_day7.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ProductApi productApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        productApi = RetrofitClient.getProductApi();

        productApi.getAllProducts().enqueue(new Callback<AllProductsResponse>() {
            @Override
            public void onResponse(Call<AllProductsResponse> call, Response<AllProductsResponse> response) {
                if (response.isSuccessful()) {
                    int code = response.code();
                    if (code == 200) {
                        Log.d(TAG, "onResponse: " + response.body().toString());
                        Log.d(TAG, "onResponse: " + response.body().getProducts().size());
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProductsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }
}