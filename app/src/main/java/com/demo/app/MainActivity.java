package com.demo.app;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.simplemented.okdelay.DelayInterceptor;
import com.simplemented.okdelay.SimpleDelayProvider;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private TextView seekBarValueTextView;
  private SeekBar seekBar;
  private Button callRequestButton;
  private ProgressBar progressBar;
  private TextView responseTextView;

  private GitHubService service;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    bindViews();
    setupRetrofit();

    callRequestButton.setOnClickListener(
        view -> {
          callRequestButton.setEnabled(false);
          progressBar.setVisibility(View.VISIBLE);
          final long startTimeMillis = System.currentTimeMillis();

          service
              .listRepositories("pawel-schmidt", "updated")
              .enqueue(
                  new Callback<List<RepoEntity>>() {
                    @Override
                    public void onResponse(
                        final Call<List<RepoEntity>> call,
                        final Response<List<RepoEntity>> response) {
                      callRequestButton.setEnabled(true);
                      progressBar.setVisibility(View.INVISIBLE);

                      final long elapsedMillis = System.currentTimeMillis() - startTimeMillis;
                      final String elapsedTime =
                          getString(R.string.activity_main_seek_request_time_fmt, elapsedMillis);

                      final StringBuilder builder = new StringBuilder(elapsedTime);
                      for (final RepoEntity repoEntity : response.body()) {
                        builder.append(
                            String.format(
                                "<br/><br/><a href=\"%s\">%s</a>",
                                repoEntity.getUrl(), repoEntity.getName()));
                      }

                      responseTextView.setText(Html.fromHtml(builder.toString()));
                    }

                    @Override
                    public void onFailure(final Call<List<RepoEntity>> call, final Throwable t) {
                      callRequestButton.setEnabled(true);
                      progressBar.setVisibility(View.INVISIBLE);
                      responseTextView.setText(
                          getString(R.string.activity_main_error_fmt, t.getMessage()));
                    }
                  });
        });

    responseTextView.setMovementMethod(LinkMovementMethod.getInstance());

    seekBar.setOnSeekBarChangeListener(
        new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(final SeekBar seekBar, final int i, final boolean b) {
            final long duration = i * 500L;
            ((App) getApplication()).delayProvider().setDelay(duration, TimeUnit.MILLISECONDS);
            seekBarValueTextView.setText(
                getString(R.string.activity_main_seek_bar_value_fmt, duration / 1000f));
          }

          @Override
          public void onStartTrackingTouch(final SeekBar seekBar) {}

          @Override
          public void onStopTrackingTouch(final SeekBar seekBar) {}
        });

    seekBar.setProgress(5);
  }

  private void setupRetrofit() {

    final Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(
                new OkHttpClient.Builder()
                    .addInterceptor(new DelayInterceptor(((App) getApplication()).delayProvider()))
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    service = retrofit.create(GitHubService.class);
  }

  private void bindViews() {
    seekBarValueTextView = (TextView) findViewById(R.id.activity_main_seek_bar_value);
    seekBar = (SeekBar) findViewById(R.id.activity_main_seek_bar);
    callRequestButton = (Button) findViewById(R.id.activity_main_call_request);
    progressBar = (ProgressBar) findViewById(R.id.activity_main_progress_bar);
    responseTextView = (TextView) findViewById(R.id.activity_main_response);
  }
}
