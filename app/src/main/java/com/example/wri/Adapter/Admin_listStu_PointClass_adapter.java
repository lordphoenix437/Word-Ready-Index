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

import com.example.wri.Activity.Admin.Class.Admin_Detail_Class;
import com.example.wri.Activity.Admin.Class.Admin_listPoint_ofStu_inClass_Admin;
import com.example.wri.Activity.Admin.Class.Admin_listStuPoint_inClass;
import com.example.wri.Activity.Admin.Student.Detail_Student_Admin;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Admin_listStu_PointClass_adapter extends RecyclerView.Adapter<Admin_listStu_PointClass_adapter.ViewHolder> {
    Context context;
    ArrayList<Students> studentsArrayList;

    public Admin_listStu_PointClass_adapter(Context context, ArrayList<Students> studentsArrayList) {
        this.context = context;
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public Admin_listStu_PointClass_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item_liststupoint_class_admin, parent, false);
        return new Admin_listStu_PointClass_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_listStu_PointClass_adapter.ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnameStu, txtcodeStu;
        ImageView thumbnailStu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnameStu = itemView.findViewById(R.id.adt_name_stu_pointclass_admin);
            txtcodeStu = itemView.findViewById(R.id.adt_code_stu_pointclass_admin);
            thumbnailStu = itemView.findViewById(R.id.adt_img_stu_pointclass_admin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Admin_listPoint_ofStu_inClass_Admin.class);
                    intent.putExtra("stu_point", studentsArrayList.get(getPosition()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}

