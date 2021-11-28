package com.demo.app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertContains;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn;

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

    ViewInteraction state = onView(withText("Idle"));
    state.check(matches(isDisplayed()));

    ViewInteraction absentViewCount = onView(withText("random_text"));
    absentViewCount.check(doesNotExist());
  }

  @Test
  public void whenCallRequestIsClickedAndNetworkReturned_shouldShowResult() {

    // by default, it is idle
    assertDisplayed("Idle");

    clickOn(R.id.activity_main_call_request);

    // With IdlingRegistry applied, we will never check the intermediate state.
    assertNotExist("Loading");
    assertDisplayed("Success");
    assertContains("Request took");
  }
}
