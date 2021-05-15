package com.example.danhbadienthoai.FragmentABC;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.danhbadienthoai.MainActivity;
import com.example.danhbadienthoai.R;
import com.example.danhbadienthoai.databaseproduct.ChitietProduct;
import com.example.danhbadienthoai.databaseproduct.DataLocal;
import com.example.danhbadienthoai.databaseproduct.Product;
import com.example.danhbadienthoai.databaseproduct.ProductAdapter;
import com.example.danhbadienthoai.product.PhotoAdapter;
import com.example.danhbadienthoai.product.Photos;
import com.example.danhbadienthoai.search.SearchProduct;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class FragmentHome extends Fragment implements ChitietProduct{
    RecyclerView rclView;
    ProductAdapter productAdapter;
    List<Product> list;
    List<Photos> photolist;
    CircleIndicator circleIndicator;
    ViewPager viewPager;
    PhotoAdapter photosAdapter;
    FloatingActionButton floatingActionButton;
    Timer timer;
    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        init(view);
        photolist = getListPhotos();
        photosAdapter = new PhotoAdapter(photolist);
        viewPager.setAdapter(photosAdapter);
        circleIndicator.setViewPager(viewPager);
        photosAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSildeImage();
        list = DataLocal.getInstance(getContext()).productDAO().getListProduct();
        if(list.size()==0){
            list = getList();
            for(int i=0; i < list.size() ; i++){
                DataLocal.getInstance(this.getContext()).productDAO().insertProduct(list.get(i));
            }
        }
        productAdapter = new ProductAdapter(list,  this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false);
        rclView.setLayoutManager(linearLayoutManager);
        rclView.setAdapter(productAdapter);
        DataLocal.getInstance(getContext()).productDAO().getListProduct();
        rclView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                if(dy>0){
                    floatingActionButton.hide();
                }
                if(dy<0){
                    floatingActionButton.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getContext(),SearchProduct.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null)
            timer.cancel();
        timer=null;
    }

    public void autoSildeImage(){
        if(photolist==null || viewPager ==null || photolist.isEmpty()){
            return;
        }
        // khoi tao timer
        if(timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItems = viewPager.getCurrentItem();
                        int totalItems = photolist.size()-1;
                        if (currentItems < totalItems){
                            currentItems++;
                            viewPager.setCurrentItem(currentItems);
                        }
                        else{
                            currentItems=0;
                            viewPager.setCurrentItem(currentItems);
                        }
                    }
                });
            }
        },500,3000);

    }
    public void init(View view){
        rclView = (RecyclerView) view.findViewById(R.id.fragmentproduct_rclView);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.circleIndicator);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager2Slide);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingbutton);
    }
    public List<Product> getList(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"IPhone12 Pro Max","https://image.thanhnien.vn/1080/uploaded/nthanhluan/2020_10_14/1_foyn.jpg"));
        list.add(new Product(2,"Note 20","https://cdn.tgdd.vn/Products/Images/42/234315/samsung-galaxy-a32-600x600-600x600.jpg"));
        list.add(new Product(3,"Galaxy A52","https://cdn.tgdd.vn/Products/Images/42/235440/samsung-galaxy-a52-5g-thumb-blue-600x600-200x200.jpg"));
        list.add(new Product(4,"XiaoMi RedMi8","https://cdn.tgdd.vn/Products/Images/42/233130/xiaomi-redmi-9t-6gb-110621-080650-600x600.jpg"));
        list.add(new Product(5,"Xiaomi RedMi 10","https://cdn.tgdd.vn/Products/Images/42/229228/xiaomi-redmi-note-10-pro-thumb-xam-600x600-600x600.jpg"));
        list.add(new Product(6,"Nokia 5.3","https://hc.com.vn/i/ecommerce/media/GS.004470_FEATURE_63272.jpg"));
        list.add(new Product(7,"Huawei Nove3i","https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/huawei-nova-3i-mo-ta-1.jpg"));
        list.add(new Product(8,"Huawei P30","https://cdn1.viettelstore.vn/images/Product/ProductImage/medium/676122753.jpeg"));
        return list;
    }
    public List<Photos> getListPhotos(){
        List<Photos> mlist = new ArrayList<>();
        mlist.add(new Photos(R.drawable.huawei));
        mlist.add(new Photos(R.drawable.galaxinote8));
        mlist.add(new Photos(R.drawable.iphone12));
        mlist.add(new Photos(R.drawable.opponeo7));
        mlist.add(new Photos(R.drawable.matexhuawei));
        return mlist;
    }

    @Override
    public void Clicktosee(Product product) {

    }
}