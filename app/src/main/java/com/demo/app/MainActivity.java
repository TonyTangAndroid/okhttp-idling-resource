package com.demo.app;

import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;
import retrofit2.Response;

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
          execute();
        });

    responseTextView.setMovementMethod(LinkMovementMethod.getInstance());

    seekBar.setOnSeekBarChangeListener(
        new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(final SeekBar seekBar, final int i, final boolean b) {
            MainActivity.this.setDelay(i);
          }

          @Override
          public void onStartTrackingTouch(final SeekBar seekBar) {
          }

          @Override
          public void onStopTrackingTouch(final SeekBar seekBar) {
          }
        });

    seekBar.setProgress(5);
  }

  private void setDelay(int time) {
    final long duration = time * 500L;
    ((App) getApplication()).delayProvider().setDelay(duration, TimeUnit.MILLISECONDS);
    seekBarValueTextView.setText(
        getString(R.string.activity_main_seek_bar_value_fmt, duration / 1000f));
  }

  private void execute() {
    callRequestButton.setEnabled(false);
    progressBar.setVisibility(View.VISIBLE);
    final long startTimeMillis = System.currentTimeMillis();

    service
        .list("pawel-schmidt", "updated")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .as(autoDisposable(from(this)))
        .subscribe(response -> onSuccess(response, startTimeMillis),this::onFailure );
  }

  public void onSuccess(final List<RepoEntity> response, long startTimeMillis) {
    callRequestButton.setEnabled(true);
    progressBar.setVisibility(View.INVISIBLE);

    final long elapsedMillis = System.currentTimeMillis() - startTimeMillis;
    final String elapsedTime =
        getString(R.string.activity_main_seek_request_time_fmt, elapsedMillis);

    final StringBuilder builder = new StringBuilder(elapsedTime);
    for (final RepoEntity repoEntity : response) {
      builder.append(
          String.format(
              "<br/><br/><a href=\"%s\">%s</a>",
              repoEntity.getUrl(), repoEntity.getName()));
    }

    responseTextView.setText(Html.fromHtml(builder.toString()));
  }


  public void onFailure( final Throwable t) {
    callRequestButton.setEnabled(true);
    progressBar.setVisibility(View.INVISIBLE);
    responseTextView.setText(
        getString(R.string.activity_main_error_fmt, t.getMessage()));
  }

  private void setupRetrofit() {
    this.service = OkhttpProvider.gitHubService(OkhttpProvider.get((App) getApplication()));
  }

  private void bindViews() {
    seekBarValueTextView = (TextView) findViewById(R.id.activity_main_seek_bar_value);
    seekBar = (SeekBar) findViewById(R.id.activity_main_seek_bar);
    callRequestButton = (Button) findViewById(R.id.activity_main_call_request);
    progressBar = (ProgressBar) findViewById(R.id.activity_main_progress_bar);
    responseTextView = (TextView) findViewById(R.id.activity_main_response);
  }
}
