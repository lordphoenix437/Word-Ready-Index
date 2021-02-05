package com.example.wri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_List_Recruitment extends RecyclerView.Adapter<Adapter_List_Recruitment.ViewHolder>{
    Context context;
    ArrayList listRecruitment;

    public Adapter_List_Recruitment(Context context, ArrayList listRecruitment) {
        this.context = context;
        this.listRecruitment = listRecruitment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recruitment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_courseRequirement.setText(listRecruitment.get(position)+"");
    }

    @Override
    public int getItemCount() {
        return listRecruitment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgv_thumbnailRecruitment;
        TextView tv_courseRequirement,tv_companyName,tv_timeExpiration;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgv_thumbnailRecruitment=itemView.findViewById(R.id.imgv_thumbnailRecruitment);
            tv_courseRequirement = itemView.findViewById(R.id.tv_courseRequirement);
            tv_companyName = itemView.findViewById(R.id.tv_companyName);
            tv_timeExpiration = itemView.findViewById(R.id.tv_timeExpiration);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
