package com.example.ecs_c5g7.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();
    SharedPreferences pref;
    Editor editor;
    Context context;

    private static final String PREF_NAME = "ECSPref";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_IS_ISADMIN = "IsADMIN";
    private static final String KEY_USERNAME = "user_name";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }


    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLogin() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getUsername() {
        return pref.getString(KEY_USERNAME, "");
    }
    public void setUsername(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.apply();
        Log.d(TAG, "User login session modified!");
    }

}