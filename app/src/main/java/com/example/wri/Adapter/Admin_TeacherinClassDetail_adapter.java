package com.example.wri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Model.Students;
import com.example.wri.Model.Teacher;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Admin_TeacherinClassDetail_adapter extends RecyclerView.Adapter<Admin_TeacherinClassDetail_adapter.ViewHolder>{
    Context context;
    ArrayList<Teacher> teachersArrayList;

    public Admin_TeacherinClassDetail_adapter(Context context, ArrayList<Teacher> teachersArrayList) {
        this.context = context;
        this.teachersArrayList = teachersArrayList;
    }

    @NonNull
    @Override
    public Admin_TeacherinClassDetail_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item_teacherinclass_detailclass,parent,false);
        return new Admin_TeacherinClassDetail_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_TeacherinClassDetail_adapter.ViewHolder holder, int position) {
        Teacher teacher = teachersArrayList.get(position);
        Picasso.with(context).load(teacher.getThumbnailTeacher()).into(holder.thumbnailTeacher);
    }

    @Override
    public int getItemCount() {
        return teachersArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbnailTeacher;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailTeacher = itemView.findViewById(R.id.img_teacherinclass_detailclass);
        }
    }
}