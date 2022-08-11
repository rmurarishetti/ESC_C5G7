package com.example.ecs_c5g7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;

import static java.util.regex.Pattern.matches;

import android.content.Intent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DocumentSubmissionTest {

    @Rule
    public IntentsTestRule<UserMainActivity> intentsTestRule =
            new IntentsTestRule<>(UserMainActivity.class);
    @Test
    public void CheckSubmissionActivity(){
        onView(withId(R.id.spinner1)).perform(click());

        onView(withId(R.id.button_upload)).perform(click());
        intending(hasPackage("com.android.files"));
    }

}
