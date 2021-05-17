package com.prashantchaubey.recipescrappers;

import com.prashantchaubey.recipescrappers.providers.RecipeHtmlContentProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractRecipeScrapper implements RecipeScrapper {
  private String uri;
  private Document dom;
  private String htmlContent;

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
}
