package com.demo.app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

  @Rule
  public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void basicTextCheck() {
    ViewInteraction totalViewCount = onView(withText("CALL REQUEST"));
    totalViewCount.check(matches(isDisplayed()));

    ViewInteraction absentViewCount = onView(withText("total:151"));
    absentViewCount.check(doesNotExist());
  }

}
