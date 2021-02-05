package com.example.wri.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Activity.Admin.Student.Detail_Student_Admin;
import com.example.wri.Activity.Company.Company_Specialize_List_Student;
import com.example.wri.Model.MajorItem;
import com.example.wri.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class searchMajor_Company_Adapter extends RecyclerView.Adapter<searchMajor_Company_Adapter.ViewHolder> implements Filterable{
    private List<MajorItem> majorList;
    private List<MajorItem> majorListFull;
    private Context context;

    public searchMajor_Company_Adapter(Context context, List<MajorItem> majorList){
        this.context = context;
        this.majorList = majorList;
        this.majorListFull = new ArrayList<>(majorList);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialize_item_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MajorItem majorItem = majorList.get(position);
        holder.txtmajor.setText(majorItem.getNamegmajor());

    }

    @Override
    public int getItemCount() {
        return majorList.size();
    }

    @Override
    public Filter getFilter() {
        return majorFilter;
    }
    private Filter majorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MajorItem> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(majorListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MajorItem item : majorListFull){
                    if (item.getNamegmajor().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            majorList.clear();
            majorList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtmajor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmajor = itemView.findViewById(R.id.special_item_tv);

            txtmajor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, Company_Specialize_List_Student.class);
                  // truyền thông số id theo vị trí của nó trên list đddax tạo
                    i.putExtra("gmajor_id",majorList.get(getPosition()));
                    context.startActivity(i);
                }
            });
        }
    }

}

