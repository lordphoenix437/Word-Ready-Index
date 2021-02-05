package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wri.Adapter.Admin_addTeacherClass_Adapter;
import com.example.wri.Model.Student_Teacher_Class_Point;
import com.example.wri.Model.Teacher;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_DeleteTeacher_Class extends AppCompatActivity {
    Admin_addTeacherClass_Adapter deleteTeacherClass_adapter;
    ArrayList<Teacher> teachersArrayList;
    RecyclerView rcv_deleteTeacherClass_admin;
    String id;
    ImageView id_back_deleteteacherclass_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__delete_teacher__class);
        id_back_deleteteacherclass_admin = findViewById(R.id.id_back_deleteteacherclass_admin);
        rcv_deleteTeacherClass_admin = findViewById(R.id.rcv_deleteTeacherClass_admin);
        Intent in = getIntent();
        id = in.getStringExtra("idclass");
        Getdata(id);
        checkButtonClick();
    }
    public void onBack(View view) {
        finish();
    }
    private void Getdata(String idclass) {
        DataService dataService = APIService.getService();
        Call<List<Teacher>> callback = dataService.getTeacherinClass_admin(idclass);
        callback.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                teachersArrayList = new ArrayList<Teacher>();
                teachersArrayList = (ArrayList<Teacher>)response.body();
                deleteTeacherClass_adapter = new Admin_addTeacherClass_Adapter(getApplicationContext(),teachersArrayList);
                rcv_deleteTeacherClass_admin.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rcv_deleteTeacherClass_admin.setAdapter(deleteTeacherClass_adapter);
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {

            }
        });
    }
    private void checkButtonClick() {
        ImageView myButton =  findViewById(R.id.findSelected_teacher);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<Teacher> teachersArrayList = deleteTeacherClass_adapter.teacherArrayList;
                for(int i=0;i<teachersArrayList.size();i++){
                    final Teacher teacher= teachersArrayList.get(i);
                    if(teacher.isSelected()){
                        DataService dataService = APIService.getService();
                        Call<Student_Teacher_Class_Point> callback = dataService.delete_teacher_class(id,teacher.getCodeTeacher());
                        callback.enqueue(new Callback<Student_Teacher_Class_Point>() {
                            @Override
                            public void onResponse(Call<Student_Teacher_Class_Point> call, Response<Student_Teacher_Class_Point> response) {
                                if(response.body().getIsSuccess() == 1){
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                if(response.body().getIsSuccess() == 0){
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Student_Teacher_Class_Point> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }


            }
        });

    }
}