package com.demo.app;

import android.text.Html;
import android.text.Spanned;
import java.util.List;

public class Mapper {

  public static Spanned asSpan(List<RepoEntity> response, String elapsedTime) {

    final StringBuilder builder = new StringBuilder(elapsedTime);
    for (final RepoEntity repoEntity : response) {
      builder.append(
          String.format(
              "<br/><br/><a href=\"%s\">%s</a>", repoEntity.getUrl(), repoEntity.getName()));
    }

    return Html.fromHtml(builder.toString());
  }
}
