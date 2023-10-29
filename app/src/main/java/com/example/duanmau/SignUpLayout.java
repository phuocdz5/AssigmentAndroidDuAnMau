package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.duanmau.DAO.ThuThuDAO;
import com.example.duanmau.Model.ThuThu;

public class SignUpLayout extends AppCompatActivity {
    private Button btnBack,btnSignUp;
    private EditText edUser,edPass,edFullName;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_layout);
        initData();
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_login_signup);
        linearLayout.startAnimation(animation);
        btnBack.setOnClickListener(v->{
            startActivity(new Intent(SignUpLayout.this,Login_SignUp.class));
        });
        btnSignUp.setOnClickListener(v->{
            String user = edUser.getText().toString();
            String pass = edPass.getText().toString();
            String fullName = edFullName.getText().toString();
            if(user.equals("")||pass.equals("")||fullName.equals("")){
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            }else {
                Boolean checkUser = thuThuDAO.checkUser(user);
                if(!checkUser){
                    thuThuDAO.insertTT(new ThuThu(user,fullName,pass));
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpLayout.this, LoginLayout.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Mã thủ thư đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void initData(){
        btnBack = findViewById(R.id.btnBackSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        edUser = findViewById(R.id.signupUser);
        edPass = findViewById(R.id.signupPass);
        edFullName = findViewById(R.id.signupFullName);
        linearLayout = findViewById(R.id.backgroundSign);
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