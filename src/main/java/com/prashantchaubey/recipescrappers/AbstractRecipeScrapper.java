package com.prashantchaubey.recipescrappers;

import java.util.List;

public abstract class AbstractRecipeScrapper implements RecipeScrapper {
  private String htmlContent;
  private String uri;

  public AbstractRecipeScrapper(String uri, RecipeHtmlContentProvider contentProvider) {
    this.uri = uri;
    this.htmlContent = contentProvider.get(uri);
  }

  @Override
  public String getUri() {
    return uri;
  }

  @Override
  public int getTotalTime() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getYields() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getImageURL() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getLanguage() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<String> getIngredients() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getInstructions() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double getRatings() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getAuthor() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getReviews() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<String> getLinks() {
    throw new UnsupportedOperationException();
  }
}
