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
public class emptyNameParticulars {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(UserParticularsActivity.class);

    @Test
    public void emptyNamePart(){
        onView(withId(R.id.user_name)).perform(typeText(""));
        onView(withId(R.id.user_number)).perform(typeText("+6592376476"));
        onView(withId(R.id.user_country)).perform(typeText("Singapore"));
        onView(withId(R.id.user_email)).perform(typeText("acqquilaa18@gmail.com"));
        onView(withId(R.id.submit_particulars_btn)).perform(click());
    }

}
