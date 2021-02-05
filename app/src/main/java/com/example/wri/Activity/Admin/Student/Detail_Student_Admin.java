package com.example.wri.Activity.Admin.Student;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.wri.Activity.Company.Company_Detail_Student;
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

public class Detail_Student_Admin extends AppCompatActivity {
    Students students;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView tvnameStudent;
    private Fragment_Detail_Inf_Student inf_student;
    private Fragment_Detail_Point_Student point_student;
    ImageView id_back_detailstu_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__student__admin);
        students = new Students();
        init();


        //setcolor text hover and non click
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#0D9494"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#0D9494"));
        //
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(point_student,"Bảng điểm");
        viewPagerAdapter.addFragment(inf_student,"Thông tin cá nhân");
        viewPager.setAdapter(viewPagerAdapter);
        id_back_detailstu_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        viewPager = findViewById(R.id.view_pager_detailstudent_admin);
        tabLayout = findViewById(R.id.tab_layout_detailstudent_admin);
        id_back_detailstu_admin = findViewById(R.id.id_back_detailstu_admin);
        inf_student = new Fragment_Detail_Inf_Student();
        point_student = new Fragment_Detail_Point_Student();
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
