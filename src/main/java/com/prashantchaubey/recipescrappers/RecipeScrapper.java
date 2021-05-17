package com.prashantchaubey.recipescrappers;

import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Optional;

public interface RecipeScrapper {
  String getUri();

  String getHost();

  Optional<String> getTitle();

  Optional<Integer> getTotalTime();

  Optional<String> getYields();

  Optional<String> getImageURL();

  Optional<String> getLanguage();

  List<String> getIngredients();

  List<String> getInstructions();

  Optional<Double> getRatings();

  Optional<String> getAuthor();

  Optional<String> getReviews();

  List<String> getLinks();

  Document getDOM();

  String getHtmlContent();
}
