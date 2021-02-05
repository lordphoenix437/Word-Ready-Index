package com.example.wri.Activity.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wri.Model.Companys;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Company_Main extends AppCompatActivity {
    Button search_job_desc_main_company;
    ImageView contacted_main_company,favorite_main_company,search_main_company,update_main_company;
    TextView txtTitleUser;
  public   String  idCom, nameCompany, thumbnailCompany, addressCompany, emailUser, valuePacketCom, phoneNumberCom;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_main);
        init();
        DataIntent();
        Getdata(emailUser);

          //click search item in edittext
         search_job_desc_main_company.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(Company_Main.this, Company_Add_Search_Desc.class);
                 startActivity(intent);
             }
         });


        search_main_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Company_Main.this, Company_Search_Specialize_Main.class);
                startActivity(intent);
            }
        });
        txtTitleUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPopup();
            }
        });

    }
    private void init() {
        contacted_main_company = findViewById(R.id.contacted_main_company);
        favorite_main_company = findViewById(R.id.favorite_main_company);
        search_main_company = findViewById(R.id.search_main_company);
        update_main_company = findViewById(R.id.update_main_company);
        search_job_desc_main_company = findViewById(R.id.search_job_desc_main_company);
        txtTitleUser = findViewById(R.id.txtTitleUser);
    }

    private void clickPopup(){
        PopupMenu popup = new PopupMenu(this, this.txtTitleUser);
        popup.inflate(R.menu.menu);
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

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("emailCompany")) {
                emailUser = intent.getStringExtra("emailCompany");
            }
        }
    }
    private void Getdata(String emailCompany){
        DataService dataService = APIService.getService();
        Call<List<Companys>> callback = dataService.getCompany(emailCompany);
        callback.enqueue(new Callback<List<Companys>>(){

            @Override
            public void onResponse(Call<List<Companys>> call, Response<List<Companys>> response) {
                 idCom = response.body().get(0).getId();
                 nameCompany = response.body().get(0).getNameCompany();
                 thumbnailCompany = (String) response.body().get(0).getThumbnailCompany();
                 addressCompany = (String) response.body().get(0).getAddressCompany();
                 emailUser = response.body().get(0).getEmailUser();
                 valuePacketCom = response.body().get(0).getValuePacket();
                 phoneNumberCom = response.body().get(0).getPhoneNumber();
                txtTitleUser.setText(nameCompany);
                Toast.makeText(Company_Main.this, "Chào bạn " + nameCompany, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Companys>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lấy thông công ty thất bại", Toast.LENGTH_SHORT).show();
            }
            });
    }

    private boolean menuItemClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info_Company:

                Intent intent = new Intent(getApplicationContext(),Company_Detail.class);
                Bundle bundle = new Bundle();
                bundle.putString("idCom",idCom);
                bundle.putString("nameCom", nameCompany);
                bundle.putString("thumbnailCom", thumbnailCompany);
                bundle.putString("addressCom", addressCompany);
                bundle.putString("emailUser", emailUser);
                bundle.putString("phoneNumber", phoneNumberCom);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.mail:
                Toast.makeText(this, "Mail mẫu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tuyendung:
                Toast.makeText(this, "Tuyển dụng", Toast.LENGTH_SHORT).show();
                break;
            case R.id.quenmatkhau:
                Toast.makeText(this, "Quên mật khẩu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.doimatkhau:
                Toast.makeText(this, "Đổi mật khẩu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout_student:
                Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}