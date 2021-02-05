package com.example.wri.Activity.Student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Activity.LoginandRegister.Login;
import com.example.wri.Adapter.Adapter_Joined_Classes;
import com.example.wri.Model.Classs;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Fragment_Detail extends Fragment {
    Students students;

    TextView tv_nameStudent, tv_codeStudent, tv_birthdayStudent, tv_majorStudent, tv_emailStudent, tv_phoneStudent, tvLogout;
    Button btnEditPassword, btnEditDetail;
    String nameStu, codeStu, birthdayStu, majorStu, emailStu, phoneStu, thumbnailStu, strURLthumbnail;
    CircleImageView imgv_avatarStudent;
    String idStudent,emailStudent;

    RecyclerView rv_joinedClasses;

    Adapter_Joined_Classes adapter;
    ArrayList<Classs> listClass;

    private String url = "http://192.168.0.103/wri/student/update_detail_student.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student___detail, container, false);
        imgv_avatarStudent = view.findViewById(R.id.imgv_avatarStudent);
        btnEditPassword = view.findViewById(R.id.btnEditPassword);
        btnEditDetail = view.findViewById(R.id.btnEditDetail);
        tv_nameStudent = view.findViewById(R.id.tv_nameStudent);
        tv_codeStudent = view.findViewById(R.id.tv_codeStudent);
        tv_birthdayStudent = view.findViewById(R.id.tv_birthdayStudent);
        tv_majorStudent = view.findViewById(R.id.tv_majorStudent);
        tv_emailStudent = view.findViewById(R.id.tv_emailStudent);
        tv_phoneStudent = view.findViewById(R.id.tv_phoneStudent);
        tvLogout = view.findViewById(R.id.tvLogout);
        rv_joinedClasses = view.findViewById(R.id.rv_joinedClasses);

        emailStudent = getArguments().getString("emailStudent");

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Login.class));
                getActivity().finish();
            }
        });

        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Student_Edit_Password.class);
                intent.putExtra("emailStudent", emailStudent);
                startActivity(intent);
            }
        });

        btnEditDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Student_Edit_Detail.class);
                intent.putExtra("emailStudent", emailStudent);
                startActivity(intent);
            }
        });
        GetdataStudent(emailStudent);
        return view;
    }

    @Override
    public void onResume() {
        GetdataStudent(emailStudent);
        super.onResume();
    }

    private void GetdataStudent(final String email) {
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getDetailStudent(email);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                idStudent=response.body().get(0).getId();
                GetJoinedClasses(idStudent);
                nameStu = response.body().get(0).getNameStudent();
                codeStu = response.body().get(0).getCodeStudent();

                birthdayStu = response.body().get(0).getBirthdayStudent();
                majorStu = response.body().get(0).getMajor();
                emailStu = response.body().get(0).getEmailUser();
                phoneStu = response.body().get(0).getPhoneUser();
                thumbnailStu = response.body().get(0).getThumbnailStudent();
                //cắt chuỗi để lấy tên ảnh trong thư mục chứa sau đó ghép với uri mặc định để load ảnh lên imageview||cách tạm thời
//                strURLthumbnail = thumbnailStu.substring(thumbnailStu.indexOf("cropped"), thumbnailStu.length());
//                Uri thumbnailURI = Uri.parse("file:///data/data/com.example.wri/cache/" + strURLthumbnail);

//                imgv_avatarStudent.setImageURI(thumbnailURI);
                Picasso.with(getContext()).load(thumbnailStu).into(imgv_avatarStudent);
                tv_nameStudent.setText("Họ và tên: " + nameStu);
                if(codeStu == null){
                    tv_codeStudent.setText("Mã học viên: Chưa có");
                }else {
                    tv_codeStudent.setText("Mã học viên: " + codeStu);
                }
                tv_birthdayStudent.setText("Ngày sinh: " + birthdayStu);
                tv_majorStudent.setText("Chuyên ngành: " + majorStu);
                tv_emailStudent.setText("Email: " + emailStu);
                tv_phoneStudent.setText("Số điện thoại: " + phoneStu);
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {
                Toast.makeText(getContext(), "Lấy thông tin học sinh thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetJoinedClasses(String idStu) {
        DataService dataService= APIService.getService();
        Call<List<Classs>> callback=dataService.getJoinedClasses(idStu);
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                listClass=(ArrayList<Classs>) response.body();
                adapter=new Adapter_Joined_Classes(getContext(),listClass);
                rv_joinedClasses.setLayoutManager(new LinearLayoutManager(getContext()));
                rv_joinedClasses.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });

    }
}