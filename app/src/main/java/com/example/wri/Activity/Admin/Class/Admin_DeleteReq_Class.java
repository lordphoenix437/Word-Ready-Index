package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
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

public class Admin_DeleteReq_Class extends AppCompatActivity {
    Admin_ListAll_Req_Adapter deleteReq_adapter;
    ArrayList<Requirement> requirementArrayList;
    RecyclerView rcv_deleteReqClass_admin;
    String id;
    ImageView id_back_deleteReqinclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__delete_req__class);
        rcv_deleteReqClass_admin = findViewById(R.id.rcv_deleteReqClass_admin);

        Intent in = getIntent();
        id = in.getStringExtra("idclass");
        GetReqinClass(id);
        checkButtonClick();
    }

    private void GetReqinClass(String id) {
        DataService dataService = APIService.getService();
        Call<List<Requirement>> callback = dataService.getRequimentinClass_admin(id);
        callback.enqueue(new Callback<List<Requirement>>() {
            @Override
            public void onResponse(Call<List<Requirement>> call, Response<List<Requirement>> response) {
                requirementArrayList = (ArrayList<Requirement>)response.body();
                deleteReq_adapter = new Admin_ListAll_Req_Adapter(getApplicationContext(),requirementArrayList);
               rcv_deleteReqClass_admin.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
               rcv_deleteReqClass_admin.setAdapter(deleteReq_adapter);
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
        ImageView myButton =  findViewById(R.id.DeleteSelected_req_inclass_admin);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Requirement> requirementsArrayList = deleteReq_adapter.requirementsArrayList;
                for(int i=0;i<requirementsArrayList.size();i++){
                    final Requirement requirement = requirementsArrayList.get(i);
                    if(requirement.isSelected()){
                        DataService dataService = APIService.getService();
                        Call<Requirement> callback = dataService.deleteRequimentinClass_admin(id,requirement.getId());
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

                            }
                        });


                    }
                }


            }
        });

    }
}