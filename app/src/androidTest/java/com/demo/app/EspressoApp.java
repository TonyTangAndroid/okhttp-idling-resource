package com.demo.app;

import androidx.annotation.NonNull;
import androidx.test.espresso.IdlingRegistry;
import com.jakewharton.espresso.OkHttp3IdlingResource;
import java.util.List;
import okhttp3.Interceptor;

public class EspressoApp extends App {

  @Override
  public void onCreate() {
    super.onCreate();
    // Manually register network callback.
    IdlingRegistry.getInstance()
        .register(OkHttp3IdlingResource.create("OkHttp", okhttpClientInstance()));
  }

  @NonNull
  public List<Interceptor> interceptors() {
    return MockInterceptorListProvider.mock(delayProvider(), this);
  }
}
