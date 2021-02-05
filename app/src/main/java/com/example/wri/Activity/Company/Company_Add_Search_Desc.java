package com.example.wri.Activity.Company;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.wri.Adapter.Company_Mail_Custom_Adapter;
import com.example.wri.Model.Company_Mail;
import com.example.wri.R;

import java.util.ArrayList;
import java.util.List;

public class Company_Add_Search_Desc extends AppCompatActivity {
    ImageView id_back_company_add_search_desc;
    RecyclerView recyclerView;
    ImageView creat_form_mail_company;
    List<Company_Mail> mailList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_add__search__desc);
        recyclerView = findViewById(R.id.rc_mail_company);
        init();
        initData();
        initRecyclerView();
        id_back_company_add_search_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Company_Add_Search_Desc.this,Company_Main.class);
                startActivity(intent);
            }
        });
        creat_form_mail_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiglogAddmail();
            }
        });
    }



    private void init() {
        id_back_company_add_search_desc = findViewById(R.id.id_back_company_add_search_desc);
        creat_form_mail_company= findViewById(R.id.creat_form_mail_company);
    }
    private void initRecyclerView() {
        Company_Mail_Custom_Adapter mailadapter = new Company_Mail_Custom_Adapter(mailList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mailadapter);
    }
    private void initData() {
        mailList = new ArrayList<>();
        mailList.add(new Company_Mail("Marketing","Thư ứng tuyển","Tiểu Lộc","Tiểu Lộc thân mến,\n" +
                "\n" +
                " \n" +
                "\n" +
                "Trước hết, chúng tôi rất cám ơn sự quan tâm bạn đã dành cho công ty của chúng tôi. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Tên tôi là Tuấn Vỹ và tôi là Trưởng bộ phận tuyển dụng của công ty. Qua hồ sơ của bạn, chúng tôi nhận thấy bạn có những tiềm năng để trở thành một phần của công ty chúng tôi. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Chúng tôi rất hy vọng có thể trao đổi thêm với bạn trong một buổi phỏng vấn ngắn qua điện thoại / Skype / tại công ty Unitext. Đây là một bước cần thiết trong quá trình tuyển dụng để chúng tôi có thể hiểu hơn về bạn cũng như được chia sẻ với bạn nhiều hơn về câu chuyện của chúng tôi. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Bạn vui lòng trả lời lại email này trước 20/10 để xác nhận khả năng tham gia buổi phỏng vấn. Nếu có bất kì điều gì bất tiện, bạn có thể liên hệ ngay qua email này hoặc qua 03508040404. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Chúng tôi rất mong sớm được gặp và trò chuyện với bạn. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Trân trọng,"));
        mailList.add(new Company_Mail("Quản lí nhân lực","Thư ứng tuyển","Tiểu Vũ","Tiểu Lộc thân mến,\n" +
                "\n" +
                " \n" +
                "\n" +
                "Trước hết, chúng tôi rất cám ơn sự quan tâm bạn đã dành cho công ty của chúng tôi. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Tên tôi là Tuấn Vỹ và tôi là Trưởng bộ phận tuyển dụng của công ty. Qua hồ sơ của bạn, chúng tôi nhận thấy bạn có những tiềm năng để trở thành một phần của công ty chúng tôi. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Chúng tôi rất hy vọng có thể trao đổi thêm với bạn trong một buổi phỏng vấn ngắn qua điện thoại / Skype / tại công ty Unitext. Đây là một bước cần thiết trong quá trình tuyển dụng để chúng tôi có thể hiểu hơn về bạn cũng như được chia sẻ với bạn nhiều hơn về câu chuyện của chúng tôi. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Bạn vui lòng trả lời lại email này trước 20/10 để xác nhận khả năng tham gia buổi phỏng vấn. Nếu có bất kì điều gì bất tiện, bạn có thể liên hệ ngay qua email này hoặc qua 03508040404. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Chúng tôi rất mong sớm được gặp và trò chuyện với bạn. \n" +
                "\n" +
                " \n" +
                "\n" +
                "Trân trọng,"));
    }
    private  void  DiglogAddmail(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_addmail);

        final  Button btn_add_email_company = dialog.findViewById(R.id.btn_add_email_company);
        final Button btn_cancel_add_email_company = dialog.findViewById(R.id.btn_cancel_add_email_company);
        btn_add_email_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText special_title_add_mail_company = findViewById(R.id.special_title_add_mail_company);
                Toast.makeText(Company_Add_Search_Desc.this, "add specialize", Toast.LENGTH_SHORT).show();
            }
        });
        btn_cancel_add_email_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}