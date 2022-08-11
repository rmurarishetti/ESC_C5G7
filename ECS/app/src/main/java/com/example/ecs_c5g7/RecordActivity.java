package com.example.ecs_c5g7;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecs_c5g7.adapter.RecordAdapter;
import com.example.ecs_c5g7.model.PostModel;
import com.example.ecs_c5g7.utils.SessionManager;
import com.example.ecs_c5g7.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity {
    Utils utils;
    RecyclerView recycler;
    TextView txt_no_record;
    ArrayList<PostModel> arrayList;
    RecordAdapter adapter;
    String userId="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        userId=getIntent().getStringExtra("userId");
        init();
        initRecycler();

    }


    private void initRecycler() {
        arrayList = new ArrayList<>();
        adapter = new RecordAdapter(this, new ArrayList<>());
        adapter.setHasStableIds(true);

        recycler.setAdapter(adapter);
        getData();
    }

    private void init() {
        recycler = findViewById(R.id.recyclerView);
        txt_no_record = findViewById(R.id.txt_no_record);
        utils = new Utils(this);


    }


    private void getData() {
        utils.showProgressBar();
        FirebaseDatabase.getInstance().getReference().child(userId).child("docs")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        utils.hideProgressBar();
                        arrayList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                    if (dataSnapshot2.exists()) {
                                        PostModel model = new PostModel();
                                        model = dataSnapshot2.getValue(PostModel.class);
                                        arrayList.add(model);
                                    }
                                }
                            }
                        }
                        if (adapter != null) {
                            if (arrayList.size() < 1) {
                                txt_no_record.setVisibility(View.VISIBLE);
                                recycler.setVisibility(View.GONE);
                            } else {
                                txt_no_record.setVisibility(View.GONE);
                                recycler.setVisibility(View.VISIBLE);
                                adapter.setData(arrayList);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.withdraw:
                // do something
                showPopup();
                return true;

            case R.id.logout:
                //do something
                FirebaseAuth.getInstance().signOut();
                new SessionManager(this).setLogin(false);

                new Utils(this).showSuccessToast("Logout Successful");
                startActivity(new Intent(RecordActivity.this, ChooseLoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showPopup() {
        Dialog dialog = new Dialog(this, R.style.AlertDialogTheme);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.request_withdraw_dialog);
        Button dialogButton = dialog.findViewById(R.id.btn_send);
        EditText edtAccountDetail = dialog.findViewById(R.id.edtAccountDetail);
        EditText edtAmount = dialog.findViewById(R.id.edtAmount);
        ImageView imgCross = dialog.findViewById(R.id.imgCross);
        int count = arrayList.stream().mapToInt(PostModel::getPrice).sum();
        edtAmount.setText("" + count);


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtAccountDetail.getText().toString().trim().isEmpty()) {

                    utils.showFailureToast("Pleas enter account detail");
                    return;
                } else {
                    for (int i = 0; i < arrayList.size(); i++) {
                        FirebaseDatabase.getInstance().getReference().
                                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                child("docs").child(arrayList.get(i).getCountry()).child(arrayList.get(i).getCatagory())
                                .child(arrayList.get(i).getPushKey()).child("price").setValue(0);
                    }
                    utils.showSuccessToast("Withdraw successfully");
                    dialog.dismiss();
                }
            }
        });
        imgCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}