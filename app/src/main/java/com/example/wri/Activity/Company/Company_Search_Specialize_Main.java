package com.example.wri.Activity.Company;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wri.Adapter.searchMajor_Company_Adapter;
import com.example.wri.Model.MajorItem;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Company_Search_Specialize_Main extends AppCompatActivity {
    Context context;
    RecyclerView rcv_majorCom;
    searchMajor_Company_Adapter majorAdapter;
    ImageView imv_back_specialize_com, imv_search_majorCom;
    List<MajorItem> majorItemList;
    EditText edt_search_majorCom;
   public String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_search_specialize_main);

        init();
        key = edt_search_majorCom.getText().toString();
        Getdata(key);
        edt_search_majorCom.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
//               Getdata(edt_search_majorCom.getText().toString());
               majorAdapter.getFilter().filter(s);
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
        imv_back_specialize_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack(v);
            }
        });
        imv_search_majorCom.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Getdata(edt_search_majorCom.getText().toString());
          }
      });

    }

    private void init() {
        imv_back_specialize_com = findViewById(R.id.id_back_specialize_com);
        rcv_majorCom = findViewById(R.id.rcv_majorCom);
        edt_search_majorCom = findViewById(R.id.edt_search_majorCom);
        imv_search_majorCom = findViewById(R.id.search_majorCom);
    }
    private void Getdata( String keyword){
        DataService dataService = APIService.getService();
        Call<List<MajorItem>> callback = dataService.searchGMajor_Com(keyword);
        callback.enqueue(new Callback<List<MajorItem>>() {
            @Override
            public void onResponse(Call<List<MajorItem>> call, Response<List<MajorItem>> response) {
                majorItemList = new ArrayList<>();
                majorItemList = (ArrayList<MajorItem>) response.body();
                majorAdapter = new searchMajor_Company_Adapter(Company_Search_Specialize_Main.this, majorItemList);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Company_Search_Specialize_Main.this);
//                linearLayoutManager.setReverseLayout(true);
//                linearLayoutManager.setStackFromEnd(true);
                rcv_majorCom.setLayoutManager(new LinearLayoutManager(Company_Search_Specialize_Main.this, LinearLayoutManager.VERTICAL,false));
                rcv_majorCom.setAdapter(majorAdapter);


            }

            @Override
            public void onFailure(Call<List<MajorItem>> call, Throwable t) {

            }
        });
    }
    public void onBack(View view) {
        finish();
    }
    @Override
    protected void onResume() {
        Getdata(key);
        super.onResume();
    }
}