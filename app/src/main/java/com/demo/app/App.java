package com.demo.app;

import android.app.Application;

public class App extends Application implements DelayProvider {

  private long delayInMilliseconds = 0L;

  @Override
  public void onCreate() {
    super.onCreate();

  }

  @Override
  public void set(long delayInMilliseconds) {
    this.delayInMilliseconds = delayInMilliseconds;
  }

  @Override
  public long get() {
    return delayInMilliseconds;
  }
}
