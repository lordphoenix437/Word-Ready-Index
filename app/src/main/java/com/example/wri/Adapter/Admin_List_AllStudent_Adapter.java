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

public class Admin_List_AllStudent_Adapter extends RecyclerView.Adapter<Admin_List_AllStudent_Adapter.ViewHolder>{
    Context context;
    ArrayList<Students> studentsArrayList;

    public Admin_List_AllStudent_Adapter(Context context, ArrayList<Students> studentsArrayList) {
        this.context = context;
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public Admin_List_AllStudent_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item_allstudent,parent,false);
        return new Admin_List_AllStudent_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_List_AllStudent_Adapter.ViewHolder holder, int position) {
        Students students = studentsArrayList.get(position);
        // holder.txtnamecategory.setText(category.getCategorytitle());
        holder.txtnameStu.setText(students.getNameStudent());
        holder.txtcodeStu.setText(students.getCodeStudent());
        Picasso.with(context).load(students.getThumbnailStudent()).into(holder.thumbnailStu);
    }

    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtnameStu,txtcodeStu;
        ImageView thumbnailStu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnameStu = itemView.findViewById(R.id.adt_tv_nameteacher_allteacher);
            txtcodeStu = itemView.findViewById(R.id.adt_codeteacher_allteacher);
            thumbnailStu = itemView.findViewById(R.id.adt_img_teacher_allteacher);
            itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent intent = new Intent(context, Detail_Student_Admin.class);
                    intent.putExtra("student_admin",studentsArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

