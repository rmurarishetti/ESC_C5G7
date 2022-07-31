package com.example.ecs_c5g7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecs_c5g7.utils.SessionManager;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageView paypal_logo = findViewById(R.id.paypal);
        paypal_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new SessionManager(getApplicationContext()).isLogin()) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, ChooseLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }


}