package com.prashantchaubey.recipescrappers.decorators;

import com.prashantchaubey.recipescrappers.RecipeScrapper;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperUtils;
import org.apache.any23.Any23;
import org.apache.any23.extractor.ExtractionException;
import org.apache.any23.source.DocumentSource;
import org.apache.any23.source.StringDocumentSource;
import org.apache.any23.writer.JSONLDWriter;
import org.apache.any23.writer.TripleHandler;
import org.apache.any23.writer.TripleHandlerException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SchemaOrgDecorator extends DecoratorBase {
  private Map<String, Object> metadata;

  public SchemaOrgDecorator(RecipeScrapper recipeScrapper) {
    super(recipeScrapper);
    metadata = new HashMap<>();
    Any23 any23 = new Any23();
    DocumentSource source =
        new StringDocumentSource(recipeScrapper.getHtmlContent(), recipeScrapper.getUri());
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try (TripleHandler handler = new JSONLDWriter(out)) {
      any23.extract(source, handler);
    } catch (ExtractionException | IOException | TripleHandlerException ignored) {
    }
    String output = out.toString();
  }

  @Override
  public Optional<String> getTitle() {
    if (metadata.containsKey("name")) {
      return Optional.of(String.valueOf(metadata.get("name")));
    }
    return recipeScrapper.getTitle();
  }

  @Override
  public Optional<Integer> getTotalTime() {
    try {
      if (metadata.containsKey("totalTime")) {
        int totalTime =
            RecipeScrapperUtils.extractMinutesForSchemaOrg(
                    String.valueOf(metadata.get("totalTime")))
                .orElse(0);
        if (totalTime != 0) {
          return Optional.of(totalTime);
        }
      }

      if (metadata.containsKey("prepTime") || metadata.containsKey("cookTime")) {
        int prepTime =
            RecipeScrapperUtils.extractMinutesForSchemaOrg(String.valueOf(metadata.get("prepTime")))
                .orElse(0);
        int cookTime =
            RecipeScrapperUtils.extractMinutesForSchemaOrg(String.valueOf(metadata.get("cookTime")))
                .orElse(0);
        int totalTime = prepTime + cookTime;
        if (totalTime != 0) {
          return Optional.of(totalTime);
        }
      }
    } catch (Exception ignored) {
    }

    return recipeScrapper.getTotalTime();
  }

  @Override
  public Optional<String> getYields() {
    if (metadata.containsKey("recipeYield")) {
      Object yieldObj = metadata.get("recipeYield");
      String yield;
      if (yieldObj instanceof List) {
        //noinspection unchecked
        List<Object> yieldList = (List<Object>) yieldObj;
        if (yieldList.isEmpty()) {
          return recipeScrapper.getYields();
        }
        yield = String.valueOf(yieldList.get(0));
      } else {
        yield = String.valueOf(yieldObj);
      }
      if (yield.length() <= 3) {
        return Optional.of(yield + " serving(s)");
      }
      if (yield.contains(System.lineSeparator())) {
        String yieldSplits[] = yield.split(System.lineSeparator());
        return Optional.of(yieldSplits[yieldSplits.length - 1]);
      }
      return Optional.of(yield);
    }
    return recipeScrapper.getYields();
  }

  @Override
  public Optional<String> getImageURL() {
    if (metadata.containsKey("image")) {
      Object imageObj = metadata.get("image");
      if (imageObj instanceof Map) {
        //noinspection unchecked
        Map<String, Object> imageMap = (Map<String, Object>) imageObj;
        if (imageMap.containsKey("url")) {
          return Optional.of(String.valueOf(imageMap.get("url")));
        }
      }
      if (imageObj instanceof List) {
        //noinspection unchecked
        List<Object> imageList = (List<Object>) imageObj;
        if (!imageList.isEmpty()) {
          Object imageListFirst = imageList.get(0);
          if (imageListFirst instanceof Map) {
            //noinspection unchecked
            Map<String, Object> imageListFirstMap = (Map<String, Object>) imageListFirst;
            if (imageListFirstMap.containsKey("url")) {
              return Optional.of(String.valueOf(imageListFirstMap.get("url")));
            }
          } else {
            return Optional.of(String.valueOf(imageListFirst));
          }
        }
      }
    }

    return recipeScrapper.getImageURL();
  }

  @Override
  public Optional<String> getLanguage() {
    if (metadata.containsKey("inLanguage")) {
      return Optional.of(String.valueOf(metadata.get("inLanguage")));
    }
    if (metadata.containsKey("language")) {
      return Optional.of(String.valueOf(metadata.get("language")));
    }
    return recipeScrapper.getLanguage();
  }

  @Override
  public List<String> getIngredients() {
    List<String> ingredients = new ArrayList<>();
    if (metadata.containsKey("recipeIngredient")) {
      //noinspection unchecked
      ingredients = (List<String>) metadata.get("recipeIngredient");
    } else if (metadata.containsKey("ingredients")) {
      //noinspection unchecked
      ingredients = (List<String>) metadata.get("ingredients");
    }
    if (!ingredients.isEmpty()) {
      return ingredients.stream().map(RecipeScrapperUtils::normalize).collect(Collectors.toList());
    }
    return recipeScrapper.getIngredients();
  }

  @Override
  public List<String> getInstructions() {
    if (metadata.containsKey("recipeInstructions")) {
      Object instructionsObj = metadata.get("recipeInstructions");
      if (instructionsObj instanceof List) {
        //noinspection unchecked
        List<Object> instructionsList = (List<Object>) instructionsObj;
        return instructionsList.stream()
            .map(
                val -> {
                  if (val instanceof Map) {
                    // noinspection unchecked
                    return RecipeScrapperUtils.normalize(((Map<String, String>) val).get("text"));
                  }
                  return RecipeScrapperUtils.normalize(String.valueOf(val));
                })
            .collect(Collectors.toList());
      }
      return Collections.singletonList(String.valueOf(instructionsObj));
    }
    return recipeScrapper.getInstructions();
  }

  @Override
  public Optional<Double> getRatings() {
    if (metadata.containsKey("aggregateRating")) {
      Object ratingObj = metadata.get("aggregateRating");
      if (ratingObj instanceof Map) {
        //noinspection unchecked
        Map<String, Double> ratingMap = (Map<String, Double>) ratingObj;
        if (ratingMap.containsKey("ratingValue")) {
          return Optional.of(ratingMap.get("ratingValue"));
        }
      } else {
        return Optional.of(Double.parseDouble(String.valueOf(ratingObj)));
      }
    }
    return recipeScrapper.getRatings();
  }

  @Override
  public Optional<String> getAuthor() {
    if (metadata.containsKey("author")) {
      Object authorObj = metadata.get("author");
      if (authorObj instanceof String) {
        return Optional.of(String.valueOf(authorObj));
      } else if (authorObj instanceof Map) {
        // noinspection unchecked
        Map<String, Object> authorMap = (Map<String, Object>) authorObj;
        if (authorMap.containsKey("name")) {
          return Optional.of(String.valueOf(authorMap.get("name")));
        }
      }
    }

    return recipeScrapper.getAuthor();
  }
}
