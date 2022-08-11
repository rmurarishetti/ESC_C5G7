package com.example.ecs_c5g7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ecs_c5g7.utils.SessionManager;
import com.example.ecs_c5g7.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button button_upload = findViewById(R.id.button_upload3);
        Button button_Record = findViewById(R.id.button_Record);
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, UserMainActivity.class);
                startActivity(i);
            }
        });
        button_Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, RecordActivity.class);
                i.putExtra("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
                startActivity(i);
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
                return true;

            case R.id.logout:
                //do something
                FirebaseAuth.getInstance().signOut();
                new SessionManager(this).setLogin(false);

                new Utils(this).showSuccessToast("Logout Successful");
                startActivity(new Intent(HomeActivity.this, ChooseLoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
