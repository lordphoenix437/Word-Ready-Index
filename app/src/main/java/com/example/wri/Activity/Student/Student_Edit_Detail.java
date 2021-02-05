package com.example.wri.Activity.Student;

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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Edit_Detail extends AppCompatActivity {
    ImageView imgv_back;
    TextInputEditText edt_nameStu, edt_codeStu, edt_birthdayStu, edt_majorStu, edt_emailStu, edt_phoneStu;
    Button btnSave;
    String nameStu, codeStu, birthdayStu, majorStu, emailStu, phoneStu,thumbnailStu,strURLthumbnail;
    ProgressDialog progressDialog;
    ImageView imgvEditAvatar;
    CircleImageView imgvAvatarStu;
    Uri resultUri;

    private String url = "https://uni2work.000webhostapp.com/WRI/student/update_detail_student.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__edit__detail);
        Intent intent=getIntent();
        emailStu = intent.getStringExtra("emailStudent");
        init();
        GetdataStudent(emailStu);

        progressDialog = new ProgressDialog(Student_Edit_Detail.this);
        progressDialog.setMessage("Uploading Image. Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        imgvEditAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Student_Edit_Detail.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Student_Edit_Detail.this);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Student_Edit_Detail.this);
                                    builder.setTitle("Permisstion Required")
                                            .setMessage("Permisstion to assecc your device storage  is required to pick profile image. Please go to setting to enable permisstion to access")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent();
                                                    intent.setAction(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                                                    intent.setData(Uri.fromParts("package", getPackageName(), null));
                                                    startActivityForResult(intent, 51);

                                                }
                                            })
                                            .setNegativeButton("Cancel", null)
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

        imgv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strNameStu = edt_nameStu.getText().toString();
                final String strBirthdayStu = edt_birthdayStu.getText().toString();
                final String strPhoneStu = edt_phoneStu.getText().toString();
                final String strMajor = edt_majorStu.getText().toString();
                //validate
                if (TextUtils.isEmpty(strNameStu)) {
                    edt_nameStu.setError("Vui lòng nhập tên");
                    return;
                } else if (TextUtils.isEmpty(strBirthdayStu)) {
                    edt_birthdayStu.setError("Vui lòng nhập ngày sinh");
                    return;
                } else if (TextUtils.isEmpty(strPhoneStu)) {
                    edt_phoneStu.setError("Vui lòng nhập số điện thoại");
                    return;
                } else if (TextUtils.isEmpty(strMajor)) {
                    edt_majorStu.setError("Vui lòng nhập tên ngành");
                    return;
                }
                //validate
                else {
                    File imageFile;
                    if (resultUri==null){
                        imageFile=new File("/data/data/com.example.wri/cache/"+strURLthumbnail);
                    }else {
                        imageFile = new File(resultUri.getPath());
                    }
                    progressDialog.show();
                    AndroidNetworking.upload(url)
                            .addMultipartFile("image", imageFile)
                            .addMultipartParameter("emailUser", emailStu)
                            .addMultipartParameter("nameStudent", strNameStu)
                            .addMultipartParameter("birthdayStudent", strBirthdayStu)
                            .addMultipartParameter("phoneUser", strPhoneStu)
                            .addMultipartParameter("major", strMajor)
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
                                            Toast.makeText(Student_Edit_Detail.this, message, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        Toast.makeText(Student_Edit_Detail.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    progressDialog.dismiss();
                                    anError.printStackTrace();
                                    Toast.makeText(Student_Edit_Detail.this, "Sửa thông tin không thành công", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            });
                    Toast.makeText(Student_Edit_Detail.this, "Sửa thông tin thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        imgv_back = findViewById(R.id.id_back_student_detail);
        imgvAvatarStu = findViewById(R.id.imgvAvatarStu);
        imgvEditAvatar = findViewById(R.id.imgvEditAvatar);
        btnSave = findViewById(R.id.btnSave);
        edt_nameStu = findViewById(R.id.edt_nameStu);
        edt_codeStu = findViewById(R.id.edt_codeStu);
        edt_birthdayStu = findViewById(R.id.edt_birthdayStu);
        edt_majorStu = findViewById(R.id.edt_majorStu);
        edt_emailStu = findViewById(R.id.edt_emailStu);
        edt_phoneStu = findViewById(R.id.edt_phoneStu);
    }

    private void GetdataStudent(final String email) {
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getDetailStudent(email);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                nameStu = response.body().get(0).getNameStudent();
                codeStu = response.body().get(0).getCodeStudent();
                birthdayStu = response.body().get(0).getBirthdayStudent();
                majorStu = response.body().get(0).getMajor();
                emailStu = response.body().get(0).getEmailUser();
                phoneStu = response.body().get(0).getPhoneUser();
                thumbnailStu=response.body().get(0).getThumbnailStudent();
                //cắt chuỗi để lấy tên ảnh trong thư mục chứa sau đó ghép với uri mặc định để load ảnh lên imageview||cách tạm thời
//                strURLthumbnail=thumbnailStu.substring(thumbnailStu.indexOf("cropped"),thumbnailStu.length());
//                Uri thumbnailURI=Uri.parse("file:///data/data/com.example.wri/cache/"+strURLthumbnail);

                Picasso.with(getApplicationContext()).load(thumbnailStu).into(imgvAvatarStu);
                edt_nameStu.setText(nameStu);
                edt_codeStu.setText(codeStu);
                edt_birthdayStu.setText(birthdayStu);
                edt_majorStu.setText(majorStu);
                edt_emailStu.setText(emailStu);
                edt_phoneStu.setText(phoneStu);
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {
                Toast.makeText(Student_Edit_Detail.this, "Lấy thông tin học sinh thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();

                imgvAvatarStu.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}