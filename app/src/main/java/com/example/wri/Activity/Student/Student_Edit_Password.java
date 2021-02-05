package com.example.wri.Activity.Student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.wri.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Student_Edit_Password extends AppCompatActivity {
    ImageView imgv_back;
    TextInputEditText edt_oldPassword, edt_newPassword, edt_renewPassword;
    Button btnSave;
    String emailStu;

    ProgressDialog progressDialog;

    private String url = "https://uni2work.000webhostapp.com/WRI/student/update_password_student.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__edit__password);
        Intent intent = getIntent();
        emailStu = intent.getStringExtra("emailStudent");
        init();

        progressDialog = new ProgressDialog(Student_Edit_Password.this);
        progressDialog.setMessage("Uploading Image. Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        imgv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strOldPass = edt_oldPassword.getText().toString();
                final String strNewPass = edt_newPassword.getText().toString();
                final String strRenewPass = edt_renewPassword.getText().toString();
                //validate
                if (TextUtils.isEmpty(strOldPass)) {
                    edt_oldPassword.setError("Vui lòng nhập mật khẩu cũ");
                    return;
                } else if (TextUtils.isEmpty(strNewPass)) {
                    edt_newPassword.setError("Vui lòng nhập mật khẩu mới");
                    return;
                } else if (TextUtils.isEmpty(strRenewPass)) {
                    edt_renewPassword.setError("Vui lòng nhập lại mật khẩu mới");
                    return;
                } else if (!TextUtils.equals(strNewPass, strRenewPass)) {
                    Toast.makeText(Student_Edit_Password.this, "Nhập lại mật khẩu mới không chính xác!", Toast.LENGTH_SHORT).show();
                } else {
                    AndroidNetworking.upload(url)
                            .addMultipartParameter("emailUser", emailStu)
                            .addMultipartParameter("oldPassword", strOldPass)
                            .addMultipartParameter("newPassword", strNewPass)
                            .setPriority(Priority.HIGH)
                            .build()
                            .setUploadProgressListener(new UploadProgressListener() {
                                @Override
                                public void onProgress(long bytesUploaded, long totalBytes) {
                                    float progress = (float) bytesUploaded / totalBytes * 100;
                                    progressDialog.setProgress((int) progress);
                                }
                            })
                            .getAsString(new StringRequestListener() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        Log.d("nnn", response);
                                        progressDialog.dismiss();
                                        JSONObject jsonObject = new JSONObject(response);
                                        int status = jsonObject.getInt("status");
                                        String message = jsonObject.getString("message");
                                        if (status == 0) {
                                            Toast.makeText(Student_Edit_Password.this, message, Toast.LENGTH_SHORT).show();
                                            return;
                                        } else {
                                            Toast.makeText(Student_Edit_Password.this, message, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        Toast.makeText(Student_Edit_Password.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    progressDialog.dismiss();
                                    anError.printStackTrace();
                                    Toast.makeText(Student_Edit_Password.this, "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            });
                }
            }
        });
    }

    private void init() {
        imgv_back = findViewById(R.id.imgv_back);
        edt_oldPassword = findViewById(R.id.edt_oldPassword);
        edt_newPassword = findViewById(R.id.edt_newPassword);
        edt_renewPassword = findViewById(R.id.edt_renewPassword);
        btnSave = findViewById(R.id.btnSave);
    }
}