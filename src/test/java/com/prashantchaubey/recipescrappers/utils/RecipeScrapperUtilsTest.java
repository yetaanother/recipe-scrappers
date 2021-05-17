package com.prashantchaubey.recipescrappers.utils;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeScrapperUtilsTest {
  @Test
  public void itCanExtractMinutesFromSchemaOrgInPeriodFormat() {
    Optional<Integer> maybeMinutes = RecipeScrapperUtils.extractMinutesForSchemaOrg("PT2H30M");
    assertThat(maybeMinutes).isPresent();
    assertThat(maybeMinutes.get()).isEqualTo(150);
  }

  @Test
  public void itCanExtractMinutesFromSchemaOrgInPeriodFormat2() {
    Optional<Integer> maybeMinutes = RecipeScrapperUtils.extractMinutesForSchemaOrg("P0DT1H10M");
    assertThat(maybeMinutes).isPresent();
    assertThat(maybeMinutes.get()).isEqualTo(70);
  }

  @Test
  public void itCanExtractMinutesFromSchemaOrgInDashFormat() {
    Optional<Integer> maybeMinutes =
        RecipeScrapperUtils.extractMinutesForSchemaOrg("12-15 minutes");
    assertThat(maybeMinutes).isPresent();
    assertThat(maybeMinutes.get()).isEqualTo(15);
  }

  @Test
  public void itCanExtractMinutesFromSchemaOrg() {
    Optional<Integer> maybeMinutes =
        RecipeScrapperUtils.extractMinutesForSchemaOrg("1 hour 15 mins");
    assertThat(maybeMinutes).isPresent();
    assertThat(maybeMinutes.get()).isEqualTo(75);
  }

  @Test
  public void itCanExtractMinutesFromSchemaOrg2() {
    Optional<Integer> maybeMinutes = RecipeScrapperUtils.extractMinutesForSchemaOrg("3h10m");
    assertThat(maybeMinutes).isPresent();
    assertThat(maybeMinutes.get()).isEqualTo(190);
  }

  @Test
  public void itCanNormalizeString() {
    String output = RecipeScrapperUtils.normalize("&gt;    &#62; \\xa0 \n \t");
    assertThat(output).isEqualTo("> >");
  }
}
