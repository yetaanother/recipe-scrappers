package com.prashantchaubey.recipescrappers.decorators;

import com.prashantchaubey.recipescrappers.RecipeScrapper;
import org.jsoup.nodes.Element;

import javax.swing.text.html.Option;
import java.util.Optional;

public class OGImageDecorator extends DecoratorBase {
  public OGImageDecorator(RecipeScrapper recipeScrapper) {
    super(recipeScrapper);
  }

  @Override
  public Optional<String> getImageURL() {
    Element img = recipeScrapper.getDOM().selectFirst("meta[property='og:image'][content]");
    if (img == null) {
      return Optional.empty();
    }

    String url = img.attr("content").trim();

    return url.isEmpty() ? Optional.empty() : Optional.of(url);
  }
}
