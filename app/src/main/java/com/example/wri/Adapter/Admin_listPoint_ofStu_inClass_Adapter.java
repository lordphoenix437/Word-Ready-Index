package com.example.wri.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Activity.Admin.Class.Admin_Detail_Class;
import com.example.wri.Activity.Admin.Class.Admin_editPointStu_inClass;
import com.example.wri.Model.Points;
import com.example.wri.Model.Requirement;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.ArrayList;

public class Admin_listPoint_ofStu_inClass_Adapter extends RecyclerView.Adapter<Admin_listPoint_ofStu_inClass_Adapter.ViewHolder>{
    Context context;
    public ArrayList<Points> pointsArrayList;

    public Admin_listPoint_ofStu_inClass_Adapter(Context context, ArrayList<Points> pointsArrayList) {
        this.context = context;
        this.pointsArrayList = pointsArrayList;
    }

    @NonNull
    @Override
    public Admin_listPoint_ofStu_inClass_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_admin_item_pointofstu_inclass,parent,false);
        return new Admin_listPoint_ofStu_inClass_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_listPoint_ofStu_inClass_Adapter.ViewHolder holder, final int position) {
        Points points = pointsArrayList.get(position);
        holder.txtnameGroupReq.setText(points.getNameGroupReq());
        holder.txtnameReq.setText(points.getNameReq());
        holder.txtpoint.setText(""+points.getPoint());

    }

    @Override
    public int getItemCount() {
        return pointsArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtnameReq,txtnameGroupReq,txtpoint;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnameGroupReq = itemView.findViewById(R.id.adt_edt_namegroupreqofstu_inclass_admin);
            txtnameReq = itemView.findViewById(R.id.adt_edt_namereqofstu_inclass_admin);
            txtpoint = itemView.findViewById(R.id.adt_edt_pointreqofstu_inclass_admin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Admin_editPointStu_inClass.class);
                    intent.putExtra("point_stu_inclass",pointsArrayList.get(getPosition()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

}


