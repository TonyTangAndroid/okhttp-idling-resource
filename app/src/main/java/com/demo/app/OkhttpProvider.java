package com.demo.app;

import androidx.annotation.NonNull;
import com.simplemented.okdelay.DelayInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkhttpProvider {

  @NonNull
  public static OkHttpClient get(App app) {
    return new OkHttpClient.Builder()
        .addInterceptor(new DelayInterceptor(app.delayProvider()))
        .build();
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
