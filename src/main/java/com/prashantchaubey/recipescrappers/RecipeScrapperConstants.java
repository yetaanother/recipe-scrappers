package com.prashantchaubey.recipescrappers;

public final class RecipeScrapperConstants {
  private RecipeScrapperConstants() {
    throw new IllegalStateException("This should never be constructed");
  }

  public static final String SCRAPPER_IMPLEMENTATIONS_PACKAGE =
      "com.prashantchaubey.recipescrappers.scrappers";

  public final class WebsiteHost {
    private WebsiteHost() {
      throw new IllegalStateException("This should never be constructed");
    }

    public static final String ALL_RECIPES = "allrecipes.com";
  }
}
