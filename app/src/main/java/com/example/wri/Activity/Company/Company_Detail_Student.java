package com.example.wri.Activity.Company;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Company_Detail_Student extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Company_Fragment_Inf_Detail_Student inf_student;
    private Company_Fragment_Transcript_Detail_Student transcript_student;
    private ImageView id_back_company_detail_student,img_student_detail_com;
    Students students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail__student);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        img_student_detail_com = findViewById(R.id.img_student_detail_com);
        inf_student = new Company_Fragment_Inf_Detail_Student();
        transcript_student = new Company_Fragment_Transcript_Detail_Student();
        //setcolor text hover and non click
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#0D9494"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#0D9494"));
        //

        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(transcript_student,"Bảng điểm");
        viewPagerAdapter.addFragment(inf_student,"Thông tin cá nhân");

        viewPager.setAdapter(viewPagerAdapter);
        DataIntent();

        GetDataStu(students.getId());

    }
    private  void DataIntent(){
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("com_stu")) {
                students = (Students) intent.getSerializableExtra("com_stu");
            }
        }
    }
    private  void GetDataStu(String idstu){
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getDetailStudent_admin(idstu);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                Picasso.with(getApplicationContext()).load(students.getThumbnailStudent()).into(img_student_detail_com);

            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {

            }
        });
    }
    public void onBack(View view) {
        finish();
    }

    //adapter viewpager
    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}