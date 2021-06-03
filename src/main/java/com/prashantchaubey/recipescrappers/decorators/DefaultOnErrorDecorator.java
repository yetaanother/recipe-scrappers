package com.prashantchaubey.recipescrappers.decorators;

import com.prashantchaubey.recipescrappers.RecipeScrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultOnErrorDecorator extends AbstractRecipeScrapperDecorator {
  public DefaultOnErrorDecorator(RecipeScrapper recipeScrapper) {
    super(recipeScrapper);
  }

  @Override
  public Optional<String> getTitle() {
    try {
      return recipeScrapper.getTitle();
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Integer> getTotalTime() {
    try {
      return recipeScrapper.getTotalTime();
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getYields() {
    try {
      return recipeScrapper.getYields();
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getImageURL() {
    try {
      return recipeScrapper.getImageURL();
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getLanguage() {
    try {
      return recipeScrapper.getLanguage();
    } catch (Exception e) {
      return Optional.of("en");
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
  public List<String> getInstructions() {
    try {
      return recipeScrapper.getInstructions();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }

  @Override
  public Optional<Double> getRatings() {
    try {
      return recipeScrapper.getRatings();
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getAuthor() {
    try {
      return recipeScrapper.getAuthor();
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getReviews() {
    try {
      return recipeScrapper.getReviews();
    } catch (Exception e) {
      return Optional.empty();
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
