package com.example.wri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Model.Requirement;
import com.example.wri.R;

import java.util.ArrayList;

public class Admin_ReqinClass_Adapter extends RecyclerView.Adapter<Admin_ReqinClass_Adapter.ViewHolder>{
    Context context;
    public ArrayList<Requirement> requirementsArrayList;

    public Admin_ReqinClass_Adapter(Context context, ArrayList<Requirement> requirementsArrayList) {
        this.context = context;
        this.requirementsArrayList = requirementsArrayList;
    }

    @NonNull
    @Override
    public Admin_ReqinClass_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_item_reqinclass_admin,parent,false);
        return new Admin_ReqinClass_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_ReqinClass_Adapter.ViewHolder holder, final int position) {
        final Requirement requirement = requirementsArrayList.get(position);
        holder.txtnameReq.setText(requirement.getNameReq());
        holder.txtGroupReq.setText(requirement.getNameGroupReq());
    }

    @Override
    public int getItemCount() {
        return requirementsArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtnameReq,txtGroupReq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnameReq = itemView.findViewById(R.id.adt_nameReq_inclass_admin);
            txtGroupReq = itemView.findViewById(R.id.adt_nameGroupReq_inclass_admin);
        }
    }
}
