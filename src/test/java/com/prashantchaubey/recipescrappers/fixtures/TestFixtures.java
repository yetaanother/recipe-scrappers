package com.prashantchaubey.recipescrappers.fixtures;

import com.prashantchaubey.recipescrappers.providers.RecipeHtmlContentProvider;
import com.prashantchaubey.recipescrappers.providers.TestRecipeHtmlContentProvider;

public final class TestFixtures {
  public static RecipeHtmlContentProvider testProvider() {
    return new TestRecipeHtmlContentProvider();
  }
}
