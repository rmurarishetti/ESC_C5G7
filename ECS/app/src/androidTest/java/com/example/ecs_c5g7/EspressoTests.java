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

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTests {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void checkWholeUI() throws InterruptedException {
        // main page
        onView(withId(R.id.paypal)).check(matches(isDisplayed()));
        onView(withId(R.id.welcomeText)).check(matches(isDisplayed()));
        onView(withId(R.id.paypal)).perform(click());

        // choose login activity
        onView(withId(R.id.userlogin)).perform(click());

        // go to registration page
        onView(withId(R.id.tv_notReg)).perform(click());

        // register new user
        onView(withId(R.id.et_name)).perform(typeText("Samuel shark"));
        onView(withId(R.id.et_email)).perform(typeText("blueberry126@gmail.com"));
        onView(withId(R.id.et_password)).perform(typeText("test123"));
        onView(withId(R.id.et_repassword)).perform(typeText("test123"));
        onView(withId(R.id.button_register)).perform(click());

//        //Particulars
        Thread thread = new Thread();
        Thread.sleep(2000);
        onView(withId(R.id.user_name)).perform(typeText("Samuel Shark"));
        onView(withId(R.id.user_number)).perform(typeText("+6593487429"));
        onView(withId(R.id.user_country)).perform(typeText("Singapore"));
        onView(withId(R.id.user_email)).perform(typeText("blueberry125@gmail.com"));
        onView(withId(R.id.submit_particulars_btn)).perform(click());

        //Show User Home
        Thread.sleep(1000);
        onView(withId(R.id.button_upload3)).perform(click());
        onView(withId(R.id.spinner1)).check(matches(isDisplayed()));


    }


}
