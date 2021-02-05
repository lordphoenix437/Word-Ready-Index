package com.example.wri.Activity.Company;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wri.Activity.Admin.Class.Admin_List_Class;
import com.example.wri.Adapter.SearchStudent_Of_Major_Company_Adapter;
import com.example.wri.Model.Classs;
import com.example.wri.Model.MajorItem;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Company_Specialize_List_Student extends AppCompatActivity {

    SearchStudent_Of_Major_Company_Adapter majorStudent_Adapter;
    ImageView imv_Back_List_StudentOfMajor, imv_Search_StudenstOfMajor;
    RecyclerView rcv_student_ofMajor;
    TextView tv_KhoaNganh;
    EditText edt_Seach_StudentOfMajor;
    ArrayList<Students> studentsArrayList;
    String keyword,idmajor;
    MajorItem majorItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_specialize__list__student);


        init();
        DataIntent();
       Log.d("idgroup",majorItem.getId());
        //sử dụng hàm tìm kiếm giống như bản trước
        keyword = "";
        Getdata(majorItem.getId(),keyword);
        tv_KhoaNganh.setText(majorItem.getNamegmajor());
        imv_Back_List_StudentOfMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack(v);
            }
        });
        edt_Seach_StudentOfMajor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void Getdata(String idgmajor, String keyword) {
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.searchStudent_Of_Major(idgmajor,keyword);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                studentsArrayList = new ArrayList<>();
                studentsArrayList = (ArrayList<Students>) response.body();
                majorStudent_Adapter = new SearchStudent_Of_Major_Company_Adapter(getApplicationContext(), studentsArrayList);
                rcv_student_ofMajor.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                rcv_student_ofMajor.setAdapter(majorStudent_Adapter);
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {

            }
        });
    }

    private void init() {
        imv_Back_List_StudentOfMajor = findViewById(R.id.img_back_list_student_of_major);
        imv_Search_StudenstOfMajor = findViewById(R.id.img_search_student_of_major);
        edt_Seach_StudentOfMajor = findViewById(R.id.edt_search_student_of_major);
        rcv_student_ofMajor = findViewById(R.id.rcv_list_student_of_major);
        tv_KhoaNganh = findViewById(R.id.txtKhoaNganh);

    }

    private  void DataIntent(){
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("gmajor_id")) {
                majorItem = (MajorItem) intent.getSerializableExtra("gmajor_id");
            }
        }
    }
    public void onBack(View view) {
        finish();
    }
    @Override
    protected void onResume() {
        Getdata("idgmajor",keyword);
        super.onResume();
    }


}