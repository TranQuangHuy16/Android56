package com.example.android56_day2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private String userNameValue;
    private TextView tvWelcome;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initView() {
        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("name", userNameValue);
            setResult(RESULT_OK, intent);
            finish();
        });

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        String name = sharedPreferences.getString(Constants.USER_NAME, "default");
        Toast.makeText(HomeActivity.this, name, Toast.LENGTH_SHORT).show();
    }

    private void initData() {
         userNameValue = getIntent().getStringExtra("userName");

        tvWelcome.setText("Xin chào " + userNameValue + "!");
    }
}