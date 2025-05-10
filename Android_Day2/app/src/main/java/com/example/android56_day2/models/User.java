package com.example.android56_day2.models;

import java.io.Serializable;

public class User implements Serializable {
    public String userName;
    public String password;
    public String email;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public User() {
    }
}
