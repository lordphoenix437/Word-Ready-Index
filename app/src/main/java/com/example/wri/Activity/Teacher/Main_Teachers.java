package com.example.wri.Activity.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.wri.Adapter.Teacher_teached_listClass_Adapter;
import com.example.wri.Model.Classs;
import com.example.wri.Model.Teacher;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Teachers extends AppCompatActivity {
    RecyclerView rcv_teached_teacher;
   Teacher_teached_listClass_Adapter listClass_adapter;
   Classs classs;
   ArrayList<Classs> classArrayList;
   TextView tv_nameTeacher_mainTeacher;
   Teacher teacher;
   ArrayList<Teacher> teacherArrayList;
   String emailteacher,code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__teacher);
        teacher = new Teacher();
        DataInten();
        init();
        GetTeacher_email(emailteacher);
        tv_nameTeacher_mainTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPopup();
            }
        });
    }

    private void init() {
        rcv_teached_teacher = findViewById(R.id.rcv_teached_teacher);
        tv_nameTeacher_mainTeacher = findViewById(R.id.tv_nameTeacher_mainTeacher);
    }
    private void GetTeacher_email(String emailteacher){
        DataService dataService = APIService.getService();
       Call<List<Teacher>> call = dataService.getTeacher_email_teacher(emailteacher);
       call.enqueue(new Callback<List<Teacher>>() {
           @Override
           public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
             String name =   response.body().get(0).getNameTeacher();
             tv_nameTeacher_mainTeacher.setText(name);
             code = response.body().get(0).getCodeTeacher();
             GetDataClass(code);
           }

           @Override
           public void onFailure(Call<List<Teacher>> call, Throwable t) {
           }
       });

    }
    private void GetDataClass(String codeTeacher){
        DataService dataService = APIService.getService();
        Call<List<Classs>> callback = dataService.getClass_teached_teacher(codeTeacher);
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                classArrayList = new ArrayList<Classs>();
                classArrayList = (ArrayList<Classs>)response.body();
                listClass_adapter = new Teacher_teached_listClass_Adapter(getApplicationContext(),classArrayList);
                rcv_teached_teacher.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                rcv_teached_teacher.setAdapter(listClass_adapter);
            }

            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });
    }
    private void clickPopup(){
        PopupMenu popup = new PopupMenu(this, this.tv_nameTeacher_mainTeacher);
        popup.inflate(R.menu.menu_teacher);
        Menu menu = popup.getMenu();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return menuItemClicked(item);
            }
        });

        // Show the PopupMenu.
        popup.show();
    }
    private boolean menuItemClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info_teacher:
                Intent i = new Intent(getApplicationContext(),Detail_item_teacher.class);
                i.putExtra("emailteacher",emailteacher);
                startActivity(i);
                break;
            case R.id.logout_teacher:
                finish();
                break;
        }
        return true;
    }
    private void DataInten(){
        Intent i = getIntent();
        if(i != null){
            if(i.hasExtra("emailteacher")){
                emailteacher = i.getStringExtra("emailteacher");
            }
        }
    }
}