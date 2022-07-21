package com.example.esc_c5g7;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserParticularsActivity extends AppCompatActivity {
    Button submitBtn;
    EditText user_name;
    EditText user_email;
    EditText user_number;
    EditText user_country;
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userparticulars);

        submitBtn = findViewById(R.id.submit_particulars_btn);
        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_number = findViewById(R.id.user_number);
        user_country = findViewById(R.id.user_country);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitParticulars();
            }
        });
    }

    private void submitParticulars() {
        String userName = user_name.getText().toString().trim();
        String userEmail = user_email.getText().toString().trim();
        String userNumber = user_number.getText().toString().trim();
        String userCountry = user_country.getText().toString().trim();

        if(TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty email
            return;
        }

        if(TextUtils.isEmpty(userName))
        {
            Toast.makeText(this,"Enter Name",Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty email
            return;
        }

        if(TextUtils.isEmpty(userNumber))
        {
            Toast.makeText(this,"Enter Phone Number",Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty email
            return;
        }

        if(TextUtils.isEmpty(userCountry))
        {
            Toast.makeText(this,"Enter a Country",Toast.LENGTH_SHORT).show();
            //displays toast if user enters empty email
            return;
        }

        Map<String, Object> user = new HashMap<>();
        user.put("name", userName);
        user.put("country", userCountry);
        user.put("email", userEmail);
        user.put("phone", userNumber);

        firestore.collection("Particulars").document(mAuth.getCurrentUser().getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(UserParticularsActivity.this, "Particulars Registered Sucessfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserParticularsActivity.this, DocumentSubmissionActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserParticularsActivity.this, "Particulars Uplaod Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
