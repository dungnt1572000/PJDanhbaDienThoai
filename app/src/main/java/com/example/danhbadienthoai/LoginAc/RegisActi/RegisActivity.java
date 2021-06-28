package com.example.danhbadienthoai.LoginAc.RegisActi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danhbadienthoai.LoginAc.Login;
import com.example.danhbadienthoai.MySharePreference.Datalocal;
import com.example.danhbadienthoai.R;
import com.example.danhbadienthoai.Users.Users;

import java.util.ArrayList;
import java.util.List;

public class RegisActivity extends AppCompatActivity {
    private EditText editUser,editPass1,editPass2;
    private Button btBack,btRegis;
    TextView txtCheck;
    List<Users> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        init();
        setupUI(findViewById(R.id.regis_layout_parent));
        list = Datalocal.getUserList();
        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k=0; // check co hay ko
                Intent intent = new Intent();
                if(!TextUtils.isEmpty(editPass1.getText().toString().trim()) && !TextUtils.isEmpty(editUser.getText().toString().trim()) &&
                        editPass1.getText().toString().trim().equals(editPass2.getText().toString().trim())){
                    String nameuser = editUser.getText().toString().trim();
                    for (int i = 0 ; i< list.size() ; i++){
                        if(nameuser.equals(list.get(i).getUsername())){
                            k = 1;
                        }
                    }
                    if (k==1){
                        txtCheck.setText("Username existed");
                        txtCheck.setVisibility(View.VISIBLE
                        );
                    }
                    if(k==0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("username", editUser.getText().toString().trim());
                        bundle.putString("userpass", editPass1.getText().toString().trim());
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(RegisActivity.this, "Nhap thieu hoac 2 Mat Khau khong trung khop", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisActivity.this,Login.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        editUser =(EditText) findViewById(R.id.regisEditUserName);
        editPass1 =(EditText) findViewById(R.id.regisPass);
        editPass2 =(EditText) findViewById(R.id.regisagainPass);
        btBack = (Button) findViewById(R.id.btBack);
        btRegis  = (Button) findViewById(R.id.RegisBT);
        txtCheck = (TextView) findViewById(R.id.checkText);
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
                    hideSoftKeyboard(RegisActivity.this);
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