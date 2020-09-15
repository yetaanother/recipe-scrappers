package com.prashantchaubey.recipescrappers;

import java.util.ArrayList;
import java.util.List;

public class DefaultOnErrorRecipeScrapper implements RecipeScrapper {
  private RecipeScrapper recipeScrapper;

  public DefaultOnErrorRecipeScrapper(RecipeScrapper recipeScrapper) {
    this.recipeScrapper = recipeScrapper;
  }

  @Override
  public String getHost() {
    try {
      return recipeScrapper.getHost();
    } catch (Exception e) {
      return "";
    }
  }

  @Override
  public String getTitle() {
    try {
      return recipeScrapper.getTitle();
    } catch (Exception e) {
      return "";
    }
  }

  @Override
  public int getTotalTime() {
    try {
      return recipeScrapper.getTotalTime();
    } catch (Exception e) {
      return 0;
    }
  }

  @Override
  public String getYields() {
    try {
      return recipeScrapper.getYields();
    } catch (Exception e) {
      return "";
    }
  }

  @Override
  public String getImageURL() {
    try {
      return recipeScrapper.getImageURL();
    } catch (Exception e) {
      return "";
    }
  }

  @Override
  public String getLanguage() {
    try {
      return recipeScrapper.getLanguage();
    } catch (Exception e) {
      return "en";
    }
  }

  @Override
  public List<String> getIngredients() {
    try {
      return recipeScrapper.getIngredients();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }

  @Override
  public String getInstructions() {
    try {
      return recipeScrapper.getInstructions();
    } catch (Exception e) {
      return "";
    }
  }

  @Override
  public double getRatings() {
    try {
      return recipeScrapper.getRatings();
    } catch (Exception e) {
      return -1;
    }
  }

  @Override
  public String getAuthor() {
    try {
      return recipeScrapper.getAuthor();
    } catch (Exception e) {
      return "";
    }
  }

  @Override
  public String getReviews() {
    try {
      return recipeScrapper.getReviews();
    } catch (Exception e) {
      return "";
    }
  }

  @Override
  public List<String> getLinks() {
    try {
      return recipeScrapper.getLinks();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }
}
