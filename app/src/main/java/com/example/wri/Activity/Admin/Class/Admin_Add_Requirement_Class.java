package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wri.Adapter.Admin_ListAll_Req_Adapter;
import com.example.wri.Adapter.Admin_addStudentClass_Adapter;
import com.example.wri.Model.Requirement;
import com.example.wri.Model.Student_Teacher_Class_Point;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Add_Requirement_Class extends AppCompatActivity {
    Admin_ListAll_Req_Adapter req_adapter;
    ArrayList<Requirement> requirementsArrayList;
    RecyclerView rcv_addreq_class_admin;
    EditText edt_search_req_listreq_class;
    ImageView search_req_class;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add__requirement__class);
        rcv_addreq_class_admin = findViewById(R.id.rcv_addreq_class_admin);
        edt_search_req_listreq_class = findViewById(R.id.edt_search_req_listreq_class);
        final  String txtSearch = edt_search_req_listreq_class.getText().toString();
        search_req_class = findViewById(R.id.search_req_class);
        Intent in = getIntent();
        id = in.getStringExtra("idclass");
        Getdata(txtSearch);
        checkButtonClick();
    }
    private void Getdata(String keyword) {
        DataService dataService = APIService.getService();
        Call<List<Requirement>> callback = dataService.getallRequirement_class_admin(keyword);
        callback.enqueue(new Callback<List<Requirement>>() {
            @Override
            public void onResponse(Call<List<Requirement>> call, Response<List<Requirement>> response) {
                requirementsArrayList = (ArrayList<Requirement>)response.body();
                req_adapter = new Admin_ListAll_Req_Adapter(getApplicationContext(),requirementsArrayList);
                rcv_addreq_class_admin.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rcv_addreq_class_admin.setAdapter(req_adapter);
            }

            @Override
            public void onFailure(Call<List<Requirement>> call, Throwable t) {

            }
        });

    }
    public void onBack(View view) {
        finish();
    }
    private void checkButtonClick() {
        ImageView myButton =  findViewById(R.id.findSelected_req_class_admin);
        ImageView search = findViewById(R.id.search_req_class);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<Requirement> requirementsArrayList = req_adapter.requirementsArrayList;
                for(int i=0;i<requirementsArrayList.size();i++){
                    final Requirement requirement = requirementsArrayList.get(i);
                    if(requirement.isSelected()){
                        DataService dataService = APIService.getService();
                        Call<Requirement> callback = dataService.addRequirement_class_admin(id,requirement.getId());
                        callback.enqueue(new Callback<Requirement>() {
                            @Override
                            public void onResponse(Call<Requirement> call, Response<Requirement> response) {
                                if(response.body().getIsSuccess() == 1){
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                if(response.body().getIsSuccess() == 0){
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Requirement> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Bạn đã thêm: \n"+requirement.getNameReq()+"\nVào Lớp", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            }
        });
       search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String txtSearch = edt_search_req_listreq_class.getText().toString();
               Getdata(txtSearch);
           }
       });
    }
}