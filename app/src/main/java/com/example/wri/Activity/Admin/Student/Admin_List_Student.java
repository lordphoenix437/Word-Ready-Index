package com.example.wri.Activity.Admin.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wri.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Adapter.Admin_List_AllStudent_Adapter;
import com.example.wri.Model.Students;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_List_Student extends AppCompatActivity {
    RecyclerView rcv_admin_liststudent;
    Admin_List_AllStudent_Adapter liststudent_adapter;
    ArrayList<Students> studentsArrayList;
    ImageView search_allstudent_student_admin;
    EditText edt_search_allstudent_student_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__list__student);
        init();
        String keyword = edt_search_allstudent_student_admin.getText().toString();
        Getdata(keyword);
        search_allstudent_student_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String keyword = edt_search_allstudent_student_admin.getText().toString();
                  Getdata(keyword);
            }
        });
    }

    private void Getdata(String keyword) {
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getAllStudent_admin(keyword);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                studentsArrayList = (ArrayList<Students>)response.body();
                liststudent_adapter = new Admin_List_AllStudent_Adapter(Admin_List_Student.this,studentsArrayList);
                rcv_admin_liststudent.setLayoutManager(new LinearLayoutManager(Admin_List_Student.this));
                rcv_admin_liststudent.setAdapter(liststudent_adapter);
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {

            }
        });
    }

    private void init() {
        rcv_admin_liststudent = findViewById(R.id.rcv_admin_listStudent);
        search_allstudent_student_admin = findViewById(R.id.search_allstudent_student_admin);
        edt_search_allstudent_student_admin = findViewById(R.id.edt_search_allstudent_student_admin);
    }

    public void onBack(View view) {
        finish();
    }
}