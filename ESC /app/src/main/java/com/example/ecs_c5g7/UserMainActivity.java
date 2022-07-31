package com.example.ecs_c5g7;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.ecs_c5g7.utils.FileUtils;
import com.example.ecs_c5g7.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
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
import java.util.Random;

public class UserMainActivity extends AppCompatActivity {
    Spinner dropdown, dropDown2;
    Button button_upload, button_upload2;
    TextView txtPath;
    String mediaPath = null;
    String fileName = null;
    String uploadMediaUrl = null;
    String dropdownSelection1 = "";
    String dropdownSelection2 = null;
    Utils utils;
    private static final int IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        init();
    }
    private void init() {
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
                validateTheInputData();
            }
        });

    }

    private void checkForReadStoragePermission() {
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
                    fileName = selectedFilePath.substring(selectedFilePath.lastIndexOf("/")+1);

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

    private void validateTheInputData() {
        Date currentTime = Calendar.getInstance().getTime();

        if (dropdownSelection1.equalsIgnoreCase("Select Country")){
            utils.showSuccessToast("Please select country");
            return;
        }
        else if (dropdownSelection2.equalsIgnoreCase("Please select document type")){
            utils.showSuccessToast("Please select document type");
            return;
        }
        else if (mediaPath ==null) {
            utils.showSuccessToast("Please select document");
            return;
        }
        else {
            utils.showProgressBar();
            //utils.showProgressBar();
            FirebaseStorage.getInstance().getReference().child("docs")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(String.valueOf(currentTime))
                    .putFile(Uri.fromFile(new File(mediaPath)))
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                            if (task.isSuccessful()) {
                                FirebaseStorage.getInstance().getReference().child("docs")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .child(String.valueOf(currentTime)).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                utils.hideProgressBar();
                                                if (task.isSuccessful()) {
                                                    Uri tempUri = task.getResult();
                                                    uploadMediaUrl = tempUri.toString();
                                                    saveComplaintToDatabase(currentTime);
                                                } else {
                                                    utils.hideProgressBar();
                                                    utils.showFailureToast(task.getException().getMessage());
                                                }
                                            }
                                        });
                            } else {
                                utils.hideProgressBar();
                                utils.showFailureToast(task.getException().getMessage());
                            }
                        }

                    });
        }
    }


    private void saveComplaintToDatabase(Date currentTime) {
        final int min = 100;
        final int max = 8000;
        final int random = new Random().nextInt((max - min) + 1) + min;
        HashMap hashMap = new HashMap();
        hashMap.put("docId", "01" + random);
        hashMap.put("postImage", uploadMediaUrl);
        hashMap.put("postTime", currentTime.toString());
        hashMap.put("catagory", dropdownSelection2);
        hashMap.put("price", 0);
        hashMap.put("status", 0);
        String pushKey = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("docs").push().getKey();
        hashMap.put("pushKey", pushKey);
        hashMap.put("fileName", fileName);
        hashMap.put("country", dropdownSelection1);
        utils.showProgressBar();
        FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("docs").child(dropdownSelection1).child(dropdownSelection2)
                .child(pushKey)
                .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        utils.hideProgressBar();
                        if (task.isSuccessful()) {
                            // utils.hideProgressBar();
                            dropdown.setSelection(0);
                            dropDown2.setSelection(0);
                            txtPath.setText("");
                            utils.showSuccessToast("Document Upload Successfuly");

                        } else utils.showFailureToast(task.getException().getMessage());
                    }
                });
    }


}