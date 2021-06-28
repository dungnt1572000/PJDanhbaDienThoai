package com.example.danhbadienthoai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.danhbadienthoai.databaseproduct.Product;
import com.example.danhbadienthoai.event.AddProductEvent;

import org.greenrobot.eventbus.EventBus;

public class Product_chitiet extends AppCompatActivity {
    TextView txtname,txtid,txtValue;
    ImageView imgsp;
    Button btBuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_chitiet);
        txtname = findViewById(R.id.textviewspName);
        txtid = findViewById(R.id.textviewspID);
        txtValue = findViewById(R.id.textviewspvalue);
        imgsp = findViewById(R.id.imgViewsp);


        btBuy = findViewById(R.id.btBuy);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Product sp = (Product) bundle.getSerializable("sanpham");
        txtid.append(" "+sp.getId());
        txtname.append(" "+sp.getName());
        txtValue.append(" "+sp.getValue());
        Glide.with(imgsp.getContext()).load(sp.getUrl()).into(imgsp);

        btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(new AddProductEvent(sp));
                onBackPressed();
            }
        });
    }
}