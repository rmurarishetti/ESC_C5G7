package com.example.ecs_c5g7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChooseUserLoginActivityTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(ChooseLoginActivity.class);

    @Test
    public void UserLoginTest(){
        onView(withId(R.id.userlogin)).perform(click());

    }

}