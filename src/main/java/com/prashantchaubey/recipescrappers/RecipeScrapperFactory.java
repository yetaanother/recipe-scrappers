package com.prashantchaubey.recipescrappers;

import com.prashantchaubey.recipescrappers.decorators.*;
import com.prashantchaubey.recipescrappers.exceptions.ScrapperNotImplementedException;
import com.prashantchaubey.recipescrappers.providers.RecipeHtmlContentProvider;
import com.prashantchaubey.recipescrappers.scrappers.AllRecipesScrapper;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperConstants;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperUtils;

public final class RecipeScrapperFactory {
  private RecipeScrapperFactory() {
    throw new IllegalStateException("This should never be constructed");
  }

  public static RecipeScrapper get(
      String url, RecipeHtmlContentProvider contentProvider, boolean defaultValues) {
    String host = RecipeScrapperUtils.getHost(url.replace("://www.", "://"));
    RecipeScrapper recipeScrapper;
    switch (host) {
      case RecipeScrapperConstants.WebsiteHost.ALL_RECIPES:
        recipeScrapper = new AllRecipesScrapper(url, contentProvider);
        break;
      default:
        throw new ScrapperNotImplementedException(
            String.format("Scrapper not implemented for host: [%s]", host));
    }

    return applyDecorators(recipeScrapper, defaultValues);
  }

  private static RecipeScrapper applyDecorators(
      RecipeScrapper recipeScrapper, boolean defaultValues) {
    recipeScrapper =
        new NormalizeTitleDecorator(
            new LanguageValidatorDecorator(
                new SchemaOrgDecorator(new OGImageDecorator(recipeScrapper))));

    return defaultValues ? new DefaultOnErrorDecorator(recipeScrapper) : recipeScrapper;
  }
}
