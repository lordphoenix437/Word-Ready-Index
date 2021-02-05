package com.example.wri.Activity.Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
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


public class Student_Fragment_Course extends Fragment {
    RecyclerView rcv_coursesIT, rcv_courseLanguage,rcv_courseDesign;
    ArrayList<Classs> classsArrayList;
    Student_main_getCourse_Adapter course_adapter;
    TextView tv_xemthemIT, tv_xemthemLanguage,tv_xemthemDesign;
    String idStu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student___course, container, false);

        idStu=getArguments().getString("idStu");

        rcv_coursesIT = view.findViewById(R.id.rcv_coursesIT);
        rcv_courseLanguage = view.findViewById(R.id.rcv_coursesLanguage);
        rcv_courseDesign = view.findViewById(R.id.rcv_coursesDesign);

        tv_xemthemIT = view.findViewById(R.id.tv_xemthemIT);
        tv_xemthemLanguage = view.findViewById(R.id.tv_xemthemLanguage);
        tv_xemthemDesign = view.findViewById(R.id.tv_xemthemDesign);

        tv_xemthemIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Student_Fragment_Course_ShowAll(),"IT");
            }
        });
        tv_xemthemLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Student_Fragment_Course_ShowAll(),"Ngôn ngữ");
            }
        });
        tv_xemthemDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Student_Fragment_Course_ShowAll(),"Thiết kế đồ họa");
            }
        });

        GetDataCourseIT();
        GetDataCourseLanguage();
        GetDataCourseDesign();
        return view;
    }

    public void addFragment(Fragment fragment,String courseName) {
        Bundle bundle = new Bundle();
        bundle.putString("courseName", courseName);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    private void GetDataCourseIT() {
        DataService dataService = APIService.getService();
        Call<List<Classs>> callback = dataService.getCourseIT_student();
        callback.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                classsArrayList = (ArrayList<Classs>) response.body();
                course_adapter = new Student_main_getCourse_Adapter(getContext(), classsArrayList,idStu);
                rcv_coursesIT.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                rcv_coursesIT.setAdapter(course_adapter);
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
                rcv_courseLanguage.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                rcv_courseLanguage.setAdapter(course_adapter);
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
                rcv_courseDesign.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                rcv_courseDesign.setAdapter(course_adapter);
            }

            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {

            }
        });
    }
}