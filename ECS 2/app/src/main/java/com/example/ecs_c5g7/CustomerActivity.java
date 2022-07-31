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

import com.example.ecs_c5g7.adapter.CustomerAdapter;
import com.example.ecs_c5g7.adapter.RecordAdapter;
import com.example.ecs_c5g7.model.PostModel;
import com.example.ecs_c5g7.model.UserModel;
import com.example.ecs_c5g7.utils.SessionManager;
import com.example.ecs_c5g7.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {
    Utils utils;
    RecyclerView recycler;
    TextView txt_no_record;
    ArrayList<UserModel> arrayList;
    CustomerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        init();
        initRecycler();

    }


    private void initRecycler() {
        arrayList = new ArrayList<>();
        adapter = new CustomerAdapter(this, new ArrayList<>());
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
        FirebaseDatabase.getInstance().getReference().child("users")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        utils.hideProgressBar();
                        arrayList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                   if (dataSnapshot.exists()) {
                                        UserModel model = new UserModel();
                                        model = dataSnapshot.getValue(UserModel.class);
                                        arrayList.add(model);
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

        menu.findItem(R.id.withdraw).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.logout:
                //do something
                new SessionManager(this).setLogin(false);

                new Utils(this).showSuccessToast("Logout Successful");
                startActivity(new Intent(CustomerActivity.this, ChooseLoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}