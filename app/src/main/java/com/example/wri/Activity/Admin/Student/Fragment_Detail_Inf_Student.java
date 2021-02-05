package com.example.wri.Activity.Admin.Student;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.wri.Model.Students;
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

public class Fragment_Detail_Inf_Student  extends Fragment {
    Button btneditinf;
    TextView tv_name_student_detailadminf,tv_codestudent_detailstudentf,tv_birthday_student_detailadminf,
            tv_major_detailstudentf,tv_email_detailstudentf,tv_phone_detailstudentf;
    Students students;

    private CircleImageView civProfile;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_inf_detailstu_admin, container, false);
        Dataintent();
        Getdata(students.getId());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btneditinf = view.findViewById(R.id.btn_edit_detailstudentf);
        tv_name_student_detailadminf = view.findViewById(R.id.tv_name_student_detailadminf);
        tv_codestudent_detailstudentf = view.findViewById(R.id.tv_codestudent_detailstudentf);
        tv_birthday_student_detailadminf = view.findViewById(R.id.tv_birthday_student_detailadminf);
        tv_major_detailstudentf = view.findViewById(R.id.tv_major_detailstudentf);
        tv_email_detailstudentf = view.findViewById(R.id.tv_email_detailstudentf);
        tv_phone_detailstudentf = view.findViewById(R.id.tv_phone_detailstudentf);
        civProfile =view.findViewById(R.id.img_edit_student_admin);
        btneditinf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tv_email_detailstudentf.getText().toString();
                String codeStu = tv_codestudent_detailstudentf.getText().toString();
                String nameStu = tv_name_student_detailadminf.getText().toString();
                String birthdayStu = tv_birthday_student_detailadminf.getText().toString();
                String major = tv_major_detailstudentf.getText().toString();
                String phoneStu = tv_phone_detailstudentf.getText().toString();
                if (TextUtils.isEmpty(email)){
                    tv_email_detailstudentf.setError("Email Không được để trống");
                    return;
                }else if(TextUtils.isEmpty(codeStu)){
                    tv_codestudent_detailstudentf.setError("Mã sinh viên Không được để trống");
                    return;
                }else if(TextUtils.isEmpty(nameStu)){
                    tv_name_student_detailadminf.setError("Tên sinh viên Không được để trống");
                    return;
                }else if(TextUtils.isEmpty(birthdayStu)){
                    tv_birthday_student_detailadminf.setError("Tên sinh viên Không được để trống");
                    return;
                }else if(TextUtils.isEmpty(major)){
                    tv_major_detailstudentf.setError("Tên sinh viên Không được để trống");
                    return;
                }else if(TextUtils.isEmpty(phoneStu)){
                    tv_phone_detailstudentf.setError("Tên sinh viên Không được để trống");
                    return;
                }else {
                    updateStudent(email,codeStu,nameStu,birthdayStu,major,phoneStu);
                }
            }
        });


    }
    private void Getdata(String email) {
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getDetailStudent_admin(email);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                tv_name_student_detailadminf.setText(students.getNameStudent());
                tv_codestudent_detailstudentf.setText(students.getCodeStudent());
                tv_birthday_student_detailadminf.setText(students.getBirthdayStudent());
                tv_major_detailstudentf.setText(students.getMajor());
                tv_email_detailstudentf.setText(students.getEmailUser());
                tv_phone_detailstudentf.setText(students.getPhoneUser());
                Picasso.with(getContext()).load(students.getThumbnailStudent()).into(civProfile);
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {

            }
        });
    }
    private void Dataintent() {
        Intent intent = getActivity().getIntent();
        if(intent != null){
            if(intent.hasExtra("student_admin")) {
                students = (Students) intent.getSerializableExtra("student_admin");
            }
        }

    }


    private void   updateStudent(String emailStu, String codeStu, String nameStu,String birthdayStu,String major,String phoneStu){
        DataService dataService = APIService.getService();
        Call<Students> callback = dataService.updateDetailStudent(emailStu,codeStu,nameStu,birthdayStu,major,phoneStu);
        callback.enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> call, Response<Students> response) {
                if(response.body().getIsSuccess() == 1){
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                if(response.body().getIsSuccess() == 0){
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Students> call, Throwable t) {

            }
        });
    }

}
