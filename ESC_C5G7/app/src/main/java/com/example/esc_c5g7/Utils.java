package com.example.esc_c5g7;


import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;


public class Utils {

    Context context;

    public Utils(Context context) {
        this.context = context;
    }


    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void showSuccessToast(String successMsg) {
        Toast.makeText(context.getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
    }

    public void showFailureToast(String errorMsg) {
        Toast.makeText(context.getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
    }
}

