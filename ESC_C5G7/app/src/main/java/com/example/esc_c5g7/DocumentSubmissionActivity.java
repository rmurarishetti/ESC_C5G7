package com.example.esc_c5g7;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DocumentSubmissionActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    Spinner dropdown, dropDown2;
    Button button_upload, button_upload2;
    TextView txtPath;
    String mediaPath = null;
    String uploadMediaUrl = null;
    String dropdownSelection1 = "Food";
    String dropdownSelection2 = null;
    Utils utils;

    private static final int IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdocumentsubmission);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        dropdown = findViewById(R.id.spinner1);
        button_upload = findViewById(R.id.button_upload);
        txtPath = findViewById(R.id.txtPath);
        button_upload2 = findViewById(R.id.button_upload2);
        dropDown2 = findViewById(R.id.spinner2);
        utils = new Utils(this);
        listner();

    }

    private void listner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.all_countries,
                        android.R.layout.simple_spinner_item);


        // Apply the adapter to the spinner
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                dropdownSelection1 = (String) parent.getItemAtPosition(position);
                ArrayAdapter<CharSequence> adapter = null;
                if (position==1){
                    adapter = ArrayAdapter
                            .createFromResource(getApplicationContext(), R.array.india_document_type,
                                    android.R.layout.simple_spinner_item);

                }
                else if (position==2){
                    adapter = ArrayAdapter
                            .createFromResource(getApplicationContext(), R.array.singapor_document_type,
                                    android.R.layout.simple_spinner_item);
                }
                if (adapter!=null){
                    dropDown2.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dropDown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                dropdownSelection2 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForReadStoragePermission();
            }
        });
        button_upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Still Under Development", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void checkForReadStoragePermission(){
        Dexter.withActivity(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport response) {
                        if (response.areAllPermissionsGranted()) {
                            Intent intent =new  Intent();
                            //sets the select file to all types of files
                            intent.setType("*/*");
                            //allows to select data and return it
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            //starts new activity to select file and return data
                            startActivityForResult(
                                    Intent.createChooser(intent, "Choose File to Upload.."),
                                    IMAGE_REQUEST
                            );
                        }
                        if (response.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                            showSettingsDialog();
                        }
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Need Permission");
        builder.setMessage("This app needs permission to select picture from gallery. You can grant it in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (data != null && requestCode == IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
                Uri capturedImage = data.getData();
                String selectedFilePath = FileUtils.getPath(capturedImage, this);
                Log.i(ContentValues.TAG, "Selected File Path:$selectedFilePath");
                if (selectedFilePath != null && selectedFilePath != "") {
                    mediaPath = selectedFilePath;

                } else {
                    Toast.makeText(
                            this,
                            "Cannot upload file to server",
                            Toast.LENGTH_SHORT
                    ).show();
                }
                txtPath.setText(mediaPath);

            } else {
                utils.showFailureToast("You haven't picked Image");

            }
        } catch (Exception e) {
            utils.showFailureToast(e.getMessage());
        }
    }


}
