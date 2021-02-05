package com.example.wri.Activity.Student;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.wri.Model.Points;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Fragment_Point extends Fragment {
    LinearLayout ln_layout1, ln_layout2, ln_layout3, ln_layout4, ln_layout5;
    TextView tv_point1, tv_point2, tv_point3, tv_point4, tv_point5, tv_desc1, tv_desc2, tv_desc3, tv_desc4, tv_desc5, tv_getPoint, tv_chuyenmon, tv_kynang, tv_tienganh, tv_thaido;
    double point = 0;
    String idStu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student___point, container, false);
        ln_layout1 = view.findViewById(R.id.ln_layout1);
        ln_layout2 = view.findViewById(R.id.ln_layout2);
        ln_layout3 = view.findViewById(R.id.ln_layout3);
        ln_layout4 = view.findViewById(R.id.ln_layout4);
        ln_layout5 = view.findViewById(R.id.ln_layout5);
        tv_point1 = view.findViewById(R.id.tv_point1);
        tv_point2 = view.findViewById(R.id.tv_point2);
        tv_point3 = view.findViewById(R.id.tv_point3);
        tv_point4 = view.findViewById(R.id.tv_point4);
        tv_point5 = view.findViewById(R.id.tv_point5);
        tv_desc1 = view.findViewById(R.id.tv_desc1);
        tv_desc2 = view.findViewById(R.id.tv_desc2);
        tv_desc3 = view.findViewById(R.id.tv_desc3);
        tv_desc4 = view.findViewById(R.id.tv_desc4);
        tv_desc5 = view.findViewById(R.id.tv_desc5);
        tv_chuyenmon = view.findViewById(R.id.tv_chuyenmon);
        tv_kynang = view.findViewById(R.id.tv_kynang);
        tv_tienganh = view.findViewById(R.id.tv_tienganh);
        tv_thaido = view.findViewById(R.id.tv_thaido);

        idStu = getArguments().getString("idStu");

        getPointsStu();


        return view;
    }

    private void getPointsStu() {
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
                tv_chuyenmon.setText(chuyenmon + "");
                tv_kynang.setText(kynang + "");
                tv_tienganh.setText(ngoaingu + "");
                tv_thaido.setText(thaido + "");
                point =Double.parseDouble(String.format("%.2f", (chuyenmon + kynang + ngoaingu + thaido) / 4));
                Log.d("avccc", point + "");

                Log.d("avccc", "Chỉ số chuyên môn: " + chuyenmon);
                Log.d("avccc", "Chỉ số ngoại ngữ: " + ngoaingu);
                Log.d("avccc", "Chỉ số kỹ năng: " + kynang);
                Log.d("avccc", "Chỉ số thái độ: " + thaido);

                if (point <= 1) {
                    ln_layout1.setBackgroundResource(R.drawable.circle_pick);
                    tv_point1.setText(point + "");
                    tv_point1.setTextColor(Color.parseColor("#000000"));
                    tv_point1.setAlpha(1);
                    tv_desc1.setTextColor(Color.parseColor("#000000"));
                } else if (point <= 2) {
                    ln_layout2.setBackgroundResource(R.drawable.circle_pick);
                    tv_point2.setText(point + "");
                    tv_point2.setTextColor(Color.parseColor("#000000"));
                    tv_point2.setAlpha(1);
                    tv_desc2.setTextColor(Color.parseColor("#000000"));
                } else if (point <= 3) {
                    ln_layout3.setBackgroundResource(R.drawable.circle_pick);
                    tv_point3.setText(point + "");
                    tv_point3.setTextColor(Color.parseColor("#000000"));
                    tv_point3.setAlpha(1);
                    tv_desc3.setTextColor(Color.parseColor("#000000"));
                } else if (point <= 4) {
                    ln_layout4.setBackgroundResource(R.drawable.circle_pick);
                    tv_point4.setText(point + "");
                    tv_point4.setTextColor(Color.parseColor("#000000"));
                    tv_point4.setAlpha(1);
                    tv_desc4.setTextColor(Color.parseColor("#000000"));
                } else {
                    ln_layout5.setBackgroundResource(R.drawable.circle_pick);
                    tv_point5.setText(point + "");
                    tv_point5.setTextColor(Color.parseColor("#000000"));
                    tv_point5.setAlpha(1);
                    tv_desc5.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void onFailure(Call<List<List<Points>>> call, Throwable t) {

            }
        });
    }
}