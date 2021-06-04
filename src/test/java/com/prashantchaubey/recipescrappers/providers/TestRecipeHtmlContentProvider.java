package com.prashantchaubey.recipescrappers.providers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class TestRecipeHtmlContentProvider implements RecipeHtmlContentProvider {
  @Override
  public String get(String uri) {
    try {
      URL url = getClass().getClassLoader().getResource(getTestDataFileName(uri));
      if (url == null) {
        throw new RuntimeException(String.format("Can't load test data: %s", uri));
      }

      return Files.readString(Paths.get(url.toURI()));
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String getTestDataFileName(String uri) {
    Map<String, String> urlToFile = Map.of("http://acouplecooks.com", "data/acouplecooks.html");
    return urlToFile.get(uri);
  }
}
