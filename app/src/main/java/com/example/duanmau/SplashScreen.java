package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.duanmau.DAO.ThuThuDAO;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(thuThuDAO.checkAccount()){
                    Intent intent = new Intent(SplashScreen.this, Login_SignUp.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finishAffinity();
                }else {
                    Intent intent = new Intent(SplashScreen.this, Onboarding.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finishAffinity();
                }
            }
        },3000);
    }
}