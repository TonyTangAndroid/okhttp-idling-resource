package com.demo.app;

import okhttp3.OkHttpClient;

public interface OkhttpProvider {

  OkHttpClient okhttpClientInstance();
}
