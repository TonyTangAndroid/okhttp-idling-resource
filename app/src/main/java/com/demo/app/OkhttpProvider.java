package com.demo.app;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.simplemented.okdelay.DelayInterceptor;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkhttpProvider   {

  @NonNull
  public static OkHttpClient get(App app) {
    return new OkHttpClient.Builder()
        .addInterceptor(new DelayInterceptor(app.delayProvider()))
        .build();
  }
}
