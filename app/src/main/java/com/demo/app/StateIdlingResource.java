
package com.demo.app;

import androidx.test.espresso.IdlingResource;

public final class StateIdlingResource implements IdlingResource {

  volatile ResourceCallback callback;
  private boolean idle = false;


  @Override
  public String getName() {
    return "manual";
  }

  @Override
  public boolean isIdleNow() {
    return idle;
  }

  public void markAsIdle() {
    this.idle = true;
    if (callback != null) {
      callback.onTransitionToIdle();
    }
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback callback) {
    this.callback = callback;
  }
}
