package com.example.wri.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wri.Model.Company_Mail;
import com.example.wri.R;

import java.util.List;

public class Company_Mail_Custom_Adapter extends RecyclerView.Adapter<Company_Mail_Custom_Adapter.MailVH> {
    private static final String TAG = "MailAdapter";
    List<Company_Mail> mailList;
    public Company_Mail_Custom_Adapter(List<Company_Mail> mailList) {
        this.mailList = mailList;
    }
    @NonNull
    @Override
    public MailVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mail_company, parent, false);
        return new MailVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MailVH holder, int position) {

        Company_Mail mail = mailList.get(position);
        holder.title_specialize_mail_company.setText(mail.getTiltlespecialize());
        holder.title_mail_company.setText(mail.getTitle());
        holder.name_student_mail_company.setText(mail.getNamestudent());
        holder.content_mail_company.setText(mail.getContent());

        //set visible layout got adapter
        boolean isExpanded = mailList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return mailList.size();
    }

    class MailVH extends RecyclerView.ViewHolder {

        private static final String TAG = "MovieVH";

        ConstraintLayout expandableLayout;
        TextView title_specialize_mail_company,title_mail_company, name_student_mail_company, content_mail_company;

        public MailVH(@NonNull final View itemView) {
            super(itemView);
            title_specialize_mail_company = itemView.findViewById(R.id.title_specialize_mail_company);
            title_mail_company = itemView.findViewById(R.id.title_mail_company);
            name_student_mail_company = itemView.findViewById(R.id.name_student_mail_company);
            content_mail_company = itemView.findViewById(R.id.content_mail_company);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            title_specialize_mail_company.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Company_Mail movie = mailList.get(getAdapterPosition());
                    movie.setExpanded(!movie.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}
