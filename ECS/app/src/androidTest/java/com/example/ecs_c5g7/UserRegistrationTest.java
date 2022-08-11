package com.example.ecs_c5g7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
        //check everything is displayed
        onView(withId(R.id.tv_reg)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_regdetails)).check(matches(isDisplayed()));
        onView(withId(R.id.et_name)).check(matches(isDisplayed()));
        onView(withId(R.id.et_email)).check(matches(isDisplayed()));
        onView(withId(R.id.et_password)).check(matches(isDisplayed()));
        onView(withId(R.id.et_repassword)).check(matches(isDisplayed()));
        onView(withId(R.id.button_register)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_alreadyReg)).check(matches(isDisplayed()));

        //registering user
        onView(withId(R.id.et_name)).perform(typeText("Yogesh"));
        onView(withId(R.id.et_password)).perform(typeText("hello123"));
        onView(withId(R.id.et_email)).perform(typeText("yogesh.shelgaonkar@gmail.com"));
        onView(withId(R.id.et_repassword)).perform(typeText("hello123"));
        onView(withId(R.id.button_register)).perform(click());
        onView(withId(R.id.tv_alreadyReg)).perform(click());
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));

    }
}
