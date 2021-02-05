package com.example.wri.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Activity.Admin.Student.Detail_Student_Admin;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Admin_StudentinClassDetail_adapter extends RecyclerView.Adapter<Admin_StudentinClassDetail_adapter.ViewHolder>{
    Context context;
    public ArrayList<Students> studentsArrayList;

    public Admin_StudentinClassDetail_adapter(Context context, ArrayList<Students> studentsArrayList) {
        this.context = context;
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public Admin_StudentinClassDetail_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item_studentinclass_detailclass,parent,false);
        return new Admin_StudentinClassDetail_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_StudentinClassDetail_adapter.ViewHolder holder, int position) {
        Students students = studentsArrayList.get(position);
        Picasso.with(context).load(students.getThumbnailStudent()).into(holder.thumbnailStu);
    }

    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbnailStu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailStu = itemView.findViewById(R.id.img_studentinclass_detailclass);
        }
    }
}


