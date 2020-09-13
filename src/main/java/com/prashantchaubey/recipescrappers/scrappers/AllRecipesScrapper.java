package com.prashantchaubey.recipescrappers.scrappers;

import com.prashantchaubey.recipescrappers.AbstractRecipeScrapper;
import com.prashantchaubey.recipescrappers.RecipeScrapperConstants;

public class AllRecipesScrapper extends AbstractRecipeScrapper {

  public AllRecipesScrapper() {
    super(RecipeScrapperConstants.WebsiteHost.ALL_RECIPES);
  }
}
