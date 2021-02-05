package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wri.Adapter.Admin_listClass_Adapter;
import com.example.wri.Model.Classs;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_List_Class extends AppCompatActivity {
    RecyclerView rcv_admin_listClass;
    Admin_listClass_Adapter listClass_adapter;
    ArrayList<Classs> classsArrayList;
    Button btn_create_class_adminlist;
    EditText edt_search_listallclass_admin;
    ImageView img_search_listallclass_admin;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__list__class);
        init();
        key = edt_search_listallclass_admin.getText().toString();
        Getdata(key);
        img_search_listallclass_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Getdata(key);
            }
        });
        btn_create_class_adminlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Admin_Add_Class.class));
            }
        });
        edt_search_listallclass_admin.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listClass_adapter.getFilter().filter(s);
            }
        });
    }

    private void init() {
        rcv_admin_listClass = findViewById(R.id.rcv_admin_listClass);
        btn_create_class_adminlist = findViewById(R.id.btn_create_class_adminlist);
        edt_search_listallclass_admin = findViewById(R.id.edt_search_listallclass_admin);
        img_search_listallclass_admin = findViewById(R.id.img_search_listallclass_admin);
    }

    private void Getdata(String keyword) {
        DataService dataService = APIService.getService();
        Call<List<Classs>> callback = dataService.getAllClass(keyword);
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                classsArrayList = (ArrayList<Classs>)response.body();
                listClass_adapter = new Admin_listClass_Adapter(Admin_List_Class.this,classsArrayList);
                rcv_admin_listClass.setLayoutManager(new LinearLayoutManager(Admin_List_Class.this));
                rcv_admin_listClass.setAdapter(listClass_adapter);
            }

            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });
    }

    public void onBack(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        Getdata(key);
        super.onResume();
    }
}