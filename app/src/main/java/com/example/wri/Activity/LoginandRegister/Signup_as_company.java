package com.example.wri.Activity.LoginandRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wri.Model.Companys;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;


import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_as_company extends AppCompatActivity {
        private static  Pattern PASSWORD_PATTEN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //Ít nhất một chữ số
                    "(?=.*[a-z])" +         //Ít nhất một chữ thường
                    //"(?=.*[a-zA-Z])" +      //Any letter
                    "(?=.*[A-Z])" +         //Ít nhất một chữ hoa
                    "(?=.*[@#$%^&*=])" +    //Ít nhất một kí tự đặc biệt
                    "(?=\\S+$)" +           //Không được có khoảng trắng
                    ".{6,}" +               //Có ít nhất 6 kí tự
                    "$");
    Button btn_return_signup_company;
    Button btn_signup_company;
    ImageView img_signup_as_company;
    EditText email_company_signup,
            pass_company_signup,
            confirmpassword_company_signup,
            name_company_signup,
            address_company_signup,
            phonenumber_company_signup;

    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_as_company);
        init();


        btn_return_signup_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup_as_company.this, Singup_Main.class);
                startActivity(intent);
            }
        });
        btn_signup_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUserData();

            }


        });

    }
    private void validateUserData() {
        final String    name = name_company_signup.getText().toString().trim();
        final String   email = email_company_signup.getText().toString().trim();
        final String   password = pass_company_signup.getText().toString().trim();
        final String  confirm = confirmpassword_company_signup.getText().toString().trim();
        final String   phonenumber = phonenumber_company_signup.getText().toString().trim();
        final String address = address_company_signup.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            name_company_signup.setError("Tên công ty Không được để trống");
            return;
        }else
        if (TextUtils.isEmpty(email)) {
            email_company_signup.setError("Email Không được để trống");
            return;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_company_signup.setError("Vui lòng nhập đúng định dạng email");
        }else
        if (TextUtils.isEmpty(password)) {
            pass_company_signup.setError("Mật khẩu không được để trống");
            return;
        }
//        else
//        if (password.length() < 6 || password.length() > 24) {
//            pass_company_signup.setError("Mật khẩu độ dài từ 6 đến 24 ký tự");
//            return;
//        }
        else if (!PASSWORD_PATTEN.matcher(password).matches()) {
            pass_company_signup.setError("Mật khẩu phải có: \n" +
                    "- Ít nhất một chữ số \n" +
                    "- Ít nhất một chữ thường \n" +
                    "- Ít nhất một chữ hoa \n" +
                    "- Ít nhất một kí tự đặc biệt \n" +
                    "- Không được có khoảng trắng \n" +
                    "- Ít nhất 6 kí tự");
            return;
        }
        else
        if (TextUtils.isEmpty(confirm)) {
            confirmpassword_company_signup.setError("Mật khẩu nhập lại không chính xác");
            return;
        }else
        if(!password.equals(confirm)){
            confirmpassword_company_signup.setError("Mật khẩu nhập lại không chính xác");
            return;
        }else if(TextUtils.isEmpty(phonenumber)){
            phonenumber_company_signup.setError("Bạn cần nhập số điện thoại");
            return;
        }else if(TextUtils.isEmpty(address)){
            address_company_signup.setError("Bạn cần nhập địa chỉ");
            return;
        }else {
            DataService dataService = APIService.getService();
            Call<Companys> callback = dataService.createcompany(name,email,password,phonenumber,address);
            callback.enqueue(new Callback<Companys>() {
                @Override
                public void onResponse(Call<Companys> call, Response<Companys> response) {
                    if(response.body().getIsSuccess() == 1){
                        //get username
                     //   String user = response.body().getEmailCompany();
                        startActivity(new Intent(Signup_as_company.this,Login.class));
                       // Log.d("tag",user + response.body().getMessage());
                        Toast.makeText(Signup_as_company.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else if(response.body().getIsSuccess() == 0) {
                        Toast.makeText(Signup_as_company.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        Log.d("tag",response.body().getMessage());
                    }else {
                        Toast.makeText(Signup_as_company.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        Log.d("tag",response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<Companys> call, Throwable t) {
                    Toast.makeText(Signup_as_company.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void init() {
        btn_return_signup_company = findViewById(R.id.btn_return_signup_company);
        btn_signup_company = findViewById(R.id.btn_signup_company);
        name_company_signup = findViewById(R.id.name_company_signup);
        email_company_signup = findViewById(R.id.email_company_signup);
        pass_company_signup = findViewById(R.id.password_company_signup);
        confirmpassword_company_signup = findViewById(R.id.confirmpassword_company_signup);
        phonenumber_company_signup = findViewById(R.id.phonenumber_company_signup);
        address_company_signup = findViewById(R.id.address_company_signup);
    }




}