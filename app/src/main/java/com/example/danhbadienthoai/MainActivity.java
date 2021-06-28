package com.example.danhbadienthoai;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.danhbadienthoai.FragmentABC.FragmentHome;
import com.example.danhbadienthoai.FragmentABC.FragmentProduct;
import com.example.danhbadienthoai.FragmentABC.FragmentUser;
import com.example.danhbadienthoai.LoginAc.Login;
import com.example.danhbadienthoai.Users.Users;
import com.example.danhbadienthoai.databaseproduct.ChitietProduct;
import com.example.danhbadienthoai.databaseproduct.Product;
import com.example.danhbadienthoai.event.AddProductEvent;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        init();
        Users user = new Users(Login.curusers.getUsername(),Login.curusers.getPassword());
        setupView();
        setupUI(findViewById(R.id.mainac_layout_parent));
    }
    private void init() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavi);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPager);
    }

    // ok ok luon b oi! kho hieu vaicac nghien cuu observer pattern di .. the nhe ok an com thoi b oi
    public Product getProFromProduct_chitiet(){
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        Product pd = null;
        if (bundle!=null) {
            pd = (Product) bundle.getSerializable("sanphammua");
            return pd;
        }
        return null;
    }
    ViewPager2Adapter viewPager2Adapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupView() {
        viewPager2Adapter = new ViewPager2Adapter(MainActivity.this);
        viewPager2.setAdapter(viewPager2Adapter);
        viewPager2.setPageTransformer(new DepthPageTransformer());
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_product).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_user).setChecked(true);
                        break;
                }
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.action_product:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.action_user:
                        viewPager2.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
        viewPager2.setOffscreenPageLimit(3);
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
                    hideSoftKeyboard(MainActivity.this);
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

    public final List<Product> orderList = new ArrayList<>();


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddEvent(AddProductEvent event) {
        try {
            orderList.add(event.getProd());
            if (viewPager2Adapter != null) {
                viewPager2Adapter.fragmentProduct.onRefreshing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}