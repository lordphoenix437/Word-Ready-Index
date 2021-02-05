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
import android.widget.TextView;
import android.widget.Toast;

import com.example.wri.Activity.Admin.Admin_Main;
import com.example.wri.Activity.Company.Company_Main;
import com.example.wri.Activity.Student.Student_Main;
import com.example.wri.Activity.Teacher.Main_Teachers;
import com.example.wri.Model.Teacher;
import com.example.wri.Model.Users;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
//    private static final Pattern PASSWORD_PATTEN =
//            Pattern.compile("^" +
//                    "(?=.*[0-9])" +         //Ít nhất một chữ số
//                    "(?=.*[a-z])" +         //Ít nhất một chữ thường
//                    //"(?=.*[a-zA-Z])" +      //Any letter
//                    "(?=.*[A-Z])" +         //Ít nhất một chữ hoa
//                    "(?=.*[@#$%^&*=])" +    //Ít nhất một kí tự đặc biệt
//                    "(?=\\S+$)" +           //Không được có khoảng trắng
//                    ".{6,}" +               //Có ít nhất 6 kí tự
//                    "$");
    TextView gotoRegister;
    EditText email_login, password_login;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Singup_Main.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        final String email = email_login.getText().toString().trim();
        final String password = password_login.getText().toString().trim();
        if (email.isEmpty()) {
            email_login.setError("Email Không được để trống");
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_login.setError("Vui lòng nhập đúng định dạng địa chỉ email");
            return;
        }
        else if (password.isEmpty()) {
            password_login.setError("Password Không được để trống");
            return;
        }
//        else if (!PASSWORD_PATTEN.matcher(password).matches()) {
//            password_login.setError("Mật khẩu quá yếu");
//            return;
//        }
        else {
            DataService dataService = APIService.getService();
            Call<Users> callback = dataService.login(email, password);
            callback.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.body().getIsSuccess() == 1) {
                        //get email
                        String user = response.body().getEmail();
                        Intent intent = new Intent(Login.this, Student_Main.class);
                        intent.putExtra("emailStudent", user);
                        startActivity(intent);
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    if (response.body().getIsSuccess() == 2) {
                        //get email
                        String user = response.body().getEmail();
                        Intent i = new Intent(Login.this, Main_Teachers.class);
                        i.putExtra("emailteacher", user);
                        startActivity(i);
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    if (response.body().getIsSuccess() == 3) {
                        String user = response.body().getEmail();
                        Intent i = new Intent(Login.this, Company_Main.class);
                        i.putExtra("emailCompany", user);
                        startActivity(i);

                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    if (response.body().getIsSuccess() == 0) {
                        String user = response.body().getEmail();
                        startActivity(new Intent(Login.this, Admin_Main.class));
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    if (response.body().getIsSuccess() == 404) {
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    if (response.body().getIsSuccess() == 4044) {
                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("avc", response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(Login.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    Log.d("avc", t.toString());
                }
            });
        }
    }

    private void init() {
        btn_login = findViewById(R.id.btn_Login);
        email_login = findViewById(R.id.email_login);
        password_login = findViewById(R.id.password_login);
        gotoRegister = findViewById(R.id.gotoRegister);
    }
}