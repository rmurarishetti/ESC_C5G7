package com.example.ecs_c5g7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserLoginTest {
    @Rule
    public IntentsTestRule<UserLoginActivity> mActivityRule = new IntentsTestRule<>(
            UserLoginActivity.class);
    @Test
    public void checkUserActivity() {
        onView(withId(R.id.et_user_email)).perform(typeText("rmurarishetti@gmail.com"));
        onView(withId(R.id.et_user_password)).perform(typeText("hello123"));
        onView(withId(R.id.user_button_login)).perform(click());

        intending(hasPackage("com.example.ecs_c5g7"));
    }
}
