package com.example.ecs_c5g7;

import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AdminLoginTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<> (AdminLoginActivity.class);
    @Test
    public void checkAdminActivity() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.et_admin_email)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_details)).check(matches(isDisplayed()));


        onView(withId(R.id.et_admin_email)).perform(typeText("admin"));
        onView(withId(R.id.et_admin_password)).perform(typeText("admin123"));
        onView(withId(R.id.admin_button_login)).perform(click());
    }



}
