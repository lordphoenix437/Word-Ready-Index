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

public class Admin_Add_Class extends AppCompatActivity {
    private ImageButton ibPick;
    private CircleImageView civProfile;
    private Button btnConfirm;
    private ProgressDialog progressDialog;
    private EditText edt_name_addclass,edt_code_addclass,edt_maxstudent_addclass,edt_description_addclass,edt_openingclass_addclass;
    private String url = "https://uni2work.000webhostapp.com/WRI/class/create_class.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add__class);
        edt_name_addclass = findViewById(R.id.edt_name_addclass);
        edt_code_addclass = findViewById(R.id.edt_code_addclass);
        edt_maxstudent_addclass = findViewById(R.id.edt_maxstudent_addclass);
        edt_description_addclass = findViewById(R.id.edt_description_addclass);
        edt_openingclass_addclass = findViewById(R.id.edt_openingclass_addclass);
        ibPick = findViewById(R.id.btn_pick_addclass);
        civProfile = findViewById(R.id.img_edit_student_admin);
        btnConfirm = findViewById(R.id.btn_create_class);
        progressDialog = new ProgressDialog(Admin_Add_Class.this);
        progressDialog.setMessage("Uploading Image. Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        ibPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Admin_Add_Class.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Admin_Add_Class.this);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if(response.isPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Add_Class.this);
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
    @Override
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
                        final String nameClass = edt_name_addclass.getText().toString();
                        final String codeClass = edt_code_addclass.getText().toString();
                        final String maxstudentClass = edt_maxstudent_addclass.getText().toString();
                        final String decriptionClass = edt_description_addclass.getText().toString();
                        final String openingClass = edt_openingclass_addclass.getText().toString();
                        Log.d("nnn",nameClass + " "+codeClass + " "+maxstudentClass+ " "+ decriptionClass +" ");
                        File imageFile = new File (resultUri.getPath());
                        progressDialog.show();
                        AndroidNetworking.upload(url)
                                .addMultipartFile("image",imageFile)
                                .addMultipartParameter("codeClass",codeClass)
                                .addMultipartParameter("nameClass",nameClass)
                                .addMultipartParameter("maxstudentClass",maxstudentClass)
                                .addMultipartParameter("decriptionClass",decriptionClass)
                                .addMultipartParameter("openingClass",openingClass)
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
                                                Toast.makeText(Admin_Add_Class.this, message, Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(Admin_Add_Class.this, "Pasring Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        progressDialog.dismiss();
                                        anError.printStackTrace();
                                        Toast.makeText(Admin_Add_Class.this, "Tạo lớp không thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void onBack(View view) {
        finish();
    }
}