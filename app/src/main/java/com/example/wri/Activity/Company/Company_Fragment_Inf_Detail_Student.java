package com.example.wri.Activity.Company;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.wri.R;

public class Company_Fragment_Inf_Detail_Student extends Fragment {
//    Button btnShowContactInfo, btnContactCenter;
//    TextView txtEmail, txtPhone;
    // listview JD
    String[] listJD = {"Web", "Android", "Maketing", "Quản lí nhân sự", "Fresher React"};
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_inf__detail_student, container, false);
        // Inflate the layout for this fragment
        //init
//        btnShowContactInfo = view.findViewById(R.id.btn_show_contact_info);
//        btnContactCenter = view.findViewById(R.id.btn_contact_center);
//        txtEmail = view.findViewById(R.id.txt_email_show);
//        txtPhone = view.findViewById(R.id.txt_phone_show);

        //
//        btnShowContactInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog();
//            }
//        });
        //
//        btnContactCenter.setVisibility(View.VISIBLE);
//
//        txtPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogCall();
//            }
//        });
//        txtEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogEmail();
//            }
//        });
        return view;
    }

    private void Dialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog);
        final Button btnProceed = dialog.findViewById(R.id.btn_yes);


        Button btnCancel = dialog.findViewById(R.id.btn_no);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                txtEmail.setText("vyhtps10208@fpt.edu.vn");
//                txtPhone.setText("0902165165");
//
//                dialog.dismiss();
//                btnShowContactInfo.setEnabled(false);
//                btnShowContactInfo.setBackgroundResource(R.drawable.btn_disabled);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void DialogCall(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_phone);
        Button btnCall = dialog.findViewById(R.id.btn_call);
        Button btnLater = dialog.findViewById(R.id.btn_later);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Call", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Later", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void DialogEmail() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_email);
        Button btnJD = dialog.findViewById(R.id.btn_jd_email);
        Button btnLater = dialog.findViewById(R.id.btn_later_email);
        btnJD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogListView();
                dialog.dismiss();
            }
        });
        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hủy", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void DialogListView(){
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());
        View row = getLayoutInflater().inflate(R.layout.custom_dialog_listview, null);
        ListView listView = row.findViewById(R.id.lv_jd);

        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, listJD);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        alertdialog.setView(row);
        final AlertDialog dialog = alertdialog.create();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogConfirmJD();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void DialogConfirmJD() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_confirm_jd);

        Button btnSendJD = dialog.findViewById(R.id.btn_send_confirm_jd);
        Button btnLaterJD = dialog.findViewById(R.id.btn_later_confirm_jd);
        btnSendJD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Gửi email job", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnLaterJD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hủy", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}