package com.example.android56_day2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android56_day2.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;

    private ArrayList<User> users = new ArrayList<>();
    private static final String USER_DEFAULT = "huytq";
    private static final String PASS_DEFAULT = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: ");

        initView();

//        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARE_PREF_NAME, MODE_PRIVATE);
//        String name = "Huy";
//        sharedPreferences.edit().putString(Constants.USER_NAME, name).apply();

        initFragments();
    }

    private void initFragments() {
        // add SettingFragment
        SettingFragment settingFragment = SettingFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flFirst, settingFragment)
                .commit();

        // Replce SecondFragment
        SecondFragment secondFragment = SecondFragment.newInstance("", "");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flSecond, secondFragment)
                .commit();

//        getSupportFragmentManager().beginTransaction().remove(secondFragment).commit();
    }

    private void initView() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Login");
            String userNameValue = edtUserName.getText().toString();
            String passwordValue = edtPassword.getText().toString();

            login(userNameValue, passwordValue);
        });

        btnRegister.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Register");
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            intent.putExtra("users", users);
            homeLauncherRegister.launch(intent);
        });
    }


    private void login(String userNameValue, String passwordValue) {
        boolean isValid = false;
        if (userNameValue.equals(USER_DEFAULT) && passwordValue.equals(PASS_DEFAULT)) {
            isValid = true;
        } else if (users != null) {
            for (User user : users) {
                if (user.userName.equals(userNameValue) && user.password.equals(passwordValue)) {
                    isValid = true;
                    break;
                }
            }
        }

        if (isValid) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("userName", userNameValue);
            homeLauncherLogin.launch(intent);
        } else {
            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
        }

    }

    private ActivityResultLauncher<Intent> homeLauncherLogin = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            String name = result.getData().getStringExtra("name");
            Toast.makeText(MainActivity.this, "USER NAME " + name + " IS LOGGED OUT", Toast.LENGTH_SHORT).show();
        }
    });

    private ActivityResultLauncher<Intent> homeLauncherRegister = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            users = (ArrayList<User>) result.getData().getSerializableExtra("users");
            if (users != null && !users.isEmpty()) {
                User newUser = users.get(users.size() - 1); // Lấy user vừa đăng ký
                edtUserName.setText(newUser.userName);
                edtPassword.setText(newUser.password);
            }
        }
    });

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}