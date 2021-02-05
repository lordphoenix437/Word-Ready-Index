package com.example.wri.Activity.Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Adapter.Student_main_getCourse_Adapter;
import com.example.wri.Model.Classs;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Fragment_Course_ShowAll extends Fragment {
    ImageView imgv_back;
    TextView tv_courseName;
    RecyclerView rcv_coursesShowAll;
    ArrayList<Classs> classsArrayList;
    Student_main_getCourse_Adapter course_adapter;
    String courseName,idStu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student___course__show_all, container, false);
        imgv_back=view.findViewById(R.id.imgv_back);
        tv_courseName=view.findViewById(R.id.tv_courseName);
        rcv_coursesShowAll=view.findViewById(R.id.rcv_coursesShowAll);

        courseName = getArguments().getString("courseName");
        tv_courseName.setText(courseName);

        switch(courseName) {
            case "IT":
                GetDataCourseIT();
                break;
            case "Ngôn ngữ":
                GetDataCourseLanguage();
                break;
            case "Thiết kế đồ họa":
                GetDataCourseDesign();
                break;
            default:
                break;
        }

        imgv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }

    private void GetDataCourseIT() {
        DataService dataService = APIService.getService();
        Call<List<Classs>> callback = dataService.getCourseIT_student();
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                classsArrayList = (ArrayList<Classs>) response.body();
                course_adapter = new Student_main_getCourse_Adapter(getContext(), classsArrayList,idStu);
                rcv_coursesShowAll.setLayoutManager(new GridLayoutManager(getContext(), 3));
                rcv_coursesShowAll.setAdapter(course_adapter);
            }

            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });
    }

    private void GetDataCourseLanguage() {
        DataService dataService = APIService.getService();
        Call<List<Classs>> callback = dataService.getCourseLanguage_student();
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                classsArrayList = (ArrayList<Classs>) response.body();
                course_adapter = new Student_main_getCourse_Adapter(getContext(), classsArrayList,idStu);
                rcv_coursesShowAll.setLayoutManager(new GridLayoutManager(getContext(), 3));
                rcv_coursesShowAll.setAdapter(course_adapter);
            }

            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });
    }

    private void GetDataCourseDesign() {
        DataService dataService = APIService.getService();
        Call<List<Classs>> callback = dataService.getCourseDesign_student();
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                classsArrayList = (ArrayList<Classs>) response.body();
                course_adapter = new Student_main_getCourse_Adapter(getContext(), classsArrayList,idStu);
                rcv_coursesShowAll.setLayoutManager(new GridLayoutManager(getContext(), 3));
                rcv_coursesShowAll.setAdapter(course_adapter);
            }

            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });
    }
}