package com.example.wri.Activity.Admin.Teacher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.wri.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class Admin_Create_Teacher extends AppCompatActivity {
    private ImageButton ibPick;
    private CircleImageView civProfile;
    private Button btnConfirm;
    private ProgressDialog progressDialog;
    private EditText edt_code_teacher_admincreate,edt_name_teacher_admincreate
            ,edt_email_teacher_admincreate,edt_password_teacher_admincreate,
            edt_confirmpassword_teacher_admincreate,edt_phonenumber_teacher_admincreate,
            edt_description_teacher_admincreate;

    private String url = "https://uni2work.000webhostapp.com/WRI/teacher/create_teacher.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__create__teacher);
        init();
        progressDialog = new ProgressDialog(Admin_Create_Teacher.this);
        progressDialog.setMessage("Uploading Image. Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        ibPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Admin_Create_Teacher.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Admin_Create_Teacher.this);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if(response.isPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Create_Teacher.this);
                                    builder.setTitle("Permisstion Required")
                                            .setMessage("Permisstion to assecc your device storage  is required to pick profile image. Please go to setting to enable permisstion to access")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent();
                                                    intent.setAction(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                                                    intent.setData(Uri.fromParts("package",getPackageName(),null));
                                                    startActivityForResult(intent, 51);

                                                }
                                            })
                                            .setNegativeButton("Cancel",null)
                                            .show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

    }

    private void init() {
        ibPick = findViewById(R.id.btn_pick_edit_teacher_admin);
        civProfile = findViewById(R.id.img_edit_teacher_admin);
        btnConfirm = findViewById(R.id.btn_create_teacher);
        edt_code_teacher_admincreate = findViewById(R.id.edt_code_teacher_admincreate);
        edt_name_teacher_admincreate = findViewById(R.id.edt_name_teacher_admincreate);
        edt_email_teacher_admincreate = findViewById(R.id.edt_email_teacher_admincreate);
        edt_password_teacher_admincreate = findViewById(R.id.edt_password_teacher_admincreate);
        edt_confirmpassword_teacher_admincreate = findViewById(R.id.edt_confirmpassword_teacher_admincreate);
        edt_phonenumber_teacher_admincreate = findViewById(R.id.edt_phonenumber_teacher_admincreate);
        edt_description_teacher_admincreate = findViewById(R.id.edt_description_teacher_admincreate);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                final Uri resultUri = result.getUri();

                civProfile.setImageURI(resultUri);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String codeTeacher = edt_code_teacher_admincreate.getText().toString();
                        final String nameTeacher = edt_name_teacher_admincreate.getText().toString();
                        final String emailTeacher = edt_email_teacher_admincreate.getText().toString();
                        final String passwordTeacher = edt_password_teacher_admincreate.getText().toString();
                        final String confirmpassword = edt_confirmpassword_teacher_admincreate.getText().toString();
                        final String phoneTeacher = edt_phonenumber_teacher_admincreate.getText().toString();
                        final String decriptionTeacher = edt_description_teacher_admincreate.getText().toString();
                        //validate
                        if (TextUtils.isEmpty(codeTeacher)) {
                            edt_code_teacher_admincreate.setError("Vui lòng nhập mã của giáo viên");
                            return;
                        }else
                        if (TextUtils.isEmpty(nameTeacher)) {
                            edt_name_teacher_admincreate.setError("Vui lòng nhập tên của giáo viên");
                            return;
                        }else
                        if (TextUtils.isEmpty(emailTeacher)) {
                            edt_email_teacher_admincreate.setError("Vui lòng nhập email của giáo viên");
                            return;
                        }if (TextUtils.isEmpty(passwordTeacher)) {
                            edt_password_teacher_admincreate.setError("Vui lòng nhập mật khẩu của giáo viên");
                            return;
                        }else
                        if (passwordTeacher.length() < 6 || passwordTeacher.length() > 24) {
                            edt_password_teacher_admincreate.setError("Mật khẩu độ dài từ 6 đến 24 ký tự");
                            return;
                        }
                        else
                        if (TextUtils.isEmpty(confirmpassword)) {
                            edt_confirmpassword_teacher_admincreate.setError("Mật khẩu nhập lại không chính xác");
                            return;
                        }else
                        if (TextUtils.isEmpty(phoneTeacher)) {
                            edt_phonenumber_teacher_admincreate.setError("Vui lòng nhập số điện thoại của giáo viên");
                            return;
                        }else
                        if (TextUtils.isEmpty(decriptionTeacher)) {
                            edt_description_teacher_admincreate.setError("Vui lòng nhập mô tả về giáo viên");
                            return;
                        }
                        //validate
                        else {
                        File imageFile = new File (resultUri.getPath());
                        progressDialog.show();
                        AndroidNetworking.upload(url)
                                .addMultipartFile("image",imageFile)
                                .addMultipartParameter("codeTeacher",codeTeacher)
                                .addMultipartParameter("nameTeacher",nameTeacher)
                                .addMultipartParameter("emailTeacher",emailTeacher)
                                .addMultipartParameter("passwordTeacher",passwordTeacher)
                                .addMultipartParameter("phoneTeacher",phoneTeacher)
                                .addMultipartParameter("decriptionTeacher",decriptionTeacher)
                                .setPriority(Priority.HIGH)
                                .build()
                                .setUploadProgressListener(new UploadProgressListener() {
                                    @Override
                                    public void onProgress(long bytesUploaded, long totalBytes) {
                                        float progress = (float) bytesUploaded/totalBytes*100;
                                        progressDialog.setProgress((int)progress);
                                    }
                                })
                                .getAsString(new StringRequestListener() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            Log.d("nnn",response);
                                            progressDialog.dismiss();
                                            JSONObject jsonObject = new JSONObject(response);
                                            int status = jsonObject.getInt("status");
                                            String message = jsonObject.getString("message");
                                            if(status == 0){
                                                Toast.makeText(Admin_Create_Teacher.this, message, Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(Admin_Create_Teacher.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        progressDialog.dismiss();
                                        anError.printStackTrace();
                                        Toast.makeText(Admin_Create_Teacher.this, "Thêm tài khoản giáo viên không thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        }
                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}