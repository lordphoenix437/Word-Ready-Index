package com.example.wri.Activity.LoginandRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wri.R;



public class ReLogin extends AppCompatActivity {
    Animation topanim,bottomanim;
    LinearLayout linearLayout;
    ImageView iconapp;
    Button btn_re_login,btn_re_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_login);
        init();
        //set animation
//        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
//        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animatoion);
//        linearLayout.setAnimation(topanim);
//        iconapp.setAnimation(bottomanim);

        btn_re_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReLogin.this,Login.class);
                startActivity(intent);
            }
        });
        btn_re_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReLogin.this,Singup_Main.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        linearLayout = findViewById(R.id.linearLayout);
        iconapp = findViewById(R.id.icon_app);
        btn_re_login = findViewById(R.id.btn_relogin);
        btn_re_signup =findViewById(R.id.btn_re_signup);
    }


}