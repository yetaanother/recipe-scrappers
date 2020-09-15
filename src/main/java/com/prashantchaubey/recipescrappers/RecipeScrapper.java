package com.prashantchaubey.recipescrappers;

import java.util.List;

public interface RecipeScrapper {
  String getHost();

  String getTitle();

  int getTotalTime();

  String getYields();

  String getImageURL();

  String getLanguage();

  List<String> getIngredients();

  String getInstructions();

  double getRatings();

  String getAuthor();

  String getReviews();

  List<String> getLinks();
}
