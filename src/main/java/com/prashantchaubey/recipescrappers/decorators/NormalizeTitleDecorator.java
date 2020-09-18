package com.prashantchaubey.recipescrappers.decorators;

import com.prashantchaubey.recipescrappers.RecipeScrapper;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperUtils;

import java.util.Optional;

public class NormalizeTitleDecorator extends DecoratorBase {
  public NormalizeTitleDecorator(RecipeScrapper recipeScrapper) {
    super(recipeScrapper);
  }

  @Override
  public Optional<String> getTitle() {
    return recipeScrapper.getTitle().map(RecipeScrapperUtils::normalize);
  }
}
