package com.prashantchaubey.recipescrappers;

import com.prashantchaubey.recipescrappers.scrappers.AllRecipesScrapper;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperConstants;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperUtils;

public final class RecipeScrapperFactory {
  private RecipeScrapperFactory() {
    throw new IllegalStateException("This should never be constructed");
  }

  public static RecipeScrapper get(String url, RecipeHtmlContentProvider contentProvider) {
    String host = RecipeScrapperUtils.getHost(url.replace("://www.", "://"));

    switch (host) {
      case RecipeScrapperConstants.WebsiteHost.ALL_RECIPES:
        return new AllRecipesScrapper(url, contentProvider);
      default:
        throw new ScrapperNotImplementedException(
            String.format("Scrapper not implemented for host: [%s]", host));
    }
  }
}
