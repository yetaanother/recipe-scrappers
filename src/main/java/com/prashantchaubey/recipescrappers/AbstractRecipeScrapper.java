package com.prashantchaubey.recipescrappers;

public abstract class AbstractRecipeScrapper implements RecipeScrapper {
  private String host;

  public AbstractRecipeScrapper(String host) {
    this.host = host;
  }

  @Override
  public String getHost() {
    return host;
  }

  @Override
  public String getTitle() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getTotalTime() {
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
  public String getIngredients() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getInstructions() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getRatings() {
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
  public String getLinks() {
    throw new UnsupportedOperationException();
  }
}
