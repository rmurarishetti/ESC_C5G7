package com.example.esc_c5g7;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private EditText repassword;
    private TextView backToLogin;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        register = (Button)findViewById(R.id.button_register);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText)findViewById(R.id.et_password);
        repassword = (EditText) findViewById(R.id.et_repassword);
        backToLogin = findViewById(R.id.tv_alreadyReg);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(RegisterActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });


    }

    private void createAccount(){

        String E_mail= email.getText().toString().trim();
        String P_assword = password.getText().toString().trim();
        String Re_password = repassword.getText().toString().trim();

        if(TextUtils.isEmpty(E_mail))
        {
            Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty email
            return;
        }
        if(TextUtils.isEmpty(P_assword))
        {
            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty password
            return;
        }
        if(P_assword.length()<6){
            Toast.makeText(this,"Password must be minimum 6 characters",Toast.LENGTH_SHORT).show();
            //displays toast if password length is less than 6 characters
        }
        if(TextUtils.isEmpty(Re_password))
        {
            Toast.makeText(this,"Enter Re-password",Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty repassword
            return;
        }
        if(!P_assword.equals(Re_password))
        {
            Toast.makeText(this,"Password Does Not Match",Toast.LENGTH_SHORT).show();
            //displays toast if password is not equal to repassword

            return;
        }

        mAuth.createUserWithEmailAndPassword(E_mail,P_assword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    //use the firebase createUserWithEmailandPassword function to register account
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Register Success",Toast.LENGTH_SHORT).show();
                            //if registration is successful

                        }else {
                            Toast.makeText(RegisterActivity.this,"Register Failure",Toast.LENGTH_SHORT).show();
                            //if registration is unsuccessful
                        }
                    }

                });


    }
}
