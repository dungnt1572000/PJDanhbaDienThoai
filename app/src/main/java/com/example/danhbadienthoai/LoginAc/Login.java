package com.example.danhbadienthoai.LoginAc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danhbadienthoai.LoginAc.RegisActi.RegisActivity;
import com.example.danhbadienthoai.MainActivity;
import com.example.danhbadienthoai.MySharePreference.Datalocal;
import com.example.danhbadienthoai.R;
import com.example.danhbadienthoai.Users.Users;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText editUserName,editPassword;
    Button btregis,btlogin;
    ConstraintLayout constraintLayout;
    private final int REQUEST_CODE = 1;
    List<Users> listuser = new ArrayList<>();
    public static Users curusers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setupUI(findViewById(R.id.login_parent));
        try{
            listuser = Datalocal.getUserList();}
        catch (NullPointerException e){
            e.printStackTrace();
        }
        btregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegisActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        btlogin.setOnClickListener(new View.OnClickListener() {
            int kcheck = 0; // check co hoac ko co 0 la ko co
            @Override
            public void onClick(View v) {
                String username = editUserName.getText().toString().trim();
                String userpass = editPassword.getText().toString().trim();
                    for (int i = 0; i < listuser.size(); i++) {
                        if (username.equals(listuser.get(i).getUsername()) == true &&
                                userpass.equals(listuser.get(i).getPassword()) == true
                        ) {
                            kcheck++;
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            curusers = new Users(username,userpass);
                            startActivity(intent);
                        }
                    }
                    if (kcheck == 0){
                        Toast.makeText(Login.this, "Nhap sai tai khoan hoac MK", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data!= null && resultCode == RESULT_OK){
            editUserName.setText(data.getStringExtra("username"));
            editPassword.setText(data.getStringExtra("userpass"));
            listuser.add(new Users(data.getStringExtra("username"),data.getStringExtra("userpass")));
            Datalocal.setUserList(listuser);
            listuser = Datalocal.getUserList();
        }
    }

    void init(){
        constraintLayout = (ConstraintLayout) findViewById(R.id.login_parent);
        editUserName = (EditText) findViewById(R.id.editUserName);
        editPassword = (EditText) findViewById(R.id.editUserPass);
        btlogin = (Button) findViewById(R.id.btlogin);
        btregis = (Button) findViewById(R.id.btregis);
    }

    // hilde keyboard
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
                    hideSoftKeyboard(Login.this);
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