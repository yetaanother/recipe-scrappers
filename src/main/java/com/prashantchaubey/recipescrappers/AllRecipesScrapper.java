package com.prashantchaubey.recipescrappers;

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
}
