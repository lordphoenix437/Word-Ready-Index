package com.example.wri.Activity.Admin.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.wri.R;

public class Admin_Company_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__company__list);

    }

    public void onBack(View view) {
        finish();
    }
}