package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wri.Adapter.Admin_StudentinClassDetail_adapter;
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

public class Admin_DeleteStudent_Class extends AppCompatActivity {
    Admin_addStudentClass_Adapter deleteStudentClass_adapter;
    ArrayList<Students> studentsArrayList;
    RecyclerView rcv_deleteStudentClass_admin;
    String id;
    ImageView id_back_deletestudentinclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__delete_student__class);
        id_back_deletestudentinclass = findViewById(R.id.id_back_deletestudentinclass);
        rcv_deleteStudentClass_admin = findViewById(R.id.rcv_deleteStudentClass_admin);
        Intent in = getIntent();
        id = in.getStringExtra("idclass");
        GetStudentinClass(id);
        checkButtonClick();
    }
    public void onBack(View view) {
        finish();
    }
    private  void  GetStudentinClass(String id){
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getStudentinClass_admin(id);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                studentsArrayList = new ArrayList<Students>();
                studentsArrayList = (ArrayList<Students>)response.body();
                deleteStudentClass_adapter = new Admin_addStudentClass_Adapter(getApplicationContext(),studentsArrayList);
                rcv_deleteStudentClass_admin.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                rcv_deleteStudentClass_admin.setAdapter(deleteStudentClass_adapter);
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {

            }
        });
    }
    private void checkButtonClick() {
        ImageView myButton =  findViewById(R.id.DeleteSelected_student);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Students> studentsArrayList = deleteStudentClass_adapter.studentsArrayList;
                for(int i=0;i<studentsArrayList.size();i++){
                    final Students students = studentsArrayList.get(i);
                    if(students.isSelected()){
                        DataService dataService = APIService.getService();
                        Call<Student_Teacher_Class_Point> callback  = dataService.delete_student_class(id,students.getId());
                        callback.enqueue(new Callback<Student_Teacher_Class_Point>() {
                            @Override
                            public void onResponse(Call<Student_Teacher_Class_Point> call, Response<Student_Teacher_Class_Point> response) {
                                if(response.body().getIsSuccess() == 1){
                                    Toast.makeText(Admin_DeleteStudent_Class.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                if(response.body().getIsSuccess() == 0){
                                    Toast.makeText(Admin_DeleteStudent_Class.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Student_Teacher_Class_Point> call, Throwable t) {
                                Log.d("count",t.toString());
                            }
                        });

                    }
                }


            }
        });

    }

}