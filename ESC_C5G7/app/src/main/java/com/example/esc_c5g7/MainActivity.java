package com.example.esc_c5g7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        TextView welcomeText = findViewById(R.id.welcomeText);
        ImageView paypal_logo = findViewById(R.id.paypal);
        paypal_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ChooseLoginActivity.class);
                startActivity(i);
            }
        });

    }


}