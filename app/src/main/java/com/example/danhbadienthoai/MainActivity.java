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
import com.example.danhbadienthoai.databaseproduct.ChitietProduct;
import com.example.danhbadienthoai.databaseproduct.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ChitietProduct  {
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupView();
        setupUI(findViewById(R.id.mainac_layout_parent));
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupView() {
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(MainActivity.this);
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

    }

    private void init() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavi);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPager);
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

    @Override
    public void Clicktosee(Product product) {

    }
}