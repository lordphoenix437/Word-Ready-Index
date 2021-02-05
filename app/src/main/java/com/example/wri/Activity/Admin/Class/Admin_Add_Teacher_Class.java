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

import com.example.wri.Adapter.Admin_addStudentClass_Adapter;
import com.example.wri.Adapter.Admin_addTeacherClass_Adapter;
import com.example.wri.Model.Student_Teacher_Class_Point;
import com.example.wri.Model.Students;
import com.example.wri.Model.Teacher;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Add_Teacher_Class extends AppCompatActivity {
    Admin_addTeacherClass_Adapter addTeacherClass_adapter;
    ArrayList<Teacher> teachersArrayList;
    RecyclerView rcv_addTeacher_admin;
    String id;
    EditText edt_searchteacher_addteacher_class;
    ImageView searchteacher_addteacher_class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add__teacher__class);
        init();
        String keyword  = edt_searchteacher_addteacher_class.getText().toString();

        Intent in = getIntent();
        id = in.getStringExtra("idclass");
        Getdata(keyword);
        checkButtonClick();
        searchteacher_addteacher_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword  = edt_searchteacher_addteacher_class.getText().toString();
                Getdata(keyword);
            }
        });
    }

    private void init() {
        rcv_addTeacher_admin = findViewById(R.id.rcv_addTeacherClass_admin);
        edt_searchteacher_addteacher_class = findViewById(R.id.edt_searchteacher_addteacher_class);
        searchteacher_addteacher_class = findViewById(R.id.searchteacher_addteacher_class);
    }

    private void Getdata(String keyword) {
        DataService dataService = APIService.getService();
        Call<List<Teacher>> callback = dataService.getAllTeacher_admin(keyword);
        callback.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                teachersArrayList = (ArrayList<Teacher>)response.body();
                addTeacherClass_adapter = new Admin_addTeacherClass_Adapter(Admin_Add_Teacher_Class.this,teachersArrayList);
                rcv_addTeacher_admin.setLayoutManager(new LinearLayoutManager(Admin_Add_Teacher_Class.this));
                rcv_addTeacher_admin.setAdapter(addTeacherClass_adapter);
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
            }
        });
    }
    public void onBack(View view) {
        finish();
    }
    private void checkButtonClick() {
        ImageView myButton =  findViewById(R.id.findSelected_teacher);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<Teacher> teachersArrayList = addTeacherClass_adapter.teacherArrayList;
                for(int i=0;i<teachersArrayList.size();i++){
                    final Teacher teacher= teachersArrayList.get(i);
                    if(teacher.isSelected()){
                        DataService dataService = APIService.getService();
                        Call<Student_Teacher_Class_Point> callback = dataService.connective_teacher_class(id,teacher.getCodeTeacher());
                        callback.enqueue(new Callback<Student_Teacher_Class_Point>() {
                            @Override
                            public void onResponse(Call<Student_Teacher_Class_Point> call, Response<Student_Teacher_Class_Point> response) {
                                if(response.body().getIsSuccess() == 1){
                                    Toast.makeText(Admin_Add_Teacher_Class.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                if(response.body().getIsSuccess() == 0){
                                    Toast.makeText(Admin_Add_Teacher_Class.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Student_Teacher_Class_Point> call, Throwable t) {
                                Toast.makeText(Admin_Add_Teacher_Class.this, "Bạn đã thêm: \n"+teacher.getNameTeacher()+"\nVào Lớp", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }


            }
        });

    }
}