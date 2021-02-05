package com.example.wri.Activity.Admin.Class;

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
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.wri.Activity.Admin.Teacher.Detail_Teacher_Admin;
import com.example.wri.R;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Admin_Edit_Class extends AppCompatActivity {
    EditText edt_name_class_editclass_admin,edt_code_class_editclass_admin,
            edt_maxstudent_class_editclass_admin,edt_opening_class_editclass_admin,
            edt_description_class_editclass_admin;
    Button btn_update_class_editclass_admin;
    TextView id_class_editclass_admin;
    private ImageButton ibPick;
    private CircleImageView civProfile;
    private ProgressDialog progressDialog;
    private String url = "https://uni2work.000webhostapp.com/WRI/class/update_class.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit__class);
        init();
        //get Intent Bundle
        Intent in = getIntent();
        Bundle b = in.getExtras();
        final String id = b.getString("id");
        String nameclass = b.getString("nameclass");
        String maxclass = b.getString("maxclass");
        String decriptionclass = b.getString("decriptionclass");
        String codeclass = b.getString("codeclass");
        String openingclass = b.getString("openingclass");
        String thumbnailclass = b.getString("thumbnailclass");
        //
        edt_name_class_editclass_admin.setText(nameclass);
        edt_code_class_editclass_admin.setText(codeclass);
        edt_maxstudent_class_editclass_admin.setText(maxclass);
        edt_opening_class_editclass_admin.setText(openingclass);
        edt_description_class_editclass_admin.setText(decriptionclass);
        id_class_editclass_admin.setText(id);
        Picasso.with(getApplicationContext()).load(thumbnailclass).into(civProfile);

        //
        progressDialog = new ProgressDialog(Admin_Edit_Class.this);
        progressDialog.setMessage("Uploading Image. Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        ibPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Admin_Edit_Class.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Admin_Edit_Class.this);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if(response.isPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Edit_Class.this);
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
        edt_name_class_editclass_admin = findViewById(R.id.edt_name_class_editclass_admin);
        edt_code_class_editclass_admin = findViewById(R.id.edt_code_class_editclass_admin);
        edt_maxstudent_class_editclass_admin = findViewById(R.id.edt_maxstudent_class_editclass_admin);
        edt_opening_class_editclass_admin = findViewById(R.id.edt_opening_class_editclass_admin);
        edt_description_class_editclass_admin = findViewById(R.id.edt_description_class_editclass_admin);
        ibPick = findViewById(R.id.btn_pick_editclass_admin);
        civProfile = findViewById(R.id.img_editclass_admin);
        btn_update_class_editclass_admin = findViewById(R.id.btn_update_class_editclass_admin);
        id_class_editclass_admin = findViewById(R.id.id_class_editclass_admin);
    }

    public void onBack(View view) {
        finish();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                final Uri resultUri = result.getUri();
                civProfile.setImageURI(resultUri);
                btn_update_class_editclass_admin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final  String id = id_class_editclass_admin.getText().toString();
                        Log.d("nnn",id);
                        final String nameClass = edt_name_class_editclass_admin.getText().toString();
                        final String codeClass = edt_code_class_editclass_admin.getText().toString();
                        final String maxStudentClass = edt_maxstudent_class_editclass_admin.getText().toString();
                        final String openingClass = edt_opening_class_editclass_admin.getText().toString();
                        final String decriptionClass = edt_description_class_editclass_admin.getText().toString();
                        File imageFile = new File (resultUri.getPath());
                        progressDialog.show();
                        AndroidNetworking.upload(url)
                                .addMultipartFile("image",imageFile)
                                .addMultipartParameter("idClass",id)
                                .addMultipartParameter("nameClass",nameClass)
                                .addMultipartParameter("codeClass",codeClass)
                                .addMultipartParameter("maxStudentClass",maxStudentClass)
                                .addMultipartParameter("openingClass",openingClass)
                                .addMultipartParameter("decriptionClass",decriptionClass)
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
                                                Toast.makeText(Admin_Edit_Class.this, message, Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(Admin_Edit_Class.this, "Pasring Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onError(ANError anError) {
                                        progressDialog.dismiss();
                                        anError.printStackTrace();
                                        Toast.makeText(Admin_Edit_Class.this, "Tạo lớp không thành công", Toast.LENGTH_SHORT).show();
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