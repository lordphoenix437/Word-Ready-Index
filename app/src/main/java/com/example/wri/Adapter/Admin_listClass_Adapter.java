package com.example.wri.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Activity.Admin.Class.Admin_Detail_Class;
import com.example.wri.Activity.Admin.Class.Admin_Edit_Class;
import com.example.wri.Model.Classs;
import com.example.wri.Model.MajorItem;
import com.example.wri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Admin_listClass_Adapter extends RecyclerView.Adapter<Admin_listClass_Adapter.ViewHolder> implements Filterable {
    Context context;
    ArrayList<Classs> classsArrayList;

    public Admin_listClass_Adapter(Context context, ArrayList<Classs> classsArrayList) {
        this.context = context;
        this.classsArrayList = classsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_admin_item_class,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Classs classs = classsArrayList.get(position);
       // holder.txtnamecategory.setText(category.getCategorytitle());
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
            txtnameclass = itemView.findViewById(R.id.adt_edt_namestudent_listallstudent);
            txtcurrentStudentclass = itemView.findViewById(R.id.adt_edt_current_listclass);
            txtmaxStudentClass = itemView.findViewById(R.id.adt_edt_maxclass_listclass);
            thumbnailClass = itemView.findViewById(R.id.adt_img_teacher_allteacher);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Admin_Detail_Class.class);
                    intent.putExtra("class_admin",classsArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
    @Override
    public Filter getFilter() {
        return classFilter;
    }
    private Filter classFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Classs> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(classsArrayList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Classs item : classsArrayList) {
                    if (item.getCodeClass().toLowerCase().contains(filterPattern)) {
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
            classsArrayList.clear();
            classsArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

