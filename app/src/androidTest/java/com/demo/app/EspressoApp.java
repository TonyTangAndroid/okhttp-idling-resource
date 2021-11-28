package com.demo.app;

import androidx.test.espresso.IdlingRegistry;
import com.jakewharton.espresso.OkHttp3IdlingResource;

public class EspressoApp extends App {

  @Override
  public void onCreate() {
    super.onCreate();
    // Manually register network callback.
    IdlingRegistry.getInstance()
        .register(OkHttp3IdlingResource.create("OkHttp", okhttpClientInstance()));
  }
}
