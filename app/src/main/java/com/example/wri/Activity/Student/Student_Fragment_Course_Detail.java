package com.example.wri.Activity.Student;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.wri.Model.Classs;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Fragment_Course_Detail extends Fragment {
    ImageView imgv_back, imgv_thumbnailClass;
    TextView tv_nameClass, tv_codeClass, tv_currentStu, tv_maxStu, tv_descriptionClass;
    Button btn_register;

    private String urlREGISTER = "https://uni2work.000webhostapp.com/WRI/student/register_course.php";
    private String urlCANCEL = "https://uni2work.000webhostapp.com/WRI/student/cancel_registration.php";
    String idStu, idClass;
    int status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_student___course__detail, container, false);
        imgv_back = view.findViewById(R.id.imgv_back);
        imgv_thumbnailClass = view.findViewById(R.id.imgv_thumbnailClass);
        tv_nameClass = view.findViewById(R.id.tv_nameClass);
        tv_codeClass = view.findViewById(R.id.tv_codeClass);
        tv_currentStu = view.findViewById(R.id.tv_currentStu);
        tv_maxStu = view.findViewById(R.id.tv_maxStu);
        tv_descriptionClass = view.findViewById(R.id.tv_descriptionClass);
        btn_register = view.findViewById(R.id.btn_register);

        idClass = getArguments().getString("idClass");
        idStu = getArguments().getString("idStu");
        GetDataCourseDetail(idClass);
        checkRegisteredCourse();

        imgv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRegisteredCourse();
                switch (status) {
                    case 0:
                        Toast.makeText(getContext(), "Bạn cần liên hệ với bộ phận công tác sinh viên để hủy khóa học", Toast.LENGTH_SHORT).show();
                        checkRegisteredCourse();
                        break;
                    case 1:
                        cancelRegistration();
                        checkRegisteredCourse();
                        break;
                    case 2:
                        register();
                        checkRegisteredCourse();
                        break;
                }
            }
        });
        return view;
    }

    private void GetDataCourseDetail(String idClass) {
        DataService dataService = APIService.getService();
        Call<List<Classs>> callback = dataService.getCourseDetail(idClass);
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                Picasso.with(getContext()).load(response.body().get(0).getThumbnailClass()).into(imgv_thumbnailClass);
                tv_nameClass.setText(response.body().get(0).getNameClass());
                tv_codeClass.setText(response.body().get(0).getCodeClass());
                tv_currentStu.setText(response.body().get(0).getCurrentStudentClass());
                tv_maxStu.setText(response.body().get(0).getMaxStudentClass());
                tv_descriptionClass.setText(response.body().get(0).getDecriptionClass());
            }

            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });
    }

    private void checkRegisteredCourse() {
        DataService dataService = APIService.getService();
        Call<Classs> callback = dataService.checkRegisteredCourse(idStu, idClass);
        callback.enqueue(new Callback<Classs>() {
            @Override
            public void onResponse(Call<Classs> call, Response<Classs> response) {
                status = response.body().getStatus();
                switch (status) {
                    case 0:
                        btn_register.setText("Đang học");
                        btn_register.setBackgroundColor(Color.parseColor("#d7d7d7"));
                        btn_register.setTextColor(Color.parseColor("#696969"));
                        break;
                    case 1:
                        btn_register.setText("Hủy đăng ký");
                        btn_register.setBackgroundColor(Color.parseColor("#d7d7d7"));
                        btn_register.setTextColor(Color.parseColor("#696969"));
                        break;
                    case 2:
                        btn_register.setText("Đăng ký");
                        btn_register.setBackgroundColor(Color.parseColor("#0d9595"));
                        btn_register.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                }
                Log.d("avc",status+"");
            }

            @Override
            public void onFailure(Call<Classs> call, Throwable t) {

            }
        });
    }

    private void register() {
        AndroidNetworking.upload(urlREGISTER)
                .addMultipartParameter("idStu", idStu)
                .addMultipartParameter("idClass", idClass)
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        float progress = (float) bytesUploaded / totalBytes * 100;
                    }
                })
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("nnn", response);
                            JSONObject jsonObject = new JSONObject(response);
                            int status = jsonObject.getInt("status");
                            String message = jsonObject.getString("message");
                            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(getContext(), "Đăng ký lớp thất bại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }

    private void cancelRegistration() {
        AndroidNetworking.upload(urlCANCEL)
                .addMultipartParameter("idStu", idStu)
                .addMultipartParameter("idClass", idClass)
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        float progress = (float) bytesUploaded / totalBytes * 100;
                    }
                })
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("nnn", response);
                            JSONObject jsonObject = new JSONObject(response);
                            int status = jsonObject.getInt("status");
                            String message = jsonObject.getString("message");
                            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(getContext(), "Hủy đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }
}