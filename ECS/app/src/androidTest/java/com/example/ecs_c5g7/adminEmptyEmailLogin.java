package com.example.ecs_c5g7;

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

public class adminEmptyEmailLogin {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(AdminLoginActivity.class);
    @Test
    public void emptyAdminEmail() {
        onView(withId(R.id.et_admin_password)).perform(typeText("admin"));
        onView(withId(R.id.button_register)).perform(click());
    }
}
