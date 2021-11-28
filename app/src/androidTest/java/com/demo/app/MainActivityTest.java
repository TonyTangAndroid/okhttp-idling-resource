package com.demo.app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
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

    //by default, it is idle
    ViewInteraction state = onView(withText("Idle"));
    state.check(matches(isDisplayed()));


    onView(withText("CALL REQUEST")).perform(ViewActions.click());

    //when the button is clicked, it is loading
    ViewInteraction loading = onView(withText("Loading"));
    loading.check(matches(isDisplayed()));

    IdlingRegistry.getInstance().register(OkhttpProvider.getResource());

    //after 6 seconds, it is success
    ViewInteraction success = onView(withText("Success"));
    success.check(matches(isDisplayed()));

  }

}
