package com.prashantchaubey.recipescrappers;

import com.prashantchaubey.recipescrappers.utils.RecipeScrapperUtils;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class RecipeScrapperFactory {
  private static Map<String, RecipeScrapper> hostToScrapperInstanceMap = new HashMap<>();

  static {
    init();
  }

  private static void init() {
    Reflections reflections =
        new Reflections(RecipeScrapperConstants.SCRAPPER_IMPLEMENTATIONS_PACKAGE);
    Set<Class<? extends RecipeScrapper>> recipeScrapperClasses =
        reflections.getSubTypesOf(RecipeScrapper.class);

    for (Class<? extends RecipeScrapper> recipeScrapperCls : recipeScrapperClasses) {
      if (recipeScrapperCls.equals(AbstractRecipeScrapper.class)) {
        continue;
      }
      try {
        RecipeScrapper recipeScrapper = recipeScrapperCls.getDeclaredConstructor().newInstance();
        hostToScrapperInstanceMap.put(recipeScrapper.getHost(), recipeScrapper);
      } catch (InstantiationException
          | IllegalAccessException
          | InvocationTargetException
          | NoSuchMethodException e) {
        throw new RuntimeException(
            String.format("Not able to initialize the scrapper [%s]", recipeScrapperCls), e);
      }
    }
  }

  private RecipeScrapperFactory() {
    throw new IllegalStateException("This should never be constructed");
  }

  public static RecipeScrapper get(String url) {
    String host = RecipeScrapperUtils.getHost(url.replace("://www.", "://"));

    RecipeScrapper scrapper = hostToScrapperInstanceMap.get(host);
    if (scrapper == null) {
      throw new ScrapperNotImplementedException(
          String.format("Scrapper not implemented for host: [%s]", host));
    }

    return scrapper;
  }
}
