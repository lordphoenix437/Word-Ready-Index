package com.example.wri.Activity.Student;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wri.Model.Students;
import com.example.wri.R;
import com.example.wri.Service.APIService;
import com.example.wri.Service.DataService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Main extends AppCompatActivity {
    String emailStudent, idStu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        DataIntent();
        GetdataStudent(emailStudent);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(new Student_Fragment_Recruitment());

    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("emailStudent")) {
//                students = (Students) intent.getSerializableExtra("emailStudent");
                emailStudent = intent.getStringExtra("emailStudent");
            }
        }
    }

    private void GetdataStudent(final String email) {
        DataService dataService = APIService.getService();
        Call<List<Students>> callback = dataService.getDetailStudent(email);
        callback.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                idStu = response.body().get(0).getId();
            }

            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {
                Toast.makeText(Student_Main.this, "Lấy thông tin học sinh thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("emailStudent", emailStudent);
        bundle.putString("idStu",idStu);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
//        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.forum:
                    replaceFragment(new Student_Fragment_Recruitment());
                    return true;
                case R.id.course:
                    replaceFragment(new Student_Fragment_Course());
                    return true;
                case R.id.point:
                    replaceFragment(new Student_Fragment_Point());
                    return true;
                case R.id.detail:
                    replaceFragment(new Student_Fragment_Detail());
                    return true;
            }
            return false;
        }
    };
}