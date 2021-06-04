package com.prashantchaubey.recipescrappers;

import com.prashantchaubey.recipescrappers.fixtures.TestFixtures;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ACoupleCooksScrapperTest {
  private final RecipeScrapper scrapper =
      RecipeScrapperFactory.get("http://acouplecooks.com", TestFixtures.testProvider(), false);

  @Test
  public void itHasTheRightHost() {
    assertThat(scrapper.getHost()).isEqualTo("acouplecooks.com");
  }

  @Test
  public void itCanExtractCanonicalUri() {
    assertThat(scrapper.getCanonicalUri())
        .isEqualTo("https://www.acouplecooks.com/garlic-butter-shrimp/");
  }

  @Test
  public void itCanExtractTitle() {
    Optional<String> maybeTitle = scrapper.getTitle();
    assertThat(maybeTitle).isPresent();
    assertThat(maybeTitle.get()).isEqualTo("Garlic Butter Shrimp (Fast & Easy Dinner!)");
  }

  @Test
  public void itCanExtractTotalTime() {
    Optional<Integer> maybeTotalTime = scrapper.getTotalTime();
    assertThat(maybeTotalTime).isPresent();
    assertThat(maybeTotalTime.get()).isEqualTo(8);
  }

  @Test
  public void itCanExtractYields() {
    Optional<String> maybeYields = scrapper.getYields();
    assertThat(maybeYields).isPresent();
    assertThat(maybeYields.get()).isEqualTo("4 serving(s)");
  }

  @Test
  public void itCanExtractIngredients() {
    assertThat(scrapper.getIngredients())
        .isEqualTo(
            List.of(
                "1 pound large shrimp, deveined (peeled or unpeeled)",
                "3 garlic cloves",
                "1/2 teaspoon kosher salt",
                "3 tablespoons butter",
                "2 lemon wedges",
                "Fresh cilantro or parsley, for garnish"));
  }

  @Test
  public void itCanExtractInstructions() {
    assertThat(scrapper.getInstructions())
        .isEqualTo(
            List.of(
                "If frozen, thaw the shrimp (see the notes above).",
                "Mince the garlic.",
                "Pat the shrimp dry. In a medium bowl, mix the shrimp with the garlic and salt.",
                "In a large skillet, heat the butter on medium high heat. Cook the shrimp for 1 to 2 minutes per side until opaque and cooked through, turning them with tongs.",
                "Spritz with juice of the lemon wedges and serve immediately."));
  }
}
