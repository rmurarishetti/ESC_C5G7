package com.example.ecs_c5g7.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.ecs_c5g7.R;
import com.kaopiz.kprogresshud.KProgressHUD;

import tech.developingdeveloper.toaster.Toaster;


public class Utils {
    public static KProgressHUD progressHUD;
    Context context;

    public Utils(Context context) {
        this.context = context;
    }

    public void showProgressBar() {
        progressHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setLabel("Please wait...", context.getResources().getColor(R.color.white))
                .show();
    }

    public void hideProgressBar() {
        if (progressHUD.isShowing()) {
            progressHUD.dismiss();
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void showSuccessToast(String successMsg) {
        Toaster.Companion.popSuccess(
                context,
                successMsg,
                Toaster.LENGTH_SHORT
        ).show();
    }

    public void showFailureToast(String errorMsg) {
        Toaster.Companion.popError(
                context,
                errorMsg,
                Toaster.LENGTH_SHORT
        ).show();
    }
}
