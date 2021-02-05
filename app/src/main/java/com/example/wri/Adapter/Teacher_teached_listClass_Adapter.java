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
import com.example.wri.Activity.Teacher.Menu_class_teacher;
import com.example.wri.Model.Classs;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Teacher_teached_listClass_Adapter extends RecyclerView.Adapter<Teacher_teached_listClass_Adapter.ViewHolder>{
    Context context;
    ArrayList<Classs> classsArrayList;

    public Teacher_teached_listClass_Adapter(Context context, ArrayList<Classs> classsArrayList) {
        this.context = context;
        this.classsArrayList = classsArrayList;
    }

    @NonNull
    @Override
    public Teacher_teached_listClass_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item_class_teached_teacher,parent,false);
        return new Teacher_teached_listClass_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Teacher_teached_listClass_Adapter.ViewHolder holder, int position) {
        Classs classs = classsArrayList.get(position);
        holder.txtnameclass.setText(classs.getNameClass());
        holder.txtcurrentStudentclass.setText(classs.getCurrentStudentClass());
        holder.txtmaxStudentClass.setText(classs.getMaxStudentClass());
        Picasso.with(context).load(classs.getThumbnailClass()).into(holder.thumbnailClass);
    }

    @Override
    public int getItemCount() {
        return classsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtnameclass,txtcurrentStudentclass,txtmaxStudentClass;
        ImageView thumbnailClass;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnameclass = itemView.findViewById(R.id.adt_edt_nameclass_listclass_teacher);
            txtcurrentStudentclass = itemView.findViewById(R.id.adt_edt_current_listclass_teacher);
            txtmaxStudentClass = itemView.findViewById(R.id.adt_edt_maxclass_listclass_teacher);
            thumbnailClass = itemView.findViewById(R.id.adt_img_class_listclass_teacher);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Menu_class_teacher.class);
                    intent.putExtra("teached_class_teacher",classsArrayList.get(getPosition()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}