package com.demo.app;

import io.reactivex.Single;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

  @GET("users/{user}/repos")
  Call<List<RepoEntity>> listRepositories(@Path("user") String user, @Query("sort") String sort);

  @GET("users/{user}/repos")
  Single<List<RepoEntity>> list(@Path("user") String user, @Query("sort") String sort);
}
