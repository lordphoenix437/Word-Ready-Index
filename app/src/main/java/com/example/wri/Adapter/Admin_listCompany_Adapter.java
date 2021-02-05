package com.example.wri.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Activity.Admin.Company.Admin_Company_List;
import com.example.wri.Model.Companys;
import com.example.wri.R;

import java.util.ArrayList;

public class Admin_listCompany_Adapter extends RecyclerView.Adapter<Admin_listCompany_Adapter.ViewHolder>{
    Admin_Company_List admin_company_list;
    ArrayList<Companys> companyArrayList;

    public Admin_listCompany_Adapter(Admin_Company_List admin_company_list, ArrayList<Companys> companyArrayList) {
        this.admin_company_list = admin_company_list;
        this.companyArrayList = companyArrayList;
    }

    @NonNull
    @Override
    public Admin_listCompany_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_admin_company_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Admin_listCompany_Adapter.ViewHolder holder, int position) {
       // holder.nameCompany.setText(companyArrayList.get(position).getEmailCompany());
    }

    @Override
    public int getItemCount() {
        return companyArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameCompany;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nameCompany = itemView.findViewById(R.id.adt_admin_listcompany_namecompany);
        }
    }
}
