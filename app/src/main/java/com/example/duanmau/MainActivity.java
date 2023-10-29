package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.DemoDB;
import com.example.duanmau.DAO.ThuThuDAO;
import com.example.duanmau.Fragment.ChangePassword;
import com.example.duanmau.Fragment.FragmentDoanhThu;
import com.example.duanmau.Fragment.FragmentTop;
import com.example.duanmau.Fragment.QuanLyLoaiSach;
import com.example.duanmau.Fragment.QuanLyPhieuMuon;
import com.example.duanmau.Fragment.QuanLySach;
import com.example.duanmau.Fragment.QuanLyThanhVien;
import com.example.duanmau.Model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private TextView fullName,userName;
    private Bundle bundle;
    private Intent intent;
    private ThuThuDAO thuThuDAO;
    private View viewHeaderNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_draw,R.string.close_draw);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null){
            navigationView.setCheckedItem(R.id.QLPM);
            toolbar.setTitle("Quán Lý Phiếu Mượn");
            loadFragment(new QuanLyPhieuMuon());
        }
        thuThuDAO = new ThuThuDAO(getApplicationContext());
        bundle = new Bundle();
        intent = getIntent();
        String user = intent.getStringExtra("user");
        ThuThu thuThu = thuThuDAO.getIDTT(user);
        fullName.setText(thuThu.getHoTen());
        userName.setText(user);

    }

    private void initData() {
        drawerLayout = findViewById(R.id.drawerNav);
        navigationView = findViewById(R.id.navView);
        toolbar = findViewById(R.id.toolBar);
        frameLayout = findViewById(R.id.frameLayout);
        viewHeaderNav = navigationView.getHeaderView(0);
        fullName = viewHeaderNav.findViewById(R.id.fullName);
        userName = viewHeaderNav.findViewById(R.id.userName);
    }
    public void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.QLPM){
            toolbar.setTitle("Quản Lý Phiếu Mượn");
            loadFragment(new QuanLyPhieuMuon());
        }else if(item.getItemId()==R.id.QLLS){
            toolbar.setTitle("Quản Lý Loại Sách");
            loadFragment(new QuanLyLoaiSach());
        }else if(item.getItemId()==R.id.QLS){
            toolbar.setTitle("Quản Lý Sách");
            loadFragment(new QuanLySach());
        }else if(item.getItemId()==R.id.QLTV){
            toolbar.setTitle("Quản Lý Thành Viên");
            loadFragment(new QuanLyThanhVien());
        }else if(item.getItemId()==R.id.TOP){
            toolbar.setTitle("Top 10 Sách mượn nhiều nhất");
            loadFragment(new FragmentTop());
        }else if(item.getItemId()==R.id.DoanhThu){
            toolbar.setTitle("Doanh Thu");
            loadFragment(new FragmentDoanhThu());
        }else if(item.getItemId()==R.id.changePassword){
            toolbar.setTitle("Đổi Mật Khẩu");
            Fragment fragment = new ChangePassword();
            bundle.putString("user",intent.getStringExtra("user"));
            bundle.putString("pass",intent.getStringExtra("pass"));
            fragment.setArguments(bundle);
            loadFragment(fragment);
        } else if (item.getItemId()==R.id.logOut) {
            Intent intent = new Intent(MainActivity.this, Login_SignUp.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finishAffinity();

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}