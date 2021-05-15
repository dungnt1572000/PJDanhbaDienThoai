package com.example.danhbadienthoai.MySharePreference;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Datalocal.getInstance().init(getApplicationContext());
    }
}
