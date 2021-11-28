package com.demo.app;

import android.text.Html;
import android.text.Spanned;
import androidx.annotation.NonNull;
import com.simplemented.okdelay.DelayInterceptor;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mapper {


  public static Spanned asSpan(List<RepoEntity> response, String elapsedTime) {

    final StringBuilder builder = new StringBuilder(elapsedTime);
    for (final RepoEntity repoEntity : response) {
      builder.append(
          String.format(
              "<br/><br/><a href=\"%s\">%s</a>",
              repoEntity.getUrl(), repoEntity.getName()));
    }

    return Html.fromHtml(builder.toString());
  }
}
