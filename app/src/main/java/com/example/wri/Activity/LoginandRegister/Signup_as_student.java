package com.example.wri.Activity.LoginandRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;


import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_as_student extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTEN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //Ít nhất một chữ số
                    "(?=.*[a-z])" +         //Ít nhất một chữ thường
                    //"(?=.*[a-zA-Z])" +      //Any letter
                    "(?=.*[A-Z])" +         //Ít nhất một chữ hoa
                    "(?=.*[@#$%^&*=])" +    //Ít nhất một kí tự đặc biệt
                    "(?=\\S+$)" +           //Không được có khoảng trắng
                    ".{6,}" +               //Có ít nhất 6 kí tự
                    "$");
    Button btn_signup_as_student, btn_return_signup_student;
    EditText email_student_signup,
            password_student_signup,
            confirmpassword_student_signup,
            name_student_signup,
            major_student_signup,
            phonenumber_student_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_as_student);

        init();

        btn_return_signup_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup_as_student.this, Singup_Main.class));
            }
        });
        btn_signup_as_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUserData();
            }
        });

    }

    private void validateUserData() {
        final String email = email_student_signup.getText().toString();
        final String name = name_student_signup.getText().toString();
        final String password = password_student_signup.getText().toString();
        final String comfirmpassword = confirmpassword_student_signup.getText().toString();
        final String phone = phonenumber_student_signup.getText().toString();
        final String major = major_student_signup.getText().toString();
        if (TextUtils.isEmpty(name)) {
            name_student_signup.setError("Tên Không được để trống");
            return;
        } else if (TextUtils.isEmpty(email)) {
            email_student_signup.setError("Email không được để trống");
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_student_signup.setError("Vui lòng nhập đúng định dạng email");
        } else if (TextUtils.isEmpty(password)) {
            password_student_signup.setError("Mật khẩu không được để trống");
            return;
        } else if (!PASSWORD_PATTEN.matcher(password).matches()) {
            password_student_signup.setError("Mật khẩu phải có: \n" +
                    "- Ít nhất một chữ số \n" +
                    "- Ít nhất một chữ thường \n" +
                    "- Ít nhất một chữ hoa \n" +
                    "- Ít nhất một kí tự đặc biệt \n" +
                    "- Không được có khoảng trắng \n" +
                    "- Ít nhất 6 kí tự");
            return;
        }
//        else if (password.length() < 6 || password.length() > 24) {
//            password_student_signup.setError("Mật khẩu độ dài từ 6 đến 24 ký tự");
//            return;
//        }
        else if (TextUtils.isEmpty(comfirmpassword)) {
            confirmpassword_student_signup.setError("Mật khẩu nhập lại không chính xác");

            return;
        } else if (!password.equals(comfirmpassword)) {
            confirmpassword_student_signup.setError("Mật khẩu nhập lại không chính xác");
            return;
        }
        if (TextUtils.isEmpty(major)) {
            major_student_signup.setError("Bạn cần nhập chuyên ngành");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            phonenumber_student_signup.setError("Bạn cần nhập số điện thoại");
            return;
        } else {
            DataService dataService = APIService.getService();
            Call<Students> callback = dataService.createstudent(name, email, password, phone, major);
            callback.enqueue(new Callback<Students>() {
                @Override
                public void onResponse(Call<Students> call, Response<Students> response) {
                    if (response.body().getIsSuccess() == 1) {

                        startActivity(new Intent(Signup_as_student.this, Login.class));
                        Toast.makeText(Signup_as_student.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Signup_as_student.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<Students> call, Throwable t) {
                    Toast.makeText(Signup_as_student.this, "Email Đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


    private void init() {
        btn_return_signup_student = findViewById(R.id.btn_return_signup_student);
        btn_signup_as_student = findViewById(R.id.btn_signup_as_student);
        email_student_signup = findViewById(R.id.email_student_signup);
        password_student_signup = findViewById(R.id.password_student_signup);
        confirmpassword_student_signup = findViewById(R.id.confirmpassword_student_signup);
        phonenumber_student_signup = findViewById(R.id.phonenumber_student_signup);
        major_student_signup = findViewById(R.id.major_student_signup);
        name_student_signup = findViewById(R.id.name_student_signup);
    }


}