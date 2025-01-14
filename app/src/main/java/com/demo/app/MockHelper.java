package com.demo.app;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class MockHelper {

  static String convertStreamToString(InputStream is) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line).append("\n");
    }
    reader.close();
    return sb.toString();
  }

  static String string(Context context, String filePath) throws Exception {
    final InputStream stream = context.getResources().getAssets().open(filePath);
    String ret = convertStreamToString(stream);
    // Make sure you close all streams.
    stream.close();
    return ret;
  }
}
