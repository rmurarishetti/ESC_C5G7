package com.example.ecs_c5g7;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

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

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserLoginTest {
    String realusername ="user";
    String realpassword ="123456";



    @Test
    public void Test1(){
        //Input: Username: Null; Password: 123456;
        //Output:Enter Username
        String U_sername = "";
        String P_assword = "123456";
        assertTrue(TextUtils.isEmpty(U_sername));

    }

    @Test
    public void Test2(){
        //Input: Username: user; Password: Null;
        //Output:Enter Password
        String U_sername = "user";
        String P_assword = "Null";
        assertTrue(TextUtils.isEmpty(P_assword));
    }
    @Test
    public void Test3(){
        //Input: Username: Null; Password: Null;
        //Output:Enter Username
        String U_sername = "";
        String P_assword = "";
        assertTrue(TextUtils.isEmpty(U_sername));
    }
    @Test
    public void Test4(){
        //Input: Username: user; Password: 123456;
        //Output:Login success
        String U_sername = "user";
        String P_assword = "123456";
        assertFalse(TextUtils.isEmpty(U_sername));
        assertFalse(TextUtils.isEmpty(P_assword));
        assertSame(U_sername,realusername);
        assertSame(P_assword,realpassword);
    }
    @Test
    public void Test5(){
        //Input: Username: user; Password: 1234567;
        //Output:Login Failure username and password incorrect
        String U_sername = "user";
        String P_assword = "1234567";
        assertNotSame(P_assword,realpassword);
    }
}