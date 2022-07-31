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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private FirebaseAuth mAuth;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText repassword;
    private TextView backToLogin;
    DatabaseReference databaseReference;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        utils = new Utils(this);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        register = (Button) findViewById(R.id.button_register);
        name = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        repassword = (EditText) findViewById(R.id.et_repassword);
        backToLogin = findViewById(R.id.tv_alreadyReg);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, UserLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });


    }

    private void createAccount() {

        String E_name = name.getText().toString().trim();
        String E_mail = email.getText().toString().trim();
        String P_assword = password.getText().toString().trim();
        String Re_password = repassword.getText().toString().trim();

        if (TextUtils.isEmpty(E_mail)) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty email
            return;
        }
        if (TextUtils.isEmpty(P_assword)) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty password
            return;
        }
        if (P_assword.length() < 6) {
            Toast.makeText(this, "Password must be minimum 6 characters", Toast.LENGTH_SHORT).show();
            //displays toast if password length is less than 6 characters
        }
        if (TextUtils.isEmpty(Re_password)) {
            Toast.makeText(this, "Enter Re-password", Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty repassword
            return;
        }
        if (!P_assword.equals(Re_password)) {
            Toast.makeText(this, "Password Does Not Match", Toast.LENGTH_SHORT).show();
            //displays toast if password is not equal to repassword

            return;
        }
        utils.showProgressBar();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(E_mail, P_assword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("email", E_mail);
                    hashMap.put("name", E_name);
                    hashMap.put("password", P_assword);
                    hashMap.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    FirebaseDatabase.getInstance().getReference().child("users")
                            .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                            .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    utils.hideProgressBar();
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                        new SessionManager(getApplicationContext()).setLogin(true);
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Error in saving data \n" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    utils.hideProgressBar();
                                    utils.showFailureToast(e.getMessage());
                                }
                            });

                } else {
                    utils.hideProgressBar();
                    Toast.makeText(RegisterActivity.this, "Register Failure" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    //if registration is unsuccessful
                }
            }

        });


    }
}
