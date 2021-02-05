package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wri.Activity.Admin.Student.Admin_List_Student;
import com.example.wri.Activity.LoginandRegister.Login;
import com.example.wri.Activity.Student.Student_Main;
import com.example.wri.Adapter.Admin_List_AllStudent_Adapter;
import com.example.wri.Adapter.Admin_addStudentClass_Adapter;
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

public class Admin_Add_Student_Class extends AppCompatActivity {
    Admin_addStudentClass_Adapter addStudentClass_adapter;
    ArrayList<Students> studentsArrayList;
    RecyclerView rcv_addStudentClass_admin;
    String id;
    EditText edt_searchaddstu_class_admin;
    ImageView searchaddstu_class_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmin__add__student__class);
        //Generate list View from ArrayList
        init();
        String keyword = edt_searchaddstu_class_admin.getText().toString();
        Getdata(keyword);
        searchaddstu_class_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edt_searchaddstu_class_admin.getText().toString();
                Getdata(keyword);
            }
        });

        Intent in = getIntent();
        id = in.getStringExtra("idclass");
        checkButtonClick();
    }

    private void init() {
        rcv_addStudentClass_admin = findViewById(R.id.rcv_addStudentClass_admin);
        edt_searchaddstu_class_admin= findViewById(R.id.edt_searchaddstu_class_admin);
        searchaddstu_class_admin = findViewById(R.id.searchaddstu_class_admin);
    }

    private void Getdata(String keyword) {
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getAllStudent_admin(keyword);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                studentsArrayList = (ArrayList<Students>)response.body();
                addStudentClass_adapter = new Admin_addStudentClass_Adapter(Admin_Add_Student_Class.this,studentsArrayList);
                rcv_addStudentClass_admin.setLayoutManager(new LinearLayoutManager(Admin_Add_Student_Class.this));
                rcv_addStudentClass_admin.setAdapter(addStudentClass_adapter);
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {

            }
        });
    }
    public void onBack(View view) {
        finish();
    }
    private void checkButtonClick() {
        ImageView myButton =  findViewById(R.id.findSelected_student);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<Students> studentsArrayList = addStudentClass_adapter.studentsArrayList;
                for(int i=0;i<studentsArrayList.size();i++){
                    final Students students = studentsArrayList.get(i);
                    if(students.isSelected()){
                        DataService dataService = APIService.getService();
                        Call<Student_Teacher_Class_Point> callback  = dataService.connective_student_class(id,students.getId());
                        callback.enqueue(new Callback<Student_Teacher_Class_Point>() {
                            @Override
                            public void onResponse(Call<Student_Teacher_Class_Point> call, Response<Student_Teacher_Class_Point> response) {
                                if(response.body().getIsSuccess() == 1){
                                    Toast.makeText(Admin_Add_Student_Class.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                if(response.body().getIsSuccess() == 0){
                                    Toast.makeText(Admin_Add_Student_Class.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Student_Teacher_Class_Point> call, Throwable t) {
                                Toast.makeText(Admin_Add_Student_Class.this, "Thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }


            }
        });

    }

}