package com.example.wri.Activity.Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Adapter.Adapter_List_Recruitment;
import com.example.wri.R;

import java.util.ArrayList;

public class Student_Fragment_Recruitment extends Fragment {
    RecyclerView rcv_recruitment;
    ArrayList listRecruitment=new ArrayList();
    Adapter_List_Recruitment adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_student___recruitment, container, false);
        rcv_recruitment=view.findViewById(R.id.rcv_recruitment);

        listRecruitment.add("Lập trình di động");
        listRecruitment.add("Thiết kế đồ họa");
        listRecruitment.add("Ngôn ngữ Anh");
        listRecruitment.add("Ngôn ngữ Nhật");
        adapter=new Adapter_List_Recruitment(getContext(),listRecruitment);
        rcv_recruitment.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_recruitment.setAdapter(adapter);

        return view;
    }
}