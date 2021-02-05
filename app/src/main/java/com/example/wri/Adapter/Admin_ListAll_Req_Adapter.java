package com.example.wri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Model.Requirement;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Admin_ListAll_Req_Adapter extends RecyclerView.Adapter<Admin_ListAll_Req_Adapter.ViewHolder>{
    Context context;
    public ArrayList<Requirement> requirementsArrayList;

    public Admin_ListAll_Req_Adapter(Context context, ArrayList<Requirement> requirementsArrayList) {
        this.context = context;
        this.requirementsArrayList = requirementsArrayList;
    }

    @NonNull
    @Override
    public Admin_ListAll_Req_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_admin_item_all_requirement,parent,false);
        return new Admin_ListAll_Req_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_ListAll_Req_Adapter.ViewHolder holder, final int position) {
        final Requirement requirement = requirementsArrayList.get(position);
        holder.txtnameGroupReq.setText(requirement.getNameGroupReq());
        holder.name.setChecked(requirement.isSelected());
        holder.name.setTag(requirement);
        holder.txtnameReq.setText(requirement.getNameReq());
        holder.name.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v ;
                Requirement checkboxReq = (Requirement) cb.getTag();
                checkboxReq.setSelected(cb.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return requirementsArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtnameReq,txtnameGroupReq;
        CheckBox name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.checkBox_allreq_admin);
            name.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            txtnameGroupReq = itemView.findViewById(R.id.adt_namegroupreq_admin);
            txtnameReq = itemView.findViewById(R.id.adt_namereq_admin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // When clicked, show a toast with the TextView text
                    Requirement checkboxreq = requirementsArrayList.get(getPosition());
                }
            });
        }
    }
}


