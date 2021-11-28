package com.demo.app;

import com.google.gson.annotations.SerializedName;

public class RepoEntity {

  private final String name;

  @SerializedName("html_url")
  private final String url;

  public RepoEntity(final String name, final String url) {
    this.name = name;
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }
}
