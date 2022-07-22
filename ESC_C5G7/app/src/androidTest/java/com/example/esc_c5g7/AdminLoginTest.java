package com.example.esc_c5g7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasPackage;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;


import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AdminLoginTest {
    @Rule
    public IntentsTestRule<AdminLoginActivity> AdminActivityRule = new IntentsTestRule<>(
            AdminLoginActivity.class);
    @Test
    public void checkAdminActivity() {
        onView(withId(R.id.et_admin_email)).perform(typeText("admin@admin.com"));
        onView(withId(R.id.et_admin_password)).perform(typeText("admin123"));

        intending(hasPackage("com.example.esc_c5g7"));
    }

}
