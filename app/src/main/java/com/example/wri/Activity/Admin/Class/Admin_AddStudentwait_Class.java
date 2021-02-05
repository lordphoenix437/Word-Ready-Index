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

public class Admin_AddStudentwait_Class extends AppCompatActivity {
    Admin_addStudentClass_Adapter addStudentClass_adapter;
    ArrayList<Students> studentsArrayList;
    RecyclerView rcv_addStudentClasswait_admin;
    String id;
    EditText edt_searchaddstuwait_class_admin;
    ImageView searchaddstuwait_class_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add_studentwait__class);
        Intent in = getIntent();
        id = in.getStringExtra("idclass");
        init();
        GetStudentWait(id);
        checkButtonClick();
    }

    private void init() {
        rcv_addStudentClasswait_admin = findViewById(R.id.rcv_addStudentClasswait_admin);

    }
    private  void  GetStudentWait(String idclass){
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getStuwait_inClass(idclass);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                studentsArrayList = new ArrayList<Students>();
                studentsArrayList = (ArrayList<Students>)response.body();
                addStudentClass_adapter = new Admin_addStudentClass_Adapter(getApplicationContext(),studentsArrayList);
                rcv_addStudentClasswait_admin.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                rcv_addStudentClasswait_admin.setAdapter(addStudentClass_adapter);
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
        ImageView myButton =  findViewById(R.id.findSelected_studentwait_inclass);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<Students> studentsArrayList = addStudentClass_adapter.studentsArrayList;
                for(int i=0;i<studentsArrayList.size();i++){
                    final Students students = studentsArrayList.get(i);
                    if(students.isSelected()){
                        DataService dataService = APIService.getService();
                        Call<Student_Teacher_Class_Point> callback = dataService.addStuwait_inClass(id,students.getId());
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