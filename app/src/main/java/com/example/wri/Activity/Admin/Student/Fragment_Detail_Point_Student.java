package com.example.wri.Activity.Admin.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Adapter.Admin_listPoint_ofStu_inClass_Adapter;
import com.example.wri.Model.Points;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Detail_Point_Student extends Fragment {
    RecyclerView rcv_listallpointstu_admin;
    ArrayList<Points> pointsArrayList;
    Admin_listPoint_ofStu_inClass_Adapter listPoint_adapter;
    Students students;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Dataintent();
        GetAllPoint(students.getId());
        return inflater.inflate(R.layout.fragment_admin_point_detailstu_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rcv_listallpointstu_admin = view.findViewById(R.id.rcv_listallpointstu_admin);
        super.onViewCreated(view, savedInstanceState);
    }
    private  void GetAllPoint(String idStu){
        DataService dataService = APIService.getService();
        Call<List<Points>> callpack = dataService.getallPointStudent(idStu);
        callpack.enqueue(new Callback<List<Points>>() {
            @Override
            public void onResponse(Call<List<Points>> call, Response<List<Points>> response) {
                pointsArrayList = new ArrayList<Points>();
                pointsArrayList = (ArrayList<Points>)response.body();
                listPoint_adapter = new Admin_listPoint_ofStu_inClass_Adapter(getActivity(),pointsArrayList);
                rcv_listallpointstu_admin.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
                rcv_listallpointstu_admin.setAdapter(listPoint_adapter);
            }

            @Override
            public void onFailure(Call<List<Points>> call, Throwable t) {

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
}
