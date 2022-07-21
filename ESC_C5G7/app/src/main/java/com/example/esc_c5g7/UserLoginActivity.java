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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserLoginActivity extends AppCompatActivity {
    private Button login;
    private EditText email;
    private EditText password;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        registerText = findViewById(R.id.tv_notReg);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        login = (Button) findViewById(R.id.user_button_login);
        email = (EditText)findViewById(R.id.et_user_email);
        password = (EditText)findViewById(R.id.et_user_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == login)
                {
                    signIn();                       //when the "login" button is clicked
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn(){
        String U_sername = email.getText().toString().trim();    //fetches email and password from the editTexts on Login Page
        String P_assword = password.getText().toString().trim();

        if(TextUtils.isEmpty(U_sername))
        {
            Toast.makeText(this,"Enter Username",Toast.LENGTH_SHORT).show();   //displays error if user enters empty username
            return;
        }
        if(TextUtils.isEmpty(P_assword))
        {
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();   //displays error if user enters empty password
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(U_sername, P_assword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //use firebase authentication to authenticate the entered email and password

                        if (task.isSuccessful()) {
                            Toast.makeText(UserLoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), UserParticularsActivity.class));
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //startActivity(new Intent(getApplicationContext(), DocumentSubmissionActivity.class));
                            //if login successful, goes to Homepage
                        } else {
                            Toast.makeText(UserLoginActivity.this, "Login Failure", Toast.LENGTH_SHORT).show();
                            //displays toast if login unsuccessful
                        }

                    }});


    }
}
