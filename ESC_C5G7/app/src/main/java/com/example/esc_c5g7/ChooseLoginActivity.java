package com.example.esc_c5g7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooselogin);

        ImageButton user_login = findViewById(R.id.userlogin);
        ImageButton admin_login = findViewById(R.id.adminlogin);

        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseLoginActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
