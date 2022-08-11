package com.example.ecs_c5g7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminHomeActivity extends AppCompatActivity {
    ImageView UserButton;
    ImageView SubmittedButton;
    ImageView PendingButton;
    ImageView VerifiedButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        UserButton = findViewById(R.id.usersList);
        SubmittedButton = findViewById(R.id.submittedList);
        PendingButton = findViewById(R.id.pendingList);
        VerifiedButton = findViewById(R.id.verifiedList);

        UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
            }
        });

        PendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PendingCustomersActivity.class));
            }
        });

        SubmittedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
            }
        });

        VerifiedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VerifiedCustomerActivity.class));
            }
        });
    }

}