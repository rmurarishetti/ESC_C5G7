package com.example.ecs_c5g7;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecs_c5g7.utils.SessionManager;
import com.example.ecs_c5g7.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {
    private Button login;
    private EditText email;
    private EditText password;
    private FirebaseAuth firebaseAuth;
    private TextView registerText;
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        utils = new Utils(this);
        firebaseAuth = FirebaseAuth.getInstance();
        login = (Button) findViewById(R.id.admin_button_login);
        email = (EditText) findViewById(R.id.et_admin_email);
        password = (EditText) findViewById(R.id.et_admin_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == login) {
                    signIn();         //when the "login" button is clicked
                }
            }
        });


    }

    private void signIn() {
        String U_sername = email.getText().toString().trim();    //fetches email and password from the editTexts on Login Page
        String P_assword = password.getText().toString().trim();

        if (TextUtils.isEmpty(U_sername)) {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();   //displays error if user enters empty username
            return;
        }
        if (TextUtils.isEmpty(P_assword)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();   //displays error if user enters empty password
            return;
        }
        utils.showProgressBar();
        if (U_sername.equals("admin")&&P_assword.equals("admin")){
            utils.hideProgressBar();
            Toast.makeText(AdminLoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
            finish();
        }
        else {
            utils.hideProgressBar();

            Toast.makeText(AdminLoginActivity.this, "Login Failure username and password incorrect", Toast.LENGTH_SHORT).show();
        }

    }
}
