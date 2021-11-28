package com.demo.app;

import android.app.Application;
import com.simplemented.okdelay.SimpleDelayProvider;
import java.util.concurrent.TimeUnit;

public class App extends Application {


  private SimpleDelayProvider provider;

  @Override
  public void onCreate() {
    super.onCreate();
    provider = new SimpleDelayProvider(0, TimeUnit.MILLISECONDS);
  }

  public SimpleDelayProvider delayProvider() {
    return provider;
  }
}
