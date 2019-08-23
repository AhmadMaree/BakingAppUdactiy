package com.example.bakingappudactiy;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class RecViewTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =new ActivityTestRule<>(MainActivity.class);

    @Test
    public void positiontest() {
        Espresso.onView(ViewMatchers.withId(R.id.rec_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
    }









}
