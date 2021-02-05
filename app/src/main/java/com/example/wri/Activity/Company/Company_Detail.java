package com.example.wri.Activity.Company;

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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.wri.Activity.Admin.Class.Admin_Edit_Class;
import com.example.wri.Model.Companys;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;
import com.google.gson.internal.$Gson$Preconditions;
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

public class Company_Detail extends AppCompatActivity {
    ImageView imgv_back;
    ImageButton btn_pick_addclass;
    EditText edt_company_name, edt_company_address, edt_company_email, edt_company_phone;
    Button btn_company_update, btnchoose_Com, btnsubmit_imgCom;
    public String idCom, nameCompany, addressCompany, emailUser, thumbnailCompany, phoneNumber;
    ProgressDialog progressDialog;
    CircleImageView img_avatar_company;
    Uri resultUri;

    private String url = "https://uni2work.000webhostapp.com/WRI/company/updateimg_Company.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__detail);
        init();
        Bundle bundle = getIntent().getExtras();
        idCom = bundle.getString("idCom");
        nameCompany = bundle.getString("nameCom");
        thumbnailCompany = bundle.getString("thumbnailCom");
        addressCompany = bundle.getString("addressCom");
        emailUser = bundle.getString("emailUser");
        phoneNumber = bundle.getString("phoneNumber");

        edt_company_name.setText(nameCompany);
        edt_company_address.setText(addressCompany);
        edt_company_email.setText(emailUser);
        edt_company_phone.setText(phoneNumber);

        Picasso.with(getApplicationContext()).load(thumbnailCompany).into(img_avatar_company);

        progressDialog = new ProgressDialog(Company_Detail.this);
        progressDialog.setMessage("Uploading Image. Please wait");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        img_avatar_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Company_Detail.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Company_Detail.this);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {
                                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Company_Detail.this);
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
        btnchoose_Com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Company_Detail.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Company_Detail.this);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if(response.isPermanentlyDenied()){
                                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(Company_Detail.this);
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

       btn_company_update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String txtnameCom = edt_company_name.getText().toString();
               String txtaddressCom = edt_company_address.getText().toString();
               Updateinfor(idCom,txtnameCom,txtaddressCom);
               Log.d("nnn",txtaddressCom+txtnameCom);
           }
       });
    }

    private void init() {
        imgv_back = findViewById(R.id.id_back_admin_company_list);
        btnchoose_Com = findViewById(R.id.btnchoose_Com);
        btnsubmit_imgCom = findViewById(R.id.btnsubmit_imgCom);
        img_avatar_company = findViewById(R.id.img_avatar_company);
        btn_company_update = findViewById(R.id.btn_company_update);
        btn_pick_addclass = findViewById(R.id.btn_pick_addclass);
        edt_company_name = findViewById(R.id.edt_company_name);
        edt_company_address = findViewById(R.id.edt_company_address);
        edt_company_email = findViewById(R.id.edt_company_email);
        edt_company_phone = findViewById(R.id.edt_company_phone);
    }

   private void Updateinfor(String idCompany, String nameCompany, String addressCompany){
       DataService dataService = APIService.getService();
       Call <Companys> callback = dataService.UpdateCompanyinfor( idCompany, nameCompany, addressCompany);
       callback.enqueue(new Callback<Companys>() {
           @Override
           public void onResponse(Call<Companys> call, Response<Companys> response) {
               if(response.body().getIsSuccess() == 0){
                   Toast.makeText(Company_Detail.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
               }

               if(response.body().getIsSuccess() == 1){
                   Toast.makeText(Company_Detail.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<Companys> call, Throwable t) {

           }
       });
   }
//    private void UpdateCompany(final String email) {
//        DataService dataService = APIService.getService();
//        Call<List<Companys>> callback = dataService.UpdateCompany(email);
//        callback.enqueue(new Callback<List<Companys>>() {
//            @Override
//            public void onResponse(Call<List<Companys>> call, Response<List<Companys>> response) {
//                nameCompany = response.body().get(0).getNameCompany();
//                addressCompany = response.body().get(0).getAddressCompany();
//                emailCompany = response.body().get(0).getEmailCompany();
//                phoneCompany = response.body().get(0).getPhoneNumber();
//                thumbnailCompany = response.body().get(0).getThumbnailCompany();
//                strURLthumbnail = thumbnailCompany.substring(thumbnailCompany.indexOf("cropped"), thumbnailCompany.length());
//                Uri thumbnailURI = Uri.parse("file:///data/data/com.example.wri/cache/" + strURLthumbnail);
//
//                img_avatar_company.setImageURI(thumbnailURI);
//
//                edt_company_name.setText(nameCompany);
//                edt_company_address.setText(addressCompany);
//                edt_company_email.setText(emailCompany);
//                edt_company_phone.setText(phoneCompany);
//            }
//
//            @Override
//            public void onFailure(Call<List<Companys>> call, Throwable t) {
//                Toast.makeText(Company_Detail.this, "Lấy thông tin doanh nghiệp", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                final Uri resultUri = result.getUri();
                img_avatar_company.setImageURI(resultUri);
                btnsubmit_imgCom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        File imageFile = new File (resultUri.getPath());
                        progressDialog.show();
                        AndroidNetworking.upload(url)
                                .addMultipartFile("image",imageFile)
                                .addMultipartParameter("idCompany",idCom)
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
                                            Log.d("nnn",idCom);
                                            progressDialog.dismiss();
                                            JSONObject jsonObject = new JSONObject(response);
                                            int status = jsonObject.getInt("status");
                                            String message = jsonObject.getString("message");

                                            if(status == 0){
                                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                            if(status == 1){
                                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                                finish();
                                            }

                                        } catch (JSONException e) {
                                            Toast.makeText(getApplicationContext(), "Pasring Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onError(ANError anError) {
                                        progressDialog.dismiss();
                                        anError.printStackTrace();
                                        Toast.makeText(getApplicationContext(), "Tạo lớp không thành công", Toast.LENGTH_SHORT).show();
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
