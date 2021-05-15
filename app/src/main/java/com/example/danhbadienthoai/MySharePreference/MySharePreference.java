package com.example.danhbadienthoai.MySharePreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.danhbadienthoai.Users.Users;

public class MySharePreference {
    private final String PREF_USERS = "PREF_USERS";
    private Context context;

    public MySharePreference(Context context) {
        this.context = context;
    }
    public void putStringvalue(String key,String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_USERS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringvalue(String key){
        SharedPreferences sharedPreferences =context.getSharedPreferences(PREF_USERS,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}
