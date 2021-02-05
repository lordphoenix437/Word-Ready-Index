package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wri.Activity.Admin.Teacher.Detail_Teacher_Admin;
import com.example.wri.Adapter.Admin_ReqinClass_Adapter;
import com.example.wri.Adapter.Admin_StudentinClassDetail_adapter;
import com.example.wri.Adapter.Admin_TeacherinClassDetail_adapter;
import com.example.wri.Adapter.Admin_listClass_Adapter;
import com.example.wri.Model.Classs;
import com.example.wri.Model.Requirement;
import com.example.wri.Model.Student_Teacher_Class_Point;
import com.example.wri.Model.Students;
import com.example.wri.Model.Teacher;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Detail_Class extends AppCompatActivity {
    TextView name_class_detailclass_admin,code_class_detailclass_admin,currentstudent_class_detailclass_admin
            ,maxstudent_class_detailclass_admin,description_class_detailclass_admin,opening_class_detailclass_admin,
            tv_allstudentinclass_joined_detailclass_admin,tv_allteacherinclass_joined_detailclass_admin,
            tv_allreqinclass_joined_detailclass_admin,tv_waitstudent_detailclass_addmin;
    ImageView thumbnail_class_detailclass_admin,img_addstudent_detailclass_addmin
    ,img_addteacher_detailclass_addmin,img_addreq_detailclass_addmin;
    Classs classs;
    Button btn_edit_detailClass_admin,btn_point_detailClass_admin;
    public RecyclerView rcv_studentinclass_detailclass,rcv_techertinclass_detailclass,rcv_reqinclass_detailclass,rcv_studentwaitinclass_detailclass;
    public ArrayList<Students> studentsArrayList;
    public  ArrayList<Teacher> teacherArrayList;
    public ArrayList<Requirement> requirementArrayList;
    public Admin_ReqinClass_Adapter reqinClass_adapter;
    public Admin_StudentinClassDetail_adapter studentinClassDetail_adapter;
    public Admin_TeacherinClassDetail_adapter teacherinClassDetail_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__detailclass);
        init();
        Dataintent();
//        GetStudentinClass(classs.getId());
        GetData(classs.getId());
        GetTeacherinClass(classs.getId());
//        GetRequimentinClass(classs.getId());
        final String id = classs.getId();

        btn_edit_detailClass_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bundle intent -> editclass
                Bundle b = new Bundle();
                b.putString("id", classs.getId());
                b.putString("nameclass", classs.getNameClass());
                b.putString("maxclass", classs.getMaxStudentClass());
                b.putString("decriptionclass", classs.getDecriptionClass());
                b.putString("codeclass", classs.getCodeClass());
                b.putString("openingclass", classs.getOpeningClass());
                b.putString("thumbnailclass", classs.getThumbnailClass());
                Intent in = new Intent(getApplicationContext(), Admin_Edit_Class.class);
                in.putExtras(b);
                startActivity(in);
                //
            }
        });
        img_addstudent_detailclass_addmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Admin_Add_Student_Class.class);
                in.putExtra("idclass",classs.getId());
                startActivity(in);
            }
        });
        img_addteacher_detailclass_addmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Admin_Add_Teacher_Class.class);
                in.putExtra("idclass",classs.getId());
                startActivity(in);
            }
        });
        tv_allstudentinclass_joined_detailclass_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Admin_DeleteStudent_Class.class);
                in.putExtra("idclass",classs.getId());
                startActivity(in);
            }
        });
        tv_allteacherinclass_joined_detailclass_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Admin_DeleteTeacher_Class.class);
                in.putExtra("idclass",classs.getId());
                startActivity(in);
            }
        });
        img_addreq_detailclass_addmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Admin_Add_Requirement_Class.class);
                in.putExtra("idclass",classs.getId());
                startActivity(in);
            }
        });
        tv_allreqinclass_joined_detailclass_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Admin_DeleteReq_Class.class);
                in.putExtra("idclass",classs.getId());
                startActivity(in);
            }
        });
        btn_point_detailClass_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Admin_listStuPoint_inClass.class);
                in.putExtra("idclass",classs.getId());
                startActivity(in);
            }
        });
        tv_waitstudent_detailclass_addmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Admin_AddStudentwait_Class.class);
                in.putExtra("idclass",classs.getId());
                startActivity(in);
            }
        });
        AddReq_Stu_Point(classs.getId());
        GetStuWait(classs.getId());
    }

    public void GetData(String id) {
        DataService dataService = APIService.getService();
        Call<List<Classs>> callback = dataService.getDetailClass_admin(id);
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                name_class_detailclass_admin.setText(classs.getNameClass());
                code_class_detailclass_admin.setText("Mã lớp: "+classs.getCodeClass());
                currentstudent_class_detailclass_admin.setText("Học viên hiện tại: "+classs.getCurrentStudentClass());
                maxstudent_class_detailclass_admin.setText("Học viên tối đa: "+classs.getMaxStudentClass());
                opening_class_detailclass_admin.setText("Ngày mở lớp: "+ classs.getOpeningClass());
                description_class_detailclass_admin.setText("Mô tả: \n"+classs.getDecriptionClass());
                Picasso.with(getApplicationContext()).load(classs.getThumbnailClass()).into(thumbnail_class_detailclass_admin);
            }
            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });
    }
    public void GetStuWait(String id){
      DataService dataService = APIService.getService();
      Call<List<Students>> callback = dataService.getStuwait_inClass(id);
      callback.enqueue(new Callback<List<Students>>() {
          @Override
          public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
              studentsArrayList = new ArrayList<Students>();
              studentsArrayList = (ArrayList<Students>)response.body();
              studentinClassDetail_adapter = new  Admin_StudentinClassDetail_adapter(getApplicationContext(),studentsArrayList);
              rcv_studentwaitinclass_detailclass.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
              rcv_studentwaitinclass_detailclass.setAdapter(studentinClassDetail_adapter);
          }

          @Override
          public void onFailure(Call<List<Students>> call, Throwable t) {

          }
      });
    }
    public void GetRequimentinClass(String id){
        DataService dataService = APIService.getService();
        Call<List<Requirement>> callback = dataService.getRequimentinClass_admin(id);
        callback.enqueue(new Callback<List<Requirement>>() {
            @Override
            public void onResponse(Call<List<Requirement>> call, Response<List<Requirement>> response) {

                requirementArrayList = new ArrayList<Requirement>();
                requirementArrayList = (ArrayList<Requirement>)response.body();
                reqinClass_adapter = new Admin_ReqinClass_Adapter(getApplicationContext(),requirementArrayList);
                rcv_reqinclass_detailclass.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
                rcv_reqinclass_detailclass.setAdapter(reqinClass_adapter);
                ArrayList<Requirement> requirementArrayList = reqinClass_adapter.requirementsArrayList;

            }

            @Override
            public void onFailure(Call<List<Requirement>> call, Throwable t) {

            }
        });
    }
   public   void  GetStudentinClass(String id){
        DataService dataService = APIService.getService();
       Call<List<Students>> callback = dataService.getStudentinClass_admin(id);
       callback.enqueue(new Callback<List<Students>>() {
           @Override
           public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
               studentsArrayList = new ArrayList<Students>();
               studentsArrayList = (ArrayList<Students>)response.body();
               studentinClassDetail_adapter = new Admin_StudentinClassDetail_adapter(getApplicationContext(),studentsArrayList);
               rcv_studentinclass_detailclass.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
               rcv_studentinclass_detailclass.setAdapter(studentinClassDetail_adapter);

           }

           @Override
           public void onFailure(Call<List<Students>> call, Throwable t) {

           }
       });
   }
    public void AddReq_Stu_Point(final String id){
        DataService dataService = APIService.getService();
        Call<List<Requirement>> callback = dataService.getRequimentinClass_admin(id);
        callback.enqueue(new Callback<List<Requirement>>() {
            @Override
            public void onResponse(Call<List<Requirement>> call, Response<List<Requirement>> response) {
                requirementArrayList = new ArrayList<Requirement>();
                requirementArrayList = (ArrayList<Requirement>)response.body();
                reqinClass_adapter = new Admin_ReqinClass_Adapter(getApplicationContext(),requirementArrayList);
                rcv_reqinclass_detailclass.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
                rcv_reqinclass_detailclass.setAdapter(reqinClass_adapter);
                DataService dataService = APIService.getService();
                Call<List<Students>> callback = dataService.getStudentinClass_admin(id);
                callback.enqueue(new Callback<List<Students>>() {
                    @Override
                    public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                        studentsArrayList = new ArrayList<Students>();
                        studentsArrayList = (ArrayList<Students>)response.body();
                        studentinClassDetail_adapter = new Admin_StudentinClassDetail_adapter(getApplicationContext(),studentsArrayList);
                        rcv_studentinclass_detailclass.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
                        rcv_studentinclass_detailclass.setAdapter(studentinClassDetail_adapter);
                        ArrayList<Students> studentsArrayList = studentinClassDetail_adapter.studentsArrayList;
                        ArrayList<Requirement> requirementArrayList = reqinClass_adapter.requirementsArrayList;
                        for(int i=0;i<studentsArrayList.size();i++){
                            final Students students = studentsArrayList.get(i);
                            for(int j=0;j<requirementArrayList.size();j++){
                                final Requirement requirement = requirementArrayList.get(j);
                                DataService dataService = APIService.getService();
                                Call<Student_Teacher_Class_Point> callback = dataService.addStu_Req_Point(classs.getId(),requirement.getId(),students.getId());
                                callback.enqueue(new Callback<Student_Teacher_Class_Point>() {
                                    @Override
                                    public void onResponse(Call<Student_Teacher_Class_Point> call, Response<Student_Teacher_Class_Point> response) {
                                        Log.d("n",classs.getId() + " - " + requirement.getId() + " - " + students.getId());
                                    }

                                    @Override
                                    public void onFailure(Call<Student_Teacher_Class_Point> call, Throwable t) {

                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Students>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Requirement>> call, Throwable t) {

            }
        });

//        ArrayList<Students> studentsArrayList = studentinClassDetail_adapter.studentsArrayList;
//        ArrayList<Requirement> requirementArrayList = reqinClass_adapter.requirementsArrayList;
//        for(int i=0;i<studentsArrayList.size();i++){
//            final Students students = studentsArrayList.get(i);
//            for(int j=0;j<requirementArrayList.size();j++){
//                final Requirement requirement = requirementArrayList.get(j);
//                DataService dataService = APIService.getService();
//                Call<Student_Teacher_Class_Point> callback = dataService.addStu_Req_Point(classs.getId(),requirement.getId(),students.getId());
//                callback.enqueue(new Callback<Student_Teacher_Class_Point>() {
//                    @Override
//                    public void onResponse(Call<Student_Teacher_Class_Point> call, Response<Student_Teacher_Class_Point> response) {
//                        Log.d("n",classs.getId() + " - " + requirement.getId() + " - " + students.getId());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Student_Teacher_Class_Point> call, Throwable t) {
//
//                    }
//                });
//            }
//        }
    }
   public   void GetTeacherinClass(String id){
        DataService dataService = APIService.getService();
       Call<List<Teacher>> callback = dataService.getTeacherinClass_admin(id);
       callback.enqueue(new Callback<List<Teacher>>() {
           @Override
           public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
               teacherArrayList = (ArrayList<Teacher>)response.body();
               teacherinClassDetail_adapter = new Admin_TeacherinClassDetail_adapter(getApplicationContext(),teacherArrayList);
               rcv_techertinclass_detailclass.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
               rcv_techertinclass_detailclass.setAdapter(teacherinClassDetail_adapter);
           }

           @Override
           public void onFailure(Call<List<Teacher>> call, Throwable t) {

           }
       });

   }
    private void init() {
        name_class_detailclass_admin = findViewById(R.id.name_class_detailclass_admin);
        code_class_detailclass_admin = findViewById(R.id.code_class_detailclass_admin);
        currentstudent_class_detailclass_admin = findViewById(R.id.currentstudent_class_detailclass_admin);
        maxstudent_class_detailclass_admin = findViewById(R.id.maxstudent_class_detailclass_admin);
        description_class_detailclass_admin = findViewById(R.id.description_class_detailclass_admin);
        opening_class_detailclass_admin = findViewById(R.id.opening_class_detailclass_admin);
        thumbnail_class_detailclass_admin = findViewById(R.id.thumbnail_class_detailclass_admin);
        img_addstudent_detailclass_addmin = findViewById(R.id.img_addstudent_detailclass_addmin);
        tv_waitstudent_detailclass_addmin =findViewById(R.id.tv_waitstudent_detailclass_addmin);
        img_addteacher_detailclass_addmin = findViewById(R.id.img_addteacher_detailclass_addmin);
        btn_edit_detailClass_admin = findViewById(R.id.btn_edit_detailClass_admin);
        rcv_studentinclass_detailclass = findViewById(R.id.rcv_studentinclass_detailclass);
        rcv_techertinclass_detailclass = findViewById(R.id.rcv_techertinclass_detailclass);
        tv_allstudentinclass_joined_detailclass_admin = findViewById(R.id.tv_allstudentinclass_joined_detailclass_admin);
        tv_allteacherinclass_joined_detailclass_admin = findViewById(R.id.tv_allteacherinclass_joined_detailclass_admin);
        img_addreq_detailclass_addmin = findViewById(R.id.img_addreq_detailclass_addmin);
        rcv_reqinclass_detailclass = findViewById(R.id.rcv_reqinclass_detailclass);
        tv_allreqinclass_joined_detailclass_admin = findViewById(R.id.tv_allreqinclass_joined_detailclass_admin);
        btn_point_detailClass_admin = findViewById(R.id.btn_point_detailClass_admin);
        rcv_studentwaitinclass_detailclass = findViewById(R.id.rcv_studentwaitinclass_detailclass);
    }

    public void onBack(View view) {
        finish();
    }
    private void Dataintent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("class_admin")) {
                classs = (Classs) intent.getSerializableExtra("class_admin");
            }
        }

    }

    @Override
    protected void onResume() {
        GetData(classs.getId());
        super.onResume();
    }
}