package com.prashantchaubey.recipescrappers.utils;

public final class RecipeScrapperConstants {
  private RecipeScrapperConstants() {}

  public static final String SCRAPPER_IMPLEMENTATIONS_PACKAGE =
      "com.prashantchaubey.recipescrappers.scrappers";

  public final class WebsiteHost {
    private WebsiteHost() {
      throw new IllegalStateException("This should never be constructed");
    }

    public static final String ALL_RECIPES = "allrecipes.com";
  }
}
