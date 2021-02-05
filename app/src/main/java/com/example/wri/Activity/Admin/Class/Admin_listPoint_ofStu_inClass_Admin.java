package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.wri.Adapter.Admin_listPoint_ofStu_inClass_Adapter;
import com.example.wri.Model.Points;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_listPoint_ofStu_inClass_Admin extends AppCompatActivity {
    RecyclerView rcv_listpointreq_ofstu_admin;
    ArrayList<Students> studentsArrayList;
    ArrayList<Points> pointsArrayList;
    Admin_listPoint_ofStu_inClass_Adapter listPoint_adapter;
    Students students;
    ImageView id_pointreqofstu_inclass_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_point_of_stu_in_class__admin);
        rcv_listpointreq_ofstu_admin = findViewById(R.id.rcv_listpointreq_ofstu_admin);
        id_pointreqofstu_inclass_admin = findViewById(R.id.id_pointreqofstu_inclass_admin);
        Dataintent();
        GetdataPoint(students.getIdClass(),students.getId());
        Log.d("n",students.getIdClass() + "idStu: "+ students.getId());
        
    }
    public void onBack(View view) {
        finish();
    }
    private void GetdataPoint(String idClass, String idStu) {
        DataService dataService = APIService.getService();
        Call<List<Points>> callpack = dataService.getPointStu_inClass_admin(idClass,idStu);
        callpack.enqueue(new Callback<List<Points>>() {
            @Override
            public void onResponse(Call<List<Points>> call, Response<List<Points>> response) {
                pointsArrayList = new ArrayList<Points>();
                pointsArrayList = (ArrayList<Points>)response.body();
                listPoint_adapter = new Admin_listPoint_ofStu_inClass_Adapter(getApplicationContext(),pointsArrayList);
                rcv_listpointreq_ofstu_admin.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                rcv_listpointreq_ofstu_admin.setAdapter(listPoint_adapter);

            }

            @Override
            public void onFailure(Call<List<Points>> call, Throwable t) {

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