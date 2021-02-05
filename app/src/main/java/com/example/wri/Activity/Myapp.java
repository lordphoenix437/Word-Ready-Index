package com.example.wri.Activity;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
