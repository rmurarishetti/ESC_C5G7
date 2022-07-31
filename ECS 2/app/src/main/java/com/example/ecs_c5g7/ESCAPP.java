package com.example.ecs_c5g7;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LifecycleObserver;
import androidx.multidex.MultiDex;

import com.google.firebase.FirebaseApp;

public class ESCAPP extends Application implements LifecycleObserver {


    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
//        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        MultiDex.install(this);
        FirebaseApp.initializeApp(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (appContext == null) {
            appContext = getApplicationContext();
        }
    }

    public Context getContext() {
        return appContext;
    }

    public static Context get() {
        return getInstance().getContext();
    }

    private static ESCAPP instance;

    public static ESCAPP getInstance() {
        return instance == null ?
                (instance = new ESCAPP()) :
                instance;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


}
