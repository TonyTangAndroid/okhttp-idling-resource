package com.demo.app;

import static okhttp3.mock.MediaTypes.MEDIATYPE_JSON;

import android.content.Context;
import com.simplemented.okdelay.DelayInterceptor;
import com.simplemented.okdelay.SimpleDelayProvider;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.MockInterceptor;

public class MockInterceptorListProvider {

  public static List<Interceptor> mock(SimpleDelayProvider provider, Context context) {
    try {
      return Arrays.asList(new DelayInterceptor(provider), getMockInterceptor(context));
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  private static MockInterceptor getMockInterceptor(Context context) throws Exception {
    MockInterceptor interceptor = new MockInterceptor();
    interceptor
        .addRule()
        .get("https://api.github.com/users/pawel-schmidt/repos?sort=updated")
        .respond(MockHelper.string(context, "raw.json"), MEDIATYPE_JSON);
    return interceptor;
  }

  private static HttpLoggingInterceptor httpLoggingInterceptor() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(Level.BASIC);
    return logging;
  }
}
