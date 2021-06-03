package com.prashantchaubey.recipescrappers;

import com.prashantchaubey.recipescrappers.providers.RecipeHtmlContentProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractRecipeScrapper implements RecipeScrapper {
  private final String uri;
  private final Document dom;
  private final String htmlContent;

  public AbstractRecipeScrapper(String uri, RecipeHtmlContentProvider contentProvider) {
    this.uri = uri;
    this.htmlContent = contentProvider.get(uri);
    this.dom = Jsoup.parse(htmlContent);
  }

  @Override
  public String getUri() {
    return uri;
  }

  @Override
  public Optional<String> getLanguage() {
    Element html = dom.selectFirst("html[lang]");
    if (html == null) {
      return Optional.empty();
    }

    String language = html.attr("lang").trim();
    return language.isEmpty() ? Optional.empty() : Optional.of(language);
  }

  @Override
  public List<String> getLinks() {
    Set<String> invalidLinks = new HashSet<>(Arrays.asList("", "#"));
    Elements anchors = dom.select("a[href]");

    return anchors.stream()
        .map(el -> el.attr("href"))
        .filter(val -> !invalidLinks.contains(val))
        .collect(Collectors.toList());
  }

  @Override
  public Document getDOM() {
    return dom;
  }

  @Override
  public String getHtmlContent() {
    return htmlContent;
  }

  @Override
  public Optional<String> getTitle() {
    return Optional.empty();
  }

  @Override
  public Optional<Integer> getTotalTime() {
    return Optional.empty();
  }

  @Override
  public Optional<String> getYields() {
    return Optional.empty();
  }

  @Override
  public Optional<String> getImageURL() {
    return Optional.empty();
  }

  @Override
  public List<String> getIngredients() {
    return new ArrayList<>();
  }

  @Override
  public List<String> getInstructions() {
    return new ArrayList<>();
  }

  @Override
  public Optional<Double> getRatings() {
    return Optional.empty();
  }

  @Override
  public Optional<String> getAuthor() {
    return Optional.empty();
  }

  @Override
  public Optional<String> getReviews() {
    return Optional.empty();
  }
}
