package com.example.ecs_c5g7;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.*;
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

import com.example.ecs_c5g7.utils.SessionManager;
import com.example.ecs_c5g7.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class RegisterTest{
    String E_name = "aa";
    String E_mail = "13663@163.com";
    String P_assword = "123456";
    String Re_password = "123456";


    @Test
    public void Test1(){
        //Input =
        //Output =
        String E_mail ="";
        assertTrue(E_mail,TextUtils.isEmpty(E_mail));


    }
    @Test
    public void Test2(){
        //Input =
        //Output =
        String E_mail ="13663@163.com";
        assertFalse(TextUtils.isEmpty(E_mail));

    }
    @Test
    public void Test3(){
        //Input =
        //Output =
        String P_assword = "";
        assertTrue(TextUtils.isEmpty(P_assword));

    }
    @Test
    public void Test4(){
        //Input =
        //Output =
        String P_assword ="123456";
        assertFalse(TextUtils.isEmpty(P_assword));


    }
    @Test
    public void Test5(){
        //Input =
        //Output =
        String P_assword = "12345";
        assertTrue(P_assword.length()<6);
    }
    @Test
    public void Test6(){
        //Input =
        //Output =
        String P_assword = "12345";
        String Re_password = "12345";
        assertTrue(P_assword.length()<6);
    }
    @Test
    public void Test7(){
        //Input =
        //Output =
        String P_assword = "123456";
        String Re_password = "1234567";
        assertNotSame(P_assword,Re_password);
    }

}