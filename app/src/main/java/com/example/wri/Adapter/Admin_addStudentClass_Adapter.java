package com.example.wri.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Activity.Admin.Student.Detail_Student_Admin;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Admin_addStudentClass_Adapter extends RecyclerView.Adapter<Admin_addStudentClass_Adapter.ViewHolder>{
    Context context;
    public ArrayList<Students> studentsArrayList;

    public Admin_addStudentClass_Adapter(Context context, ArrayList<Students> studentsArrayList) {
        this.context = context;
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public Admin_addStudentClass_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_admin_item_checkbox_student,parent,false);
        return new Admin_addStudentClass_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Students students = studentsArrayList.get(position);
        holder.txtnameStu.setText(students.getNameStudent());
        holder.name.setChecked(students.isSelected());
        holder.name.setTag(students);
        holder.txtcodeStu.setText(students.getCodeStudent());
        holder.name.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v ;
                Students checkboxStudent = (Students) cb.getTag();
                checkboxStudent.setSelected(cb.isChecked());
            }
        });
        Picasso.with(context).load(students.getThumbnailStudent()).into(holder.imgStu);
    }

    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtcodeStu,txtnameStu;
        CheckBox name;
        ImageView imgStu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.checkBox1);
            name.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            imgStu = itemView.findViewById(R.id.adt_img_addstudentclass_admin);
            txtnameStu = itemView.findViewById(R.id.adt_namestudent_addstudentclass_admin);
            txtcodeStu = itemView.findViewById(R.id.adt_codestudent_addstudentclass_admin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // When clicked, show a toast with the TextView text
                    Students checkboxStudent = studentsArrayList.get(getPosition());
                }
            });
        }
    }
}

