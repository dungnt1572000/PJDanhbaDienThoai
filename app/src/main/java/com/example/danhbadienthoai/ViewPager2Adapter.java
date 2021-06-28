package com.example.danhbadienthoai;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.danhbadienthoai.FragmentABC.FragmentHome;
import com.example.danhbadienthoai.FragmentABC.FragmentProduct;
import com.example.danhbadienthoai.FragmentABC.FragmentUser;

public class ViewPager2Adapter extends FragmentStateAdapter {


    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public FragmentProduct fragmentProduct = new FragmentProduct();

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentHome();
            case 1:
                return fragmentProduct;
            case 2:
                return new FragmentUser();
            default:
                return new FragmentProduct();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
