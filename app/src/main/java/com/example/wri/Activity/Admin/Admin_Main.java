package com.example.wri.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wri.Activity.Admin.Class.Admin_Add_Student_Class;
import com.example.wri.Activity.Admin.Class.Admin_List_Class;
import com.example.wri.Activity.Admin.Company.Admin_Company_List;
import com.example.wri.Activity.Admin.Student.Admin_List_Student;
import com.example.wri.Activity.Admin.Teacher.Admin_List_AllTeacher;
import com.example.wri.Activity.LoginandRegister.Login;
import com.example.wri.Model.Points;
import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Main extends AppCompatActivity {
    LinearLayout ln_admin_class,ln_admin_teacher,ln_admin_company,ln_admin_student,ln_admin_updateAllPoint;
    TextView logout_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__main);
        init();
        ln_admin_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Main.this, Admin_List_Class.class));
            }
        });
        ln_admin_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Main.this, Admin_List_AllTeacher.class));
            }
        });
        ln_admin_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Main.this, Admin_Company_List.class));
            }
        });
        ln_admin_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Main.this, Admin_List_Student.class));
            }
        });
        logout_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        ln_admin_updateAllPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataService = APIService.getService();
                Call<List<Students>> callback = dataService.getAllIdStu();
                callback.enqueue(new Callback<List<Students>>() {
                    @Override
                    public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                        for (int i=0;i<response.body().size();i++){
                            getPointsStu(response.body().get(i).getId());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Students>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void init() {
        ln_admin_class = findViewById(R.id.ln_admin_class);
        ln_admin_teacher = findViewById(R.id.ln_admin_teacher);
        ln_admin_company = findViewById(R.id.ln_admin_company);
        ln_admin_student = findViewById(R.id.ln_admin_student);
        ln_admin_updateAllPoint=findViewById(R.id.ln_admin_updateAllPoint);
        logout_admin = findViewById(R.id.logout_admin);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getPointsStu(final String idStu) {
        DataService dataService = APIService.getService();
        Call<List<List<Points>>> callback = dataService.getKN_LVPointStudent(idStu);
        callback.enqueue(new Callback<List<List<Points>>>() {
            @Override
            public void onResponse(Call<List<List<Points>>> call, Response<List<List<Points>>> response) {
                double p1=0, p2=0, p3=0, p4=0;
                double DTB[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
                double chuyenmon=0, ngoaingu=0, kynang=0, thaido=0;
                String noi = "0", phanxa = "0", phatam = "0";
                double giaotiep = 0;
                for (int i = 0; i < 16; i++) {
                    if (response.body().get(i).size() == 0) {
                        DTB[i] = 0;
                    } else {
                        try {
                            p1 = Double.parseDouble(response.body().get(i).get(0).getPoint());
                        } catch (Exception e) {
                            p1 = 0;
                        }
                        try {
                            p2 = Double.parseDouble(response.body().get(i).get(1).getPoint());
                        } catch (Exception e) {
                            p2 = 0;
                        }
                        try {
                            p3 = Double.parseDouble(response.body().get(i).get(2).getPoint());
                        } catch (Exception e) {
                            p3 = 0;
                        }
                        try {
                            p4 = Double.parseDouble(response.body().get(i).get(3).getPoint());
                        } catch (Exception e) {
                            p4 = 0;
                        }
                        if (i == 2) {
                            DTB[i] = Double.parseDouble(response.body().get(i).get(0).getPoint());
                        }
                        if (i == 3) {
                            for (int k = 0; k < response.body().get(i).size(); k++) {
                                if (response.body().get(i).get(k).getNameReq().equals("Nói")) {
                                    noi = response.body().get(i).get(k).getPoint();
                                }
                                if (response.body().get(i).get(k).getNameReq().equals("Phản xạ")) {
                                    phanxa = response.body().get(i).get(k).getPoint();
                                }
                                if (response.body().get(i).get(k).getNameReq().equals("Phát âm")) {
                                    phatam = response.body().get(i).get(k).getPoint();
                                }
                            }
                            giaotiep = Double.parseDouble(noi) * 0.4 + Double.parseDouble(phanxa) * 0.4 + Double.parseDouble(phatam) * 0.2;
                            Log.d("avcc", "noi: " + noi + " | phan xa: " + phanxa + " | phat am: " + phatam);
                        }
                        if (i == 4) {
                            DTB[i] = Double.parseDouble(response.body().get(i).get(0).getPoint());
                        }
                        if (i == 6) {
                            DTB[i] = Double.parseDouble(response.body().get(i).get(0).getPoint());
                        } else {
                            DTB[i] = p1 + p2 * 0.1 + p3 * 0.07 + p4 * 0.01;
                        }
                    }
                }
                chuyenmon = Double.parseDouble(String.format("%.2f", (DTB[0] * 0.4 + DTB[1] * 0.3 + DTB[2] * 0.3)));
                ngoaingu = Double.parseDouble(String.format("%.2f", (giaotiep * 0.3 + DTB[4] * 0.4 + DTB[5] * 0.3 + DTB[6] * 0.2)));
                kynang = Double.parseDouble(String.format("%.2f", (DTB[14] * 0.3 + DTB[13] * 0.2 + DTB[9] * 0.2 + DTB[12] * 0.2 + DTB[10] * 0.1 + DTB[11] * 0.1 + DTB[15] * 0.1)));
                thaido = Double.parseDouble(String.format("%.2f", (DTB[7] * 0.3 + DTB[8] * 0.7)));
                if (chuyenmon > 5) {
                    chuyenmon = 5;
                }
                if (ngoaingu > 5) {
                    ngoaingu = 5;
                }
                if (kynang > 5) {
                    kynang = 5;
                }
                if (thaido > 5) {
                    thaido = 5;
                }
                double point =Double.parseDouble(String.format("%.2f", (chuyenmon + kynang + ngoaingu + thaido) / 4));

                DataService dataService = APIService.getService();
                Call<Students> callback = dataService.updateAllStuPoint(idStu,point+"");
                callback.enqueue(new Callback<Students>() {
                    @Override
                    public void onResponse(Call<Students> call, Response<Students> response) {
                        if(response.body().getIsSuccess() == 1){
                            Toast.makeText(Admin_Main.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("avc",response.body().getMessage());
                        }
                        if(response.body().getIsSuccess() == 0){
                            Toast.makeText(Admin_Main.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("avc",response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<Students> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<List<Points>>> call, Throwable t) {

            }
        });
    }
}