package com.example.ecs_c5g7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ecs_c5g7.adapter.CustomerAdapter;
import com.example.ecs_c5g7.model.PostModel;
import com.example.ecs_c5g7.model.UserModel;
import com.example.ecs_c5g7.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VerifiedCustomerActivity extends AppCompatActivity {
    Utils utils;
    RecyclerView recycler;
    TextView txt_no_record;
    ArrayList<UserModel> userModelArrayList;
    ArrayList<UserModel> verifiedUserArrayList = new ArrayList<>();
    ArrayList<PostModel> postModelArrayList = new ArrayList<>();
    CustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified_customer);

        init();
        initRecycler();
    }

    private void init() {
        recycler = findViewById(R.id.recyclerView);
        txt_no_record = findViewById(R.id.txt_no_record);
        utils = new Utils(this);
    }

    private void initRecycler() {
        userModelArrayList = new ArrayList<>();
        adapter = new CustomerAdapter(this, new ArrayList<>());
        adapter.setHasStableIds(true);

        recycler.setAdapter(adapter);
        getData();
    }

    private void getData() {
        utils.showProgressBar();
        FirebaseDatabase.getInstance().getReference().child("users")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userModelArrayList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (dataSnapshot.exists()) {
                                UserModel model = new UserModel();
                                model = dataSnapshot.getValue(UserModel.class);
                                userModelArrayList.add(model);
                            }
                        }
                        getDocumentDetails();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void getDocumentDetails() {
        for (int i = 0; i < userModelArrayList.size(); i++) {
            int finalI = i;
            FirebaseDatabase.getInstance().getReference().child(userModelArrayList.get(i).getUserId()).child("docs")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            postModelArrayList.clear();
                            Boolean tempIsPending = false;
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                        if (dataSnapshot2.exists()) {
                                            PostModel postModel = new PostModel();
                                            postModel = dataSnapshot2.getValue(PostModel.class);
                                            if (postModel.getStatus() == 1)
                                                tempIsPending = true;
                                        }
                                    }
                                }
                            }
                            if (tempIsPending) {
                                verifiedUserArrayList.add(userModelArrayList.get(finalI));
                            }
                            if (finalI == userModelArrayList.size() - 1){
                                updateUI();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }

    private void updateUI(){
        utils.hideProgressBar();
        if (adapter != null) {
            if (verifiedUserArrayList.size() < 1) {
                txt_no_record.setVisibility(View.VISIBLE);
                recycler.setVisibility(View.GONE);
            } else {
                txt_no_record.setVisibility(View.GONE);
                recycler.setVisibility(View.VISIBLE);
                adapter.setData(verifiedUserArrayList);
            }
        }
    }


}