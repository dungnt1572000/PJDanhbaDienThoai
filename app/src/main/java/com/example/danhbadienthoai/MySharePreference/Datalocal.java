package com.example.danhbadienthoai.MySharePreference;

import android.content.Context;

import com.example.danhbadienthoai.Users.Users;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Datalocal {
    private static final String PREF_USERS = "PREF_USERS";
    private static Datalocal instance;
    private MySharePreference mySharePreference;
    public void init(Context context){
        instance = new Datalocal();
        instance.mySharePreference = new MySharePreference(context);
    }
    public static Datalocal getInstance(){
        if(instance == null){
            instance = new Datalocal();
        }
                return instance;
    }
    public static void setUserList(List<Users> list){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(list).getAsJsonArray();
        String str = jsonArray.toString();
        Datalocal.getInstance().mySharePreference.putStringvalue(PREF_USERS,str);
    }
    public static List<Users> getUserList(){
        String str = Datalocal.getInstance().mySharePreference.getStringvalue(PREF_USERS);
        List<Users> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(str);
            JSONObject jsonObject;
            Users user;
            Gson gson = new Gson();
            for (int i =0 ; i< jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                user = gson.fromJson(jsonObject.toString(),Users.class);
                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
