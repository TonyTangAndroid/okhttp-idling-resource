package com.demo.app;

import android.app.Application;
import androidx.annotation.NonNull;
import com.simplemented.okdelay.SimpleDelayProvider;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
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
    Builder builder = new Builder();
    for (Interceptor interceptor : interceptors()) {
      builder.addInterceptor(interceptor);
    }
    okHttpClient = builder.build();
  }

  @NonNull
  public List<Interceptor> interceptors() {
    return InterceptorListProvider.prod(provider);
  }

  public SimpleDelayProvider delayProvider() {
    return provider;
  }

  @Override
  public OkHttpClient okhttpClientInstance() {
    return okHttpClient;
  }
}
