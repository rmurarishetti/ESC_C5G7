package com.example.esc_c5g7;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DocumentSubmissionActivity extends AppCompatActivity {
    TextView headerText;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdocumentsubmission);

        headerText = findViewById(R.id.submissionHeader);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        final Handler handler = new Handler(Looper.getMainLooper());
//
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                final String userName = (String) firestore.collection("Particulars").document(mAuth.getCurrentUser().getUid()).get().getResult().get("name");
//                handler.post(new Runnable() {
//                         @Override
//                         public void run() {
//                             if (userName!=null){
//                             String welcomeText = "Hi "+userName+"! Please select your country and upload the necessary documents.";
//                             headerText.setText(welcomeText);}
//                         }
//                     }
//                );
//            }
//        });
    }

}
