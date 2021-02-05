package com.example.wri.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Activity.Student.Student_Fragment_Course_Detail;
import com.example.wri.Model.Classs;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Student_main_getCourse_Adapter extends RecyclerView.Adapter<Student_main_getCourse_Adapter.ViewHolder> {
    Context context;
    ArrayList<Classs> classsArrayList;
    String idStu;

    public Student_main_getCourse_Adapter(Context context, ArrayList<Classs> classsArrayList, String idStu) {
        this.context = context;
        this.classsArrayList = classsArrayList;
        this.idStu=idStu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item_main_getcourse_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classs classs = classsArrayList.get(position);
        // holder.txtnamecategory.setText(category.getCategorytitle());
        holder.txtnameclass.setText(classs.getNameClass());
        Picasso.with(context).load(classs.getThumbnailClass()).into(holder.thumbnailClass);
    }

    @Override
    public int getItemCount() {
        return classsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnameclass;
        ImageView thumbnailClass;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtnameclass = itemView.findViewById(R.id.tv_courseIT_student);
            thumbnailClass = itemView.findViewById(R.id.img_courseIT_student);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Classs classs = classsArrayList.get(getPosition());

//                    Gson gson = new Gson();
//                    String json = gson.toJson(classs);
//                    Log.d("avc",json);

                    Bundle bundle = new Bundle();
                    bundle.putString("idClass", classs.getId());
                    bundle.putString("idStu",idStu);
                    Fragment fragment = new Student_Fragment_Course_Detail();
                    fragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = ((FragmentActivity) itemView.getContext()).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.addToBackStack(new Student_Fragment_Course_Detail().toString());
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragmentTransaction.commit();
                }
            });
        }
    }
}

