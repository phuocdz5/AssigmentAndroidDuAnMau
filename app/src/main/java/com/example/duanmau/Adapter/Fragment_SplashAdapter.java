package com.example.duanmau.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duanmau.Fragment.Fragment_Splash_1;
import com.example.duanmau.Fragment.Fragment_Splash_2;

public class Fragment_SplashAdapter extends FragmentStateAdapter {


    public Fragment_SplashAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                Fragment_Splash_1 fragment_splash_1 = new Fragment_Splash_1();
                return fragment_splash_1;
            case 1:
                Fragment_Splash_2 fragment_splash_2 = new Fragment_Splash_2();
                return fragment_splash_2;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
