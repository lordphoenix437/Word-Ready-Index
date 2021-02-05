package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.wri.Adapter.Admin_StudentinClassDetail_adapter;
import com.example.wri.Adapter.Admin_listStu_PointClass_adapter;
import com.example.wri.Model.Classs;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_listStuPoint_inClass extends AppCompatActivity {
    RecyclerView rcv_studentinclass_poin_admin;
    Students students;
    ArrayList<Students> studentsArrayList;
    Admin_listStu_PointClass_adapter listStu_pointClass_adapter;
    ImageView id_back_admin_pointstu_liststu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_stu_point_in_class);
        rcv_studentinclass_poin_admin = findViewById(R.id.rcv_studentinclass_poin_admin);
        id_back_admin_pointstu_liststu = findViewById(R.id.id_back_admin_pointstu_liststu);
        Intent in = getIntent();
        String id = in.getStringExtra("idclass");
        Dataintent();
        GetStudentinClass(id);
    }
    public void onBack(View view) {
        finish();
    }
    private void GetStudentinClass(String id) {
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getStudentinClass_admin(id);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                studentsArrayList = new ArrayList<Students>();
                studentsArrayList = (ArrayList<Students>)response.body();
                listStu_pointClass_adapter = new Admin_listStu_PointClass_adapter(getApplicationContext(),studentsArrayList);
                rcv_studentinclass_poin_admin.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                rcv_studentinclass_poin_admin.setAdapter(listStu_pointClass_adapter);
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {

            }
        });
    }

    private void Dataintent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("stu_point")) {
                students = (Students) intent.getSerializableExtra("stu_point");
            }
        }

    }
}