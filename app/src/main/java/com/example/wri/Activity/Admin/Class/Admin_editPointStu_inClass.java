package com.example.wri.Activity.Admin.Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wri.Model.Classs;
import com.example.wri.Model.Points;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_editPointStu_inClass extends AppCompatActivity {
    TextView dialog_edt_namegroupreqofstu_inclass_admin,dialog_edt_namereqofstu_inclass_admin;
    EditText dialog_edt_pointreqofstu_inclass_admin;
    Button btn_edtpointStu_inclass;
    ImageView id_back_editpoint_class;
    Points points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_point_stu_in_class);
        init();
        Dataintent();
        final String  idclass = points.getIdClass();
        final       String  idStu = points.getIdStu();
        final String idReq = points.getIdReq();
        final     String point = points.getPoint();
        Log.d("n",points.getIdClass() +" "+points.getIdStu() +" "+ points.getIdReq() +" "+points.getPoint());
        dialog_edt_namegroupreqofstu_inclass_admin.setText(points.getNameGroupReq());
        dialog_edt_namereqofstu_inclass_admin.setText(points.getNameReq());
        dialog_edt_pointreqofstu_inclass_admin.setText(point);
        btn_edtpointStu_inclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String point =  dialog_edt_pointreqofstu_inclass_admin.getText().toString().trim();
                UpdatePoint(idclass,idStu,idReq,point);
            }
        });

    }
    public void onBack(View view) {
        finish();
    }
   private void UpdatePoint(String idClass,String idStu,String idReq,String point){
       DataService dataService = APIService.getService();
       Call<Points> callpack = dataService.updatePointStu_inClass_admin(idClass,idStu,idReq,point);
       callpack.enqueue(new Callback<Points>() {
           @Override
           public void onResponse(Call<Points> call, Response<Points> response) {
               if(response.body().getIsSuccess() == 1){
                   Toast.makeText(Admin_editPointStu_inClass.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                   finish();
               }
               if(response.body().getIsSuccess() == 0){
                   Toast.makeText(Admin_editPointStu_inClass.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<Points> call, Throwable t) {

           }
       });

   }
    private void init() {
        dialog_edt_namegroupreqofstu_inclass_admin = findViewById(R.id.dialog_edt_namegroupreqofstu_inclass_admin);
        dialog_edt_namereqofstu_inclass_admin = findViewById(R.id.dialog_edt_namereqofstu_inclass_admin);
        dialog_edt_pointreqofstu_inclass_admin = findViewById(R.id.dialog_edt_pointreqofstu_inclass_admin);
        btn_edtpointStu_inclass = findViewById(R.id.btn_edtpointStu_inclass);
        id_back_editpoint_class = findViewById(R.id.id_back_editpoint_class);
    }
    private void Dataintent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("point_stu_inclass")) {
                points = (Points) intent.getSerializableExtra("point_stu_inclass");
            }
        }

    }
}