package com.prashantchaubey.recipescrappers;

public interface RecipeScrapper {
  String getHost();

  String getTitle();

  String getTotalTime();

  String getYields();

  String getImageURL();

  String getLanguage();

  String getIngredients();

  String getInstructions();

  String getRatings();

  String getAuthor();

  String getReviews();

  String getLinks();
}
