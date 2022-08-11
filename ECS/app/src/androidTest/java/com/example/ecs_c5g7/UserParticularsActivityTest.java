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
public class UserParticularsActivityTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(UserParticularsActivity.class);

    @Test
    public void enterParticulars(){
        //ensure page is displayed as expected
        onView(withId(R.id.particularsDisplayText)).check(matches(isDisplayed()));
        onView(withId(R.id.user_name)).check(matches(isDisplayed()));
        onView(withId(R.id.user_number)).check(matches(isDisplayed()));
        onView(withId(R.id.user_country)).check(matches(isDisplayed()));
        onView(withId(R.id.user_email)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_particulars_btn)).check(matches(isDisplayed()));



        //Test UI of particulars page
        onView(withId(R.id.user_name)).perform(typeText("Acqquilaa Bathumalai"));
        onView(withId(R.id.user_number)).perform(typeText("+6592376476"));
        onView(withId(R.id.user_country)).perform(typeText("Singapore"));
        onView(withId(R.id.user_email)).perform(typeText("acqquilaa18@gmail.com"));
        onView(withId(R.id.submit_particulars_btn)).perform(click());
    }

}