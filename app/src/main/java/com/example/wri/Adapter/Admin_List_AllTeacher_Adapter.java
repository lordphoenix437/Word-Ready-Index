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
import com.example.wri.Activity.Admin.Teacher.Detail_Teacher_Admin;
import com.example.wri.Model.Students;
import com.example.wri.Model.Teacher;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Admin_List_AllTeacher_Adapter extends RecyclerView.Adapter<Admin_List_AllTeacher_Adapter.ViewHolder>{
    Context context;
    ArrayList<Teacher> teacherArrayList;

    public Admin_List_AllTeacher_Adapter(Context context, ArrayList<Teacher> teacherArrayList) {
        this.context = context;
        this.teacherArrayList = teacherArrayList;
    }

    @NonNull
    @Override
    public Admin_List_AllTeacher_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item_allteacher,parent,false);
        return new Admin_List_AllTeacher_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_List_AllTeacher_Adapter.ViewHolder holder, int position) {
        Teacher teacher = teacherArrayList.get(position);
        holder.txtnameTeacher.setText(teacher.getNameTeacher());
        holder.txtcodeTeacher.setText(teacher.getCodeTeacher());
        Picasso.with(context).load(teacher.getThumbnailTeacher()).into(holder.thumbnailTeacher);
    }

    @Override
    public int getItemCount() {
        return teacherArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtnameTeacher,txtcodeTeacher;
        ImageView thumbnailTeacher;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnameTeacher = itemView.findViewById(R.id.adt_tv_nameteacher_allteacher);
            txtcodeTeacher = itemView.findViewById(R.id.adt_codeteacher_allteacher);
            thumbnailTeacher = itemView.findViewById(R.id.adt_img_teacher_allteacher);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Detail_Teacher_Admin.class);
                    intent.putExtra("teacher_admin",teacherArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}


