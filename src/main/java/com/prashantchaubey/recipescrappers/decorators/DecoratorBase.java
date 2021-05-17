package com.prashantchaubey.recipescrappers.decorators;

import com.prashantchaubey.recipescrappers.RecipeScrapper;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Optional;

public class DecoratorBase implements RecipeScrapper {
  protected RecipeScrapper recipeScrapper;

  public DecoratorBase(RecipeScrapper recipeScrapper) {
    this.recipeScrapper = recipeScrapper;
  }

  @Override
  public String getUri() {
    return recipeScrapper.getUri();
  }

  @Override
  public String getHost() {
    return recipeScrapper.getHost();
  }

  @Override
  public Optional<String> getTitle() {
    return recipeScrapper.getTitle();
  }

  @Override
  public Optional<Integer> getTotalTime() {
    return recipeScrapper.getTotalTime();
  }

  @Override
  public Optional<String> getYields() {
    return recipeScrapper.getYields();
  }

  @Override
  public Optional<String> getImageURL() {
    return recipeScrapper.getImageURL();
  }

  @Override
  public Optional<String> getLanguage() {
    return recipeScrapper.getLanguage();
  }

  @Override
  public List<String> getIngredients() {
    return recipeScrapper.getIngredients();
  }

  @Override
  public List<String> getInstructions() {
    return recipeScrapper.getInstructions();
  }

  @Override
  public Optional<Double> getRatings() {
    return recipeScrapper.getRatings();
  }

  @Override
  public Optional<String> getAuthor() {
    return recipeScrapper.getAuthor();
  }

  @Override
  public Optional<String> getReviews() {
    return recipeScrapper.getReviews();
  }

  @Override
  public List<String> getLinks() {
    return recipeScrapper.getLinks();
  }

  @Override
  public Document getDOM() {
    return recipeScrapper.getDOM();
  }

  @Override
  public String getHtmlContent() {
    return recipeScrapper.getHtmlContent();
  }
}
