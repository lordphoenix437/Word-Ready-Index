package com.example.wri.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wri.Activity.Admin.Class.Admin_Edit_Class;
import com.example.wri.Model.Classs;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_Joined_Classes extends RecyclerView.Adapter<Adapter_Joined_Classes.ViewHolder> {
    Context context;
    ArrayList<Classs> listClass;

    public Adapter_Joined_Classes(Context context, ArrayList<Classs> listClass) {
        this.context = context;
        this.listClass = listClass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_joined_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classs classs = listClass.get(position);
        // holder.txtnamecategory.setText(category.getCategorytitle());
//        Uri uri_thumbnailJoinedClass=Uri.parse(classs.getThumbnailClass());
        holder.tv_nameJoinedClass.setText(classs.getNameClass());
        holder.tv_maxStudentJoinedClass.setText(classs.getCurrentStudentClass() + "/" + classs.getMaxStudentClass());
        Picasso.with(context).load(classs.getThumbnailClass()).into(holder.imgv_thumbnailJoinedClass);
    }

    @Override
    public int getItemCount() {
        return listClass.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgv_thumbnailJoinedClass;
        TextView tv_nameJoinedClass, tv_maxStudentJoinedClass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nameJoinedClass = itemView.findViewById(R.id.tv_nameJoinedClass);
            tv_maxStudentJoinedClass = itemView.findViewById(R.id.tv_maxStudentJoinedClass);
            imgv_thumbnailJoinedClass = itemView.findViewById(R.id.imgv_thumbnailJoinedClass);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Admin_Edit_Class.class);
                    intent.putExtra("class", listClass.get(getPosition()).getNameClass());
                    Toast.makeText(context, "" + listClass.get(getPosition()).getNameClass(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }
}
