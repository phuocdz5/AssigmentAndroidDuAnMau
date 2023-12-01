package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.duanmau.DAO.ThuThuDAO;
import com.example.duanmau.Fragment.ChangePassword;

public class LoginLayout extends AppCompatActivity {
    private Button btnBack, btnLogin;
    private EditText edUser,edPass;
    private LinearLayout linearLayout;dddddddd
    private CheckBox checkBox;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initData();
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
        sharedPreferences = getSharedPreferences("User_File",MODE_PRIVATE);
        edUser.setText(sharedPreferences.getString("user",""));
        edPass.setText(sharedPreferences.getString("pass",""));
        checkBox.setChecked(sharedPreferences.getBoolean("checkBox",false));
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_login_signup);
        linearLayout.startAnimation(animation);
        btnBack.setOnClickListener(v->{
            startActivity(new Intent(LoginLayout.this, Login_SignUp.class));
        });
        btnLogin.setOnClickListener(v->{
            String user = edUser.getText().toString();
            String pass = edPass.getText().toString();
            if(user.equals("")||pass.equals("")){
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
            }else {
                Boolean checkLogin = thuThuDAO.checkLogin(user,pass);
                if(checkLogin){
                    checkRemember(user,pass,checkBox.isChecked());
                    Intent intent = new Intent(LoginLayout.this, MainActivity.class);
                    intent.putExtra("user",user);
                    intent.putExtra("pass",pass);
                    startActivity(intent);
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Nhập sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void checkRemember(String user,String pass,boolean status){
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        if(status){
            editor.putString("user",user);
            editor.putString("pass",pass);
            editor.putBoolean("checkBox",status);
        }else {
            editor.clear();
        }
        editor.commit();
    }
    public void initData(){
        btnBack = findViewById(R.id.btnBackLogin);
        btnLogin = findViewById(R.id.btnLogin);
        edUser = findViewById(R.id.loginUser);
        edPass = findViewById(R.id.loginPass);
        linearLayout = findViewById(R.id.backgroundLogin);
        checkBox = findViewById(R.id.checkBoxLogin);
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