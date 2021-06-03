package com.prashantchaubey.recipescrappers;

import com.google.common.annotations.VisibleForTesting;
import com.prashantchaubey.recipescrappers.decorators.*;
import com.prashantchaubey.recipescrappers.exceptions.ScrapperNotImplementedException;
import com.prashantchaubey.recipescrappers.providers.HttpRecipeHtmlContentProvider;
import com.prashantchaubey.recipescrappers.providers.RecipeHtmlContentProvider;
import com.prashantchaubey.recipescrappers.scrappers.AllRecipesScrapper;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperConstants;

import java.net.URI;

public final class RecipeScrapperFactory {
  private RecipeScrapperFactory() {}

  public static RecipeScrapper get(String url) {
    return get(url, new HttpRecipeHtmlContentProvider(), true);
  }

  @VisibleForTesting
  static RecipeScrapper get(
      String url, RecipeHtmlContentProvider contentProvider, boolean defaultValues) {
    String host = URI.create(url.replace("://www.", "://")).getHost();
    RecipeScrapper recipeScrapper;
    if (RecipeScrapperConstants.WebsiteHost.ALL_RECIPES.equals(host)) {
      recipeScrapper = new AllRecipesScrapper(url, contentProvider);
    } else {
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
