package com.example.danhbadienthoai.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.danhbadienthoai.FragmentABC.FragmentHome;
import com.example.danhbadienthoai.FragmentABC.FragmentProduct;
import com.example.danhbadienthoai.MainActivity;
import com.example.danhbadienthoai.R;
import com.example.danhbadienthoai.databaseproduct.ChitietProduct;
import com.example.danhbadienthoai.databaseproduct.DataLocal;
import com.example.danhbadienthoai.databaseproduct.Product;
import com.example.danhbadienthoai.databaseproduct.ProductAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchProduct extends AppCompatActivity implements ChitietProduct{
    private final String TAG = "OK";
    EditText txtsearch;
    ImageView imgbuttonsearch;
    RecyclerView rclSearch;
    List<Product> mlist;
    ProductAdapter prd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        init();
        mlist = DataLocal.getInstance(getApplicationContext()).productDAO().getListProduct();
        prd = new ProductAdapter(mlist, (ChitietProduct) this);
        rclSearch.setAdapter(prd);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rclSearch.setLayoutManager(linearLayoutManager);
        imgbuttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulisearch();
                setupUI(findViewById(R.id.layousearch));
            }
        });
    }

    private void xulisearch() {
        String stringname = txtsearch.getText().toString().trim();
        mlist = new ArrayList<>();
        if(stringname.isEmpty()){
            mlist = DataLocal.getInstance(this).productDAO().getListProduct();
            if(mlist== null){
                Log.e(TAG,"NULL me roi");
            }
        }else {
            try {
                mlist = DataLocal.getInstance(this).productDAO().searchList(Integer.parseInt(stringname.trim()));
            }catch(Exception e) {
                mlist = DataLocal.getInstance(this).productDAO().searchList(stringname.trim());
            }
        }
        prd = new ProductAdapter(mlist, this);
        prd.setData(mlist);
        rclSearch.setAdapter(prd);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rclSearch.setLayoutManager(linearLayoutManager);
    }

    private void init(){
        txtsearch = (EditText) findViewById(R.id.txtSearch);
        imgbuttonsearch = (ImageView) findViewById(R.id.imgbuttonIconsearch);
        rclSearch = (RecyclerView) findViewById(R.id.rclViewSearch);
    }
    public void Clicktosee(Product product){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentProduct fragmentProduct = new FragmentProduct();
        fragmentProduct.nhanData(product);

    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SearchProduct.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
}