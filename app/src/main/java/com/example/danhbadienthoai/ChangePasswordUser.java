package com.example.danhbadienthoai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danhbadienthoai.LoginAc.Login;
import com.example.danhbadienthoai.MySharePreference.Datalocal;
import com.example.danhbadienthoai.Users.Users;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChangePasswordUser extends AppCompatActivity {
    TextView newpass,renewpass;
    Button btchangepass , btBack;
    List<Users> listuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_user);
        listuser = new ArrayList<>();
        newpass = findViewById(R.id.newPass);
        renewpass = findViewById(R.id.renewPass);
        btBack = findViewById(R.id.btBack);
        btchangepass = findViewById(R.id.changedBT);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String newPass = newpass.getText().toString().trim();
                String renewPass = renewpass.getText().toString().trim();
                if (!newPass.isEmpty() && renewPass.equals(newPass) && !newpass.equals(intent.getStringExtra("userpass"))){
                    listuser = Datalocal.getUserList();
                    for (int i = 0 ; i < listuser.size(); i ++){
                        if(listuser.get(i).getUsername().equals(intent.getStringExtra("username"))){
                            listuser.get(i).setPassword(newPass);
                            Datalocal.setUserList(listuser);
                            break;
                        }
                    }
                    Toast.makeText(ChangePasswordUser.this,"Password Changed",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(ChangePasswordUser.this, Login.class);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(ChangePasswordUser.this,"Khong duoc",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}