package com.demo.app;

import android.app.Application;
import com.simplemented.okdelay.DelayInterceptor;
import com.simplemented.okdelay.SimpleDelayProvider;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

public class App extends Application implements OkhttpProvider {

  private OkHttpClient okHttpClient;
  private SimpleDelayProvider provider;

  @Override
  public void onCreate() {
    super.onCreate();
    provider = new SimpleDelayProvider(0, TimeUnit.MILLISECONDS);
    init();
  }

  private void init() {
    okHttpClient = new Builder().addInterceptor(new DelayInterceptor(provider)).build();
  }

  public SimpleDelayProvider delayProvider() {
    return provider;
  }

  @Override
  public OkHttpClient okhttpClientInstance() {
    return okHttpClient;
  }
}
