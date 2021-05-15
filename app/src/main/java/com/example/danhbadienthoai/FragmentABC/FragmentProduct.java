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
import com.example.danhbadienthoai.MySharePreference.Datalocal;
import com.example.danhbadienthoai.R;
import com.example.danhbadienthoai.databaseproduct.DataLocal;
import com.example.danhbadienthoai.databaseproduct.Product;
import com.example.danhbadienthoai.databaseproduct.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProduct extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView imgpro;
    TextView pro_id,pro_name;
    Button btcallBack;
    public FragmentProduct() {
        // Required empty public constructor
    }
    public interface NhanData{
        void nhanData(Product product);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProduct.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProduct newInstance(String param1, String param2) {
        FragmentProduct fragment = new FragmentProduct();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        imgpro = (ImageView) view.findViewById(R.id.img_product);
        pro_name =(TextView) view.findViewById(R.id.tenproduct);
        pro_id = (TextView) view.findViewById(R.id.product_ID);
        return view;
    }
    public void nhanData(Product product){
        try {
            Glide.with(getContext()).load(product.getUrl()).into(imgpro);
            pro_name.setText(product.getName());
            pro_id.setText(String.valueOf(product.getId()));
        }catch(Exception e){
            imgpro.setImageResource(R.drawable.iphone12);
            pro_name.setText("NULL");
            pro_id.setText("NULL");
        }
    }
}