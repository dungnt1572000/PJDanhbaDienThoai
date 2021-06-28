package com.example.danhbadienthoai.FragmentABC;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.danhbadienthoai.MainActivity;
import com.example.danhbadienthoai.MySharePreference.Datalocal;
import com.example.danhbadienthoai.Product_chitiet;
import com.example.danhbadienthoai.R;
import com.example.danhbadienthoai.databaseproduct.ChitietProduct;
import com.example.danhbadienthoai.databaseproduct.DataLocal;
import com.example.danhbadienthoai.databaseproduct.Product;
import com.example.danhbadienthoai.databaseproduct.ProductAdapter;
import com.example.danhbadienthoai.event.AddProductEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class FragmentProduct extends Fragment implements ChitietProduct {
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    ChitietProduct chitietProduct;
    List<Product> mlist;
    MainActivity mainActivity;
    private AddProductEvent AddProductEvent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rclViewBuy);
        mainActivity = (MainActivity) getActivity();
//        if(mlist==null){
//            mlist = new ArrayList<>();
//        }
        if(getActivity() instanceof MainActivity) {
            mlist = ((MainActivity) getActivity()).orderList;
        }
        // the m thoat app la mat het data à
        //uh ok
        productAdapter = new ProductAdapter(mlist,chitietProduct);
        productAdapter.setData(mlist);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    // m co cai rxjava hay rx android k t con deo biet day la cai gi
    // the dung event bus
    // implement cai nay


    @Override
    public void Clicktosee(Product product) {
        Intent intent = new Intent(getContext(), Product_chitiet.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("sanpham",product);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onRefreshing() {
        try {
            productAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// dc roi day, h fix bug