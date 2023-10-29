package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Login_SignUp extends AppCompatActivity {
    private Button btnLogin,btnSignUp;
    private LinearLayout backgroundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);
        initData();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_login_signup);
        backgroundLayout.startAnimation(animation);
        btnLogin.setOnClickListener(v->{
            Intent intent = new Intent(Login_SignUp.this, LoginLayout.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finishAffinity();
        });
        btnSignUp.setOnClickListener(v->{
            Intent intent = new Intent(Login_SignUp.this, SignUpLayout.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finishAffinity();
        });
    }
    public void initData(){
        btnLogin = findViewById(R.id.btnLoginSplash);
        btnSignUp = findViewById(R.id.btnSignUpSplash);
        backgroundLayout = findViewById(R.id.backgroundLogin_Signup);
    }
    boolean doubleBack = false;

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            return;
        }

        this.doubleBack = true;
        Toast.makeText(this, "Bấm thêm 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBack=false;
            }
        }, 2000);
    }
}