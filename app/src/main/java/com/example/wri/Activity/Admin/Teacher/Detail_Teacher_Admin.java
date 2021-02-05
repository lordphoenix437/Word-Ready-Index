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
import com.example.wri.Activity.Admin.Class.Admin_Add_Class;
import com.example.wri.Model.Students;
import com.example.wri.Model.Teacher;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;
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

public class Detail_Teacher_Admin extends AppCompatActivity {
    Teacher teacher;
    private ImageButton ibPick;
    private CircleImageView civProfile;
    private ProgressDialog progressDialog;
    private String url = "https://uni2work.000webhostapp.com/WRI/teacher/update_teacher.php";
    EditText tv_name_teacher_detailteacher,tv_codeteacher_detailteacher,tv_email_detailteacher,
            tv_phone_detailteacher,tv_description_detailteacher;
    Button btn_edit_detailteacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__teacher__admin);
        init();
        Dataintent();
        Getdata(teacher.getCodeTeacher());
        progressDialog = new ProgressDialog(Detail_Teacher_Admin.this);
        progressDialog.setMessage("Uploading Image. Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        ibPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Detail_Teacher_Admin.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Detail_Teacher_Admin.this);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if(response.isPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Detail_Teacher_Admin.this);
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

    private void Getdata(String codeTeacher) {
        DataService dataService = APIService.getService();
        Call<List<Teacher>> callpack = dataService.getDetailTeacher_admin(codeTeacher);
        callpack.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                tv_name_teacher_detailteacher.setText(teacher.getNameTeacher());
                tv_codeteacher_detailteacher.setText(teacher.getCodeTeacher());
                tv_email_detailteacher.setText(teacher.getEmailUser());
                tv_phone_detailteacher.setText(teacher.getPhoneUser());
                tv_description_detailteacher.setText(teacher.getDescriptionTeacher());
                Picasso.with(getApplicationContext()).load(teacher.getThumbnailTeacher()).into(civProfile);
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {

            }
        });

    }
    private void Dataintent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("teacher_admin")) {
                teacher = (Teacher) intent.getSerializableExtra("teacher_admin");
            }
        }

    }
    private void init(){
        tv_name_teacher_detailteacher = findViewById(R.id.tv_name_teacher_detailteacher);
        tv_codeteacher_detailteacher = findViewById(R.id.tv_codeteacher_detailteacher);
        tv_email_detailteacher = findViewById(R.id.tv_email_detailteacher);
        tv_phone_detailteacher = findViewById(R.id.tv_phone_detailteacher);
        tv_description_detailteacher = findViewById(R.id.tv_description_detailteacher);
        btn_edit_detailteacher = findViewById(R.id.btn_edit_detailteacher);
        ibPick = findViewById(R.id.btn_pick_edit_teacher_admin);
        civProfile = findViewById(R.id.img_edit_teacher_admin);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                final Uri resultUri = result.getUri();
                civProfile.setImageURI(resultUri);
                btn_edit_detailteacher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String nameTeacher = tv_name_teacher_detailteacher.getText().toString();
                        final String codeTeacher = tv_codeteacher_detailteacher.getText().toString();
                        final String emailTeacher = tv_email_detailteacher.getText().toString();
                        final String phoneTeacher = tv_phone_detailteacher.getText().toString();
                        final String decriptionTeacher = tv_description_detailteacher.getText().toString();
                        File imageFile = new File (resultUri.getPath());
                        progressDialog.show();
                        AndroidNetworking.upload(url)
                                .addMultipartFile("image",imageFile)
                                .addMultipartParameter("nameTeacher",nameTeacher)
                                .addMultipartParameter("codeTeacher",codeTeacher)
                                .addMultipartParameter("emailTeacher",emailTeacher)
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
                                            progressDialog.dismiss();
                                            JSONObject jsonObject = new JSONObject(response);
                                            int status = jsonObject.getInt("status");
                                            String message = jsonObject.getString("message");
                                            if(status == 0){
                                                Toast.makeText(Detail_Teacher_Admin.this, message, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(Detail_Teacher_Admin.this, "Pasring Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onError(ANError anError) {
                                        progressDialog.dismiss();
                                        anError.printStackTrace();
                                        Toast.makeText(Detail_Teacher_Admin.this, "Tạo lớp không thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}