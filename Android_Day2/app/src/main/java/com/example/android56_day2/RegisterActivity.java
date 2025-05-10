package com.example.android56_day2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android56_day2.models.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private ArrayList<User> users = new ArrayList<>();
    private EditText edtUserName;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtEmail;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtEmail = findViewById(R.id.edtEmail);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            users = (ArrayList<User>) getIntent().getSerializableExtra("users");
            if (users == null) {
                users = new ArrayList<>();
            }
            if (register(edtUserName.getText().toString(),
                    edtPassword.getText().toString(),
                    edtConfirmPassword.getText().toString(),
                    edtEmail.getText().toString())) {
                Intent intent = new Intent();
                intent.putExtra("users", users);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private boolean register(String userName, String password, String confirmPassword, String email) {
        if (users != null) {
            for(User user : users) {
                if (user.userName.equals(userName)) {
                    Toast.makeText(RegisterActivity.this, "User name already exists", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (user.email.equals(email)) {
                    Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Not allow empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Confirm password does not match password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            Toast.makeText(RegisterActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        }

        User user = new User(userName, password, email);
        users.add(user);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}