package com.prashantchaubey.recipescrappers;

import com.prashantchaubey.recipescrappers.utils.RecipeScrapperUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRecipeHtmlContentProvider implements RecipeHtmlContentProvider {
  private static final int DEFAULT_TIMEOUT_IN_SECS = 5;
  private static final boolean DEFAULT_FOLLOW_REDICRECTS = true;

  private int timeoutInMillis;
  private boolean followRedirects;

  public HttpRecipeHtmlContentProvider() {
    this.timeoutInMillis = DEFAULT_TIMEOUT_IN_SECS * 1000;
    this.followRedirects = DEFAULT_FOLLOW_REDICRECTS;
  }

  public HttpRecipeHtmlContentProvider(int timeoutInMillis) {
    this.timeoutInMillis = timeoutInMillis;
    this.followRedirects = DEFAULT_FOLLOW_REDICRECTS;
  }

  public HttpRecipeHtmlContentProvider(int timeoutInMillis, boolean followRedirects) {
    this.timeoutInMillis = timeoutInMillis;
    this.followRedirects = followRedirects;
  }

  @Override
  public String get(String uri) {
    HttpURLConnection conn = null;
    try {
      conn = getConnection(uri);

      int status = conn.getResponseCode();
      if (status != 200) {
        throw new NotAbleToExtractHtmlException(
            String.format("Received response code: [%s] for url: [%s]", status, uri));
      }

      return getContent(conn);
    } catch (IOException e) {
      throw new NotAbleToExtractHtmlException(e);
    } finally {
      if (conn != null) {
        try {
          conn.disconnect();
        } catch (Exception e) {
          // do nothing
        }
      }
    }
  }

  private HttpURLConnection getConnection(String uri) throws IOException {
    URL url = new URL(uri);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    RecipeScrapperUtils.getUserHeaders().forEach(conn::setRequestProperty);
    conn.setConnectTimeout(timeoutInMillis);
    conn.setReadTimeout(timeoutInMillis);
    conn.setInstanceFollowRedirects(followRedirects);

    return conn;
  }

  private String getContent(HttpURLConnection conn) throws IOException {
    StringBuilder content = new StringBuilder();
    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
    }

    return content.toString();
  }
}
