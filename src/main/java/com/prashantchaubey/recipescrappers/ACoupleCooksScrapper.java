package com.prashantchaubey.recipescrappers;

import com.prashantchaubey.recipescrappers.providers.RecipeHtmlContentProvider;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperConstants;

public class ACoupleCooksScrapper extends AbstractRecipeScrapper {
  public ACoupleCooksScrapper(String uri, RecipeHtmlContentProvider contentProvider) {
    super(uri, contentProvider);
  }

  @Override
  public String getHost() {
    return RecipeScrapperConstants.WebsiteHost.A_COUPLE_COOKS;
  }
}
