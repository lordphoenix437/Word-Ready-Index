package com.example.wri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Model.Students;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchStudent_Of_Major_Company_Adapter extends RecyclerView.Adapter<SearchStudent_Of_Major_Company_Adapter.ViewHolder> {
    Context context;
    private List<Students> studentsMajorList;
    private List<Students> studentsMahjorListFull;
    public SearchStudent_Of_Major_Company_Adapter(Context context, List<Students> studentsMajorList){
        this.context = context;
        this.studentsMajorList = studentsMajorList;
//        studentsMahjorListFull = new ArrayList<>(studentsMahjorListFull);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_company_item_student,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Students students = studentsMajorList.get(position);
        holder.txtStudent_special_Company.setText("Chuyên ngành: "+students.getMajor());
        holder.txtNamestudent_special_com.setText("Tên ứng viên: "+students.getNameStudent());
        holder.txt_mhv_special_com.setText(students.getCodeStudent());
        Picasso.with(context).load(students.getThumbnailStudent()).into(holder.imv_Avatar_Studen_special_Com);

    }

    @Override
    public int getItemCount() {
        return studentsMajorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStudent_special_Company, txtNamestudent_special_com, txt_mhv_special_com;
        ImageView imv_Avatar_Studen_special_Com, imv_favoritestudent_special_com;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStudent_special_Company = itemView.findViewById(R.id.tv_student_special_com);
            txtNamestudent_special_com = itemView.findViewById(R.id.tv_namestudent_special_com);
            txt_mhv_special_com = itemView.findViewById(R.id.tv_mhv_special_com);
            imv_favoritestudent_special_com = itemView.findViewById(R.id.img_favoritestudent_special_com);
            imv_Avatar_Studen_special_Com = itemView.findViewById(R.id.img_Avatar_Studen_special_Com);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
