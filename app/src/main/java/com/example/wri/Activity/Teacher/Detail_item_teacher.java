package com.example.wri.Activity.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.wri.Activity.Admin.Teacher.Detail_Teacher_Admin;
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

public class Detail_item_teacher extends AppCompatActivity {
     private String codeteacher,nameTeacher,descriptionTeacher,thumbnailTeacher,emailteacher,phoneUser;
     private TextView tv_name_teacher_detailteacher_teacher,tv_codeteacher_detailteacher_teacher,
             tv_email_detailteacher_teacher,tv_phone_detailteacher_teacher,tv_description_detailteacher_teacher;
     private Button btn_edit_detailteacher_teacher;
    private ImageButton ibPick;
    private CircleImageView civProfile;
    private ProgressDialog progressDialog;
    private String url = "https://uni2work.000webhostapp.com/WRI/teacher/update_teacher.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_teacher);
        init();
        DataInten();
        GetTeacher_email(emailteacher);
        progressDialog = new ProgressDialog(Detail_item_teacher.this);
        progressDialog.setMessage("Uploading Image. Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        ibPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Detail_item_teacher.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Detail_item_teacher.this);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if(response.isPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Detail_item_teacher.this);
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
        tv_email_detailteacher_teacher.setText(emailteacher);
    }



    private void GetTeacher_email(final String emailteacher){
        DataService dataService = APIService.getService();
        Call<List<Teacher>> call = dataService.getTeacher_email_teacher(emailteacher);
        call.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                nameTeacher = response.body().get(0).getNameTeacher();
                codeteacher = response.body().get(0).getCodeTeacher();
                descriptionTeacher = response.body().get(0).getDescriptionTeacher();
                phoneUser = response.body().get(0).getPhoneUser();
                tv_name_teacher_detailteacher_teacher.setText(nameTeacher);
                tv_codeteacher_detailteacher_teacher.setText(codeteacher);
                tv_phone_detailteacher_teacher.setText(phoneUser);
                tv_description_detailteacher_teacher.setText(descriptionTeacher);
                Picasso.with(getApplicationContext()).load(response.body().get(0).getThumbnailTeacher()).into(civProfile);
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
            }
        });

    }
    private void init() {
        tv_name_teacher_detailteacher_teacher = findViewById(R.id.tv_name_teacher_detailteacher_teacher);
        tv_codeteacher_detailteacher_teacher = findViewById(R.id.tv_codeteacher_detailteacher_teacher);
        tv_email_detailteacher_teacher = findViewById(R.id.tv_email_detailteacher_teacher);
        tv_phone_detailteacher_teacher = findViewById(R.id.tv_phone_detailteacher_teacher);
        tv_description_detailteacher_teacher = findViewById(R.id.tv_description_detailteacher_teacher);
        btn_edit_detailteacher_teacher = findViewById(R.id.btn_edit_detailteacher_teacher);
        ibPick = findViewById(R.id.btn_pick_edit_teacher_teacher);
        civProfile = findViewById(R.id.img_edit_teacher_teacher);
    }
    private void DataInten(){
        Intent i = getIntent();
        if(i != null){
            if(i.hasExtra("emailteacher")){
                emailteacher = i.getStringExtra("emailteacher");
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                final Uri resultUri = result.getUri();
                civProfile.setImageURI(resultUri);
                btn_edit_detailteacher_teacher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String nameTeacher = tv_name_teacher_detailteacher_teacher.getText().toString();
                        final String codeTeacher = tv_codeteacher_detailteacher_teacher.getText().toString();
                        final String emailTeacher = tv_email_detailteacher_teacher.getText().toString();
                        final String phoneTeacher = tv_phone_detailteacher_teacher.getText().toString();
                        final String decriptionTeacher = tv_description_detailteacher_teacher.getText().toString();
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
                                                Toast.makeText(Detail_item_teacher.this, message, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(Detail_item_teacher.this, "Pasring Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onError(ANError anError) {
                                        progressDialog.dismiss();
                                        anError.printStackTrace();
                                        Toast.makeText(Detail_item_teacher.this, "Tạo lớp không thành công", Toast.LENGTH_SHORT).show();
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