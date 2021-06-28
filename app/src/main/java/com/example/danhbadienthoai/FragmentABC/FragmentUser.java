package com.example.danhbadienthoai.FragmentABC;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danhbadienthoai.ChangePasswordUser;
import com.example.danhbadienthoai.LoginAc.Login;
import com.example.danhbadienthoai.MainActivity;
import com.example.danhbadienthoai.R;
import com.example.danhbadienthoai.Users.Users;

public class FragmentUser extends Fragment {
    ImageView imguser;
    TextView username;
    TextView userPass;
    Button btchangpass;

    MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        init(view);
        imguser.setImageResource(R.drawable.user);
        mainActivity = (MainActivity) getActivity();
        Users us = Login.curusers;
        if(us==null){
            Toast.makeText(mainActivity, "Null user", Toast.LENGTH_SHORT).show();
        }
        username.append(": "+us.getUsername());
        userPass.append(": "+us.getPassword());
        btchangpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePasswordUser.class);
                intent.putExtra("userpass",us.getPassword());
                intent.putExtra("username",us.getUsername());
                startActivity(intent);
            }
        });
        return view;

    }
    public void init(View view){
        imguser = view.findViewById(R.id.avataruser);
        username = view.findViewById(R.id.usertaikhoan);
        userPass = view.findViewById(R.id.userpassword);
        btchangpass = view.findViewById(R.id.changepass);
    }
}