package com.prashantchaubey.recipescrappers.scrappers;

import com.prashantchaubey.recipescrappers.AbstractRecipeScrapper;
import com.prashantchaubey.recipescrappers.RecipeHtmlContentProvider;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperConstants;

public class AllRecipesScrapper extends AbstractRecipeScrapper {

  public AllRecipesScrapper(String url, RecipeHtmlContentProvider contentProvider) {
    super(url, contentProvider);
  }

  @Override
  public String getHost() {
    return RecipeScrapperConstants.WebsiteHost.ALL_RECIPES;
  }

  @Override
  public String getTitle() {
    return null;
  }
}
