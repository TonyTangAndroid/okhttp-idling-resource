package com.demo.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;
import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.simplemented.okdelay.DelayInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkhttpProvider {

  public static IdlingResource getResource() {
    return resource;
  }

  private static IdlingResource resource;

  @NonNull
  public static OkHttpClient get(App app) {
    OkHttpClient okHttpClient = new Builder()
        .build();
    resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient);
    return okHttpClient;
  }

  @NonNull
  static GitHubService gitHubService(OkHttpClient client) {
    return new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(GitHubService.class);
  }
}
