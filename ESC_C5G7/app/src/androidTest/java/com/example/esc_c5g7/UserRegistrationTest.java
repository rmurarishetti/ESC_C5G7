package com.example.esc_c5g7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserRegistrationTest {
    @Rule
    public ActivityScenarioRule<RegisterActivity> activityRule =
            new ActivityScenarioRule<>(RegisterActivity.class);

    @Test
    public void checkActivity() {
        onView(withId(R.id.et_name)).perform(typeText("Yogesh"));
        onView(withId(R.id.et_password)).perform(typeText("hello123"));
        onView(withId(R.id.et_email)).perform(typeText("yogesh.shelgaonkar@gmail.com"));
        onView(withId(R.id.et_repassword)).perform(typeText("hello123"));
        onView(withId(R.id.button_register)).perform(click());


    }
}
