package com.demo.app;

import android.app.Application;
import com.simplemented.okdelay.DelayInterceptor;

public class App extends Application implements DelayProvider, DelayInterceptor.DelayProvider {

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
  public long getDelay() {
    return delayInMilliseconds;
  }
}
