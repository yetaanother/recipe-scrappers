package com.prashantchaubey.recipescrappers;

import com.google.common.annotations.VisibleForTesting;
import com.prashantchaubey.recipescrappers.decorators.*;
import com.prashantchaubey.recipescrappers.exceptions.ScrapperNotImplementedException;
import com.prashantchaubey.recipescrappers.providers.HttpRecipeHtmlContentProvider;
import com.prashantchaubey.recipescrappers.providers.RecipeHtmlContentProvider;

import java.net.URI;

import static com.prashantchaubey.recipescrappers.utils.RecipeScrapperConstants.WebsiteHost.ALL_RECIPES;
import static com.prashantchaubey.recipescrappers.utils.RecipeScrapperConstants.WebsiteHost.A_COUPLE_COOKS;

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
    switch (host) {
      case ALL_RECIPES:
        recipeScrapper = new AllRecipesScrapper(url, contentProvider);
        break;
      case A_COUPLE_COOKS:
        recipeScrapper = new ACoupleCooksScrapper(url, contentProvider);
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
