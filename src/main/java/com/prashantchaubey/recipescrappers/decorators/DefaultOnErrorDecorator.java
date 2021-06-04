package com.prashantchaubey.recipescrappers.decorators;

import com.prashantchaubey.recipescrappers.RecipeScrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultOnErrorDecorator extends AbstractRecipeScrapperDecorator {
  private static final Logger LOG = LoggerFactory.getLogger(DefaultOnErrorDecorator.class);

  public DefaultOnErrorDecorator(RecipeScrapper recipeScrapper) {
    super(recipeScrapper);
  }

  @Override
  public Optional<String> getTitle() {
    try {
      return recipeScrapper.getTitle();
    } catch (Exception e) {
      LOG.error("Error in getting title", e);
      return Optional.empty();
    }
  }

  @Override
  public Optional<Integer> getTotalTime() {
    try {
      return recipeScrapper.getTotalTime();
    } catch (Exception e) {
      LOG.error("Error in getting total time", e);
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getYields() {
    try {
      return recipeScrapper.getYields();
    } catch (Exception e) {
      LOG.error("Error in getting yields", e);
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getImageURL() {
    try {
      return recipeScrapper.getImageURL();
    } catch (Exception e) {
      LOG.error("Error in getting image url", e);
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getLanguage() {
    try {
      return recipeScrapper.getLanguage();
    } catch (Exception e) {
      LOG.error("Error in getting language", e);
      return Optional.of("en");
    }
  }

  @Override
  public List<String> getIngredients() {
    try {
      return recipeScrapper.getIngredients();
    } catch (Exception e) {
      LOG.error("Error in getting ingredients", e);
      return new ArrayList<>();
    }
  }

  @Override
  public List<String> getInstructions() {
    try {
      return recipeScrapper.getInstructions();
    } catch (Exception e) {
      LOG.error("Error in getting instructions", e);
      return new ArrayList<>();
    }
  }

  @Override
  public Optional<Double> getRatings() {
    try {
      return recipeScrapper.getRatings();
    } catch (Exception e) {
      LOG.error("Error in getting ratings", e);
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getAuthor() {
    try {
      return recipeScrapper.getAuthor();
    } catch (Exception e) {
      LOG.error("Error in getting author", e);
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getReviews() {
    try {
      return recipeScrapper.getReviews();
    } catch (Exception e) {
      LOG.error("Error in getting reviews", e);
      return Optional.empty();
    }
  }

  @Override
  public List<String> getLinks() {
    try {
      return recipeScrapper.getLinks();
    } catch (Exception e) {
      LOG.error("Error in getting links", e);
      return new ArrayList<>();
    }
  }
}
