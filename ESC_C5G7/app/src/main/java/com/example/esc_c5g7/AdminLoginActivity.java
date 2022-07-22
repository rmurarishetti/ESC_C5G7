package com.example.esc_c5g7;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {
    EditText admin_email;
    EditText admin_password;
    Button admin_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        admin_email = findViewById(R.id.et_admin_email);
        admin_password = findViewById(R.id.et_admin_password);
        admin_login_btn = findViewById(R.id.admin_button_login);
        admin_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    public void signIn(){
        String adminEmail = admin_email.getText().toString().trim();
        String adminPassword = admin_password.getText().toString().trim();

        if(TextUtils.isEmpty(adminEmail)||TextUtils.isEmpty(adminPassword)){
            Toast.makeText(AdminLoginActivity.this, "Either Password or Username is empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.equals(adminEmail,"admin@admin.com")&&TextUtils.equals(adminPassword,"admin123")){
            startActivity(new Intent(AdminLoginActivity.this, AdminHomeActivity.class));
        }
    }
}
