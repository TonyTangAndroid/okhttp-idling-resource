package com.demo.app;

import androidx.annotation.NonNull;
import androidx.test.espresso.IdlingResource;
import com.jakewharton.espresso.OkHttp3IdlingResource;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkhttpProvider {

  private static OkHttpClient okHttpClient;

  public static IdlingResource getResource() {
    if (resource == null) {
      init();
    }
    return resource;
  }

  private static IdlingResource resource;

  @NonNull
  public static OkHttpClient get() {
    if (okHttpClient != null) {
      return okHttpClient;
    }
    init();
    return okHttpClient;
  }

  private static void init() {
    okHttpClient = new Builder().build();
    resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient);
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
