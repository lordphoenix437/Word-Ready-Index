package com.example.wri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Model.Students;
import com.example.wri.Model.Teacher;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Admin_addTeacherClass_Adapter extends RecyclerView.Adapter<Admin_addTeacherClass_Adapter.ViewHolder>{
    Context context;
    public ArrayList<Teacher> teacherArrayList;

    public Admin_addTeacherClass_Adapter(Context context, ArrayList<Teacher> teacherArrayList) {
        this.context = context;
        this.teacherArrayList = teacherArrayList;
    }

    @NonNull
    @Override
    public Admin_addTeacherClass_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_admin_item_checkbox_teacher,parent,false);
        return new Admin_addTeacherClass_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_addTeacherClass_Adapter.ViewHolder holder, final int position) {
        final Teacher teacher = teacherArrayList.get(position);
        holder.txtnameTeacher.setText(teacher.getNameTeacher());
        holder.name.setChecked(teacher.isSelected());
        holder.name.setTag(teacher);
        holder.txtcodeTeacher.setText(teacher.getCodeTeacher());
        holder.name.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v ;
                Teacher checkboxTeacher = (Teacher) cb.getTag();
                checkboxTeacher.setSelected(cb.isChecked());
            }
        });
        Picasso.with(context).load(teacher.getThumbnailTeacher()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return teacherArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtcodeTeacher,txtnameTeacher;
        CheckBox name;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.checkBoxteacher);
            name.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            img = itemView.findViewById(R.id.adt_img_addteacherclass_admin);
            txtnameTeacher = itemView.findViewById(R.id.adt_nameteacher_addteacherclass_admin);
            txtcodeTeacher = itemView.findViewById(R.id.adt_codeteacher_addteacherclass_admin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // When clicked, show a toast with the TextView text
                    Teacher checkboxStudent = teacherArrayList.get(getPosition());
                }
            });
        }
    }
}

