package com.demo.app;

import com.simplemented.okdelay.DelayInterceptor;
import com.simplemented.okdelay.SimpleDelayProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

public class InterceptorListProvider {

  public static List<Interceptor> prod(SimpleDelayProvider provider) {
    try {
      List<Interceptor> list = new ArrayList<>();
      list.add(new DelayInterceptor(provider));
      list.add(httpLoggingInterceptor());
      return list;
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  private static HttpLoggingInterceptor httpLoggingInterceptor() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(Level.BASIC);
    return logging;
  }
}
