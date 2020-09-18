package com.prashantchaubey.recipescrappers.scrappers;

import com.prashantchaubey.recipescrappers.AbstractRecipeScrapper;
import com.prashantchaubey.recipescrappers.providers.RecipeHtmlContentProvider;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AllRecipesScrapper extends AbstractRecipeScrapper {

  public AllRecipesScrapper(String url, RecipeHtmlContentProvider contentProvider) {
    super(url, contentProvider);
  }

  @Override
  public String getHost() {
    return RecipeScrapperConstants.WebsiteHost.ALL_RECIPES;
  }

  @Override
  public Optional<String> getTitle() {
    return Optional.empty();
  }

  @Override
  public Optional<Integer> getTotalTime() {
    return Optional.empty();
  }

  @Override
  public Optional<String> getYields() {
    return Optional.empty();
  }

  @Override
  public Optional<String> getImageURL() {
    return Optional.empty();
  }

  @Override
  public List<String> getIngredients() {
    return new ArrayList<>();
  }

  @Override
  public Optional<String> getInstructions() {
    return Optional.empty();
  }

  @Override
  public Optional<Double> getRatings() {
    return Optional.empty();
  }

  @Override
  public Optional<String> getAuthor() {
    return Optional.empty();
  }

  @Override
  public Optional<String> getReviews() {
    return Optional.empty();
  }
}
