package com.example.wri.Activity.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wri.Activity.Admin.Class.Admin_Detail_Class;
import com.example.wri.Adapter.Admin_listStu_PointClass_adapter;
import com.example.wri.Model.Classs;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_class_teacher extends AppCompatActivity {
     Classs classs;
     TextView name_class_detailclass_teacher,code_class_detailclass_teacher,currentstudent_class_detailclass_teacher,
             maxstudent_class_detailclass_teacher,opening_class_detailclass_teacher,description_class_detailclass_teacher;
     ImageView thumbnail_class_detailclass_teacher,search_stuinclass_teacher;
     EditText edt_search_stuinclass_teacher;
     RecyclerView rcv_studentinclass_poin_teacher;
     ArrayList<Students> studentsArrayList;
     Admin_listStu_PointClass_adapter listStu_pointClass_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_class_teacher);
        init();
        Dataintent();
        String idclass = classs.getId();
        GetData(idclass);
        GetStudentinClass(idclass);
    }

    private void init() {
        name_class_detailclass_teacher = findViewById(R.id.name_class_detailclass_teacher);
        code_class_detailclass_teacher = findViewById(R.id.code_class_detailclass_teacher);
        currentstudent_class_detailclass_teacher = findViewById(R.id.currentstudent_class_detailclass_teacher);
        maxstudent_class_detailclass_teacher = findViewById(R.id.maxstudent_class_detailclass_teacher);
        opening_class_detailclass_teacher = findViewById(R.id.opening_class_detailclass_teacher);
        description_class_detailclass_teacher = findViewById(R.id.description_class_detailclass_teacher);
        thumbnail_class_detailclass_teacher = findViewById(R.id.thumbnail_class_detailclass_teacher);
        search_stuinclass_teacher = findViewById(R.id.search_stuinclass_teacher);
        edt_search_stuinclass_teacher = findViewById(R.id.edt_search_stuinclass_teacher);
        rcv_studentinclass_poin_teacher = findViewById(R.id.rcv_studentinclass_poin_teacher);
    }
    public void GetData(String id) {
        DataService dataService = APIService.getService();
        Call<List<Classs>> callback = dataService.getDetailClass_admin(id);
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                name_class_detailclass_teacher.setText(classs.getNameClass());
                code_class_detailclass_teacher.setText("Mã lớp: "+classs.getCodeClass());
                currentstudent_class_detailclass_teacher.setText("Học viên hiện tại: "+classs.getCurrentStudentClass());
                maxstudent_class_detailclass_teacher.setText("Học viên tối đa: "+classs.getMaxStudentClass());
                opening_class_detailclass_teacher.setText("Ngày mở lớp: "+ classs.getOpeningClass());
                description_class_detailclass_teacher.setText("Mô tả: \n"+classs.getDecriptionClass());
                Picasso.with(getApplicationContext()).load(classs.getThumbnailClass()).into(thumbnail_class_detailclass_teacher);
            }
            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });
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
                rcv_studentinclass_poin_teacher.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                rcv_studentinclass_poin_teacher.setAdapter(listStu_pointClass_adapter);
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {

            }
        });
    }
    public void onBack(View view) {
        finish();
    }
    private void Dataintent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("teached_class_teacher")) {
                classs = (Classs) intent.getSerializableExtra("teached_class_teacher");
            }
        }

    }
}