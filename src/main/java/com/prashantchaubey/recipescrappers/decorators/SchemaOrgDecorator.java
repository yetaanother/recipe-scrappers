package com.prashantchaubey.recipescrappers.decorators;

import com.prashantchaubey.recipescrappers.RecipeScrapper;

import java.util.List;
import java.util.Optional;

public class SchemaOrgDecorator extends DecoratorBase {

  public SchemaOrgDecorator(RecipeScrapper recipeScrapper) {
    super(recipeScrapper);
  }

  @Override
  public Optional<String> getTitle() {
    // todo implement
    return recipeScrapper.getTitle();
  }

  @Override
  public Optional<Integer> getTotalTime() {
    // todo implement
    return recipeScrapper.getTotalTime();
  }

  @Override
  public Optional<String> getYields() {
    // todo implement
    return recipeScrapper.getYields();
  }

  @Override
  public Optional<String> getImageURL() {
    // todo implement
    return recipeScrapper.getImageURL();
  }

  @Override
  public Optional<String> getLanguage() {
    // todo implement
    return recipeScrapper.getLanguage();
  }

  @Override
  public List<String> getIngredients() {
    // todo implement
    return recipeScrapper.getIngredients();
  }

  @Override
  public Optional<String> getInstructions() {
    // todo implement
    return recipeScrapper.getInstructions();
  }

  @Override
  public Optional<Double> getRatings() {
    // todo implement
    return recipeScrapper.getRatings();
  }

  @Override
  public Optional<String> getAuthor() {
    // todo implement
    return recipeScrapper.getAuthor();
  }
}
