package com.example.wri.Activity.Admin.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wri.Adapter.Admin_List_AllTeacher_Adapter;
import com.example.wri.Model.Teacher;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_List_AllTeacher extends AppCompatActivity {
    RecyclerView rcv_admin_listTeacher;
    Admin_List_AllTeacher_Adapter list_allTeacher_adapter;
    ArrayList<Teacher> teacherArrayList;
    ImageView id_back_admin_listteacher;
    Button btn_create_teacher_adminlist;
    EditText edt_search_allteacher_admin;
    ImageView search_allteacher_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__list__all_teacher);
        init();
        String keyword = edt_search_allteacher_admin.getText().toString();
        Getdata(keyword);
        id_back_admin_listteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_create_teacher_adminlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Admin_Create_Teacher.class));
            }
        });
        search_allteacher_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edt_search_allteacher_admin.getText().toString();
                Getdata(keyword);
            }
        });
    }

    private void Getdata(String keyword) {
        DataService dataService = APIService.getService();
        Call<List<Teacher>> callback = dataService.getAllTeacher_admin(keyword);
        callback.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                 teacherArrayList = (ArrayList<Teacher>)response.body();
                 list_allTeacher_adapter = new Admin_List_AllTeacher_Adapter(Admin_List_AllTeacher.this,teacherArrayList);
                 rcv_admin_listTeacher.setLayoutManager(new LinearLayoutManager(Admin_List_AllTeacher.this));
                 rcv_admin_listTeacher.setAdapter(list_allTeacher_adapter);
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {

            }
        });
    }

    private void init() {
        rcv_admin_listTeacher = findViewById(R.id.rcv_admin_listTeacher);
        id_back_admin_listteacher = findViewById(R.id.id_back_admin_listteacher);
        btn_create_teacher_adminlist =findViewById(R.id.btn_create_teacher_adminlist);
        edt_search_allteacher_admin= findViewById(R.id.edt_search_allteacher_admin);
        search_allteacher_admin = findViewById(R.id.search_allteacher_admin);
    }
}