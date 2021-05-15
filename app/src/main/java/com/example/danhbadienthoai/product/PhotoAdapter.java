package com.example.danhbadienthoai.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.danhbadienthoai.FragmentABC.FragmentHome;
import com.example.danhbadienthoai.R;


import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    List<Photos> list ;

    public PhotoAdapter( List<Photos> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.photos_item, container,false);
        ImageView imgphoto = view.findViewById(R.id.phtos_img);
        Photos photo = list.get(position);
        if(photo!=null){
            Glide.with(imgphoto.getContext()).load(photo.getResource()).into(imgphoto);
        }
        //ADD to view groups
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
