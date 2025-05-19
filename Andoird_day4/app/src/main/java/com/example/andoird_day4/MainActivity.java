package com.example.andoird_day4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText edtDemo;
    private TextView tvDemo;
    private Button btnDemo;
    private ImageView imgDemo;
    private CheckBox cbRememberPassword;
    private static final String IMAMAGE_DEMO = "https://static.wikia.nocookie.net/makeine/images/9/96/BasoriLN8.png/revision/latest?cb=20250404124936";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        edtDemo = findViewById(R.id.edtDemo);
        tvDemo = findViewById(R.id.tvDemo);
        btnDemo = findViewById(R.id.btnDemo);
        imgDemo = findViewById(R.id.imgDemo);
        cbRememberPassword = findViewById(R.id.cbRememberPassword);
        tvDemo.setOnClickListener(v -> {
            String textValue = edtDemo.getText().toString();
            Log.d(TAG, "onclick: " + textValue);
        });

        edtDemo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: " + s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: " + s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: " + s.toString());
            }
        });

        btnDemo.setOnClickListener(v -> {
            String textValue = edtDemo.getText().toString();
            Toast.makeText(this, textValue, Toast.LENGTH_SHORT).show();
            boolean isCheckedRememberPassword = cbRememberPassword.isChecked();
            Log.d(TAG, "onclick: " + isCheckedRememberPassword);
        });

        btnDemo.setOnLongClickListener(v -> {
            return false;
        });

        Glide.with(this).load(IMAMAGE_DEMO)
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imgDemo);
    }
}