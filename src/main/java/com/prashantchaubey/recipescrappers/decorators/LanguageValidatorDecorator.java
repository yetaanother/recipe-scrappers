package com.prashantchaubey.recipescrappers.decorators;

import com.prashantchaubey.recipescrappers.RecipeScrapper;
import org.apache.commons.lang3.LocaleUtils;

import java.util.Locale;
import java.util.Optional;

public class LanguageValidatorDecorator extends DecoratorBase {
  public LanguageValidatorDecorator(RecipeScrapper recipeScrapper) {
    super(recipeScrapper);
  }

  @Override
  public Optional<String> getLanguage() {
    Optional<String> maybeLanguage = recipeScrapper.getLanguage();
    if (!maybeLanguage.isPresent()) {
      return Optional.empty();
    }

    String language = maybeLanguage.get();
    Locale locale = new Locale.Builder().setLanguageTag(language).build();

    return LocaleUtils.isAvailableLocale(locale) ? maybeLanguage : Optional.empty();
  }
}
