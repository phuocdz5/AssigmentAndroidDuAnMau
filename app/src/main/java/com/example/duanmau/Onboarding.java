package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duanmau.Adapter.Fragment_SplashAdapter;

import me.relex.circleindicator.CircleIndicator3;

public class Onboarding extends AppCompatActivity implements View.OnClickListener {
    private Button btnPre,btnNext;
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        initData();
        btnPre.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        Fragment_SplashAdapter splashAdapter = new Fragment_SplashAdapter(this);
        viewPager2.setAdapter(splashAdapter);
        circleIndicator3.setViewPager(viewPager2);
        splashAdapter.registerAdapterDataObserver(circleIndicator3.getAdapterDataObserver());
}
    private void initData() {
        btnPre =findViewById(R.id.btnPre);
        btnNext = findViewById(R.id.btnNext);
        viewPager2 = findViewById(R.id.viewPager);
        circleIndicator3 = findViewById(R.id.indicator);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnNext){
            if (viewPager2.getCurrentItem()==1){
                startActivity(new Intent(Onboarding.this,Login_SignUp.class));
            }else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
            }
        }
        if(v.getId()==R.id.btnPre){
            if(viewPager2.getCurrentItem()==0){
                return;
            }else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()-1);
            }
        }
    }
}