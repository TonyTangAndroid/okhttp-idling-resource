package com.demo.app;

public interface DelayProvider {

  void set(long delayInMilliseconds);

  long get();
}
