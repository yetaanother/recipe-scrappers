package com.prashantchaubey.recipescrappers.decorators;

import com.prashantchaubey.recipescrappers.RecipeScrapper;
import com.prashantchaubey.recipescrappers.utils.RecipeScrapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class SchemaOrgDecorator extends AbstractRecipeScrapperDecorator {
  private static final Logger LOG = LoggerFactory.getLogger(SchemaOrgDecorator.class);
  private Map<String, Object> metadata;

  public SchemaOrgDecorator(RecipeScrapper recipeScrapper) {
    super(recipeScrapper);
    Map<String, Object> jsonLdSchema =
        RecipeScrapperUtils.extractJsonLdSchema(recipeScrapper.getDOM());
    try {
      this.metadata = extractRecipeSchemaFromRoot(jsonLdSchema);
    } catch (Exception e) {
      LOG.error("Error in extracting recipe schema from json-ld", e);
      this.metadata = Map.of();
    }
  }

  private Map<String, Object> extractRecipeSchemaFromRoot(Map<String, Object> jsonLdSchema) {
    Map<String, Object> recipeSchema = extractRecipeSchema(jsonLdSchema);
    if (!recipeSchema.isEmpty()) {
      return recipeSchema;
    }

    if (jsonLdSchema.containsKey("@graph")) {
      List<Map<String, Object>> schemas =
          (List<Map<String, Object>>) jsonLdSchema.getOrDefault("@graph", new ArrayList<>());
      for (Map<String, Object> schema : schemas) {
        recipeSchema = extractRecipeSchema(schema);
        if (!recipeSchema.isEmpty()) {
          return recipeSchema;
        }
      }
    }

    return Map.of();
  }

  private Map<String, Object> extractRecipeSchema(Map<String, Object> entity) {
    String context = String.valueOf(entity.getOrDefault("@context", ""));
    if (!context.contains("schema.org")) {
      return Map.of();
    }

    Object type = entity.getOrDefault("@type", "");
    if (!(type instanceof String)) {
      return Map.of();
    }

    String typeInLowerCase = ((String) type).toLowerCase();
    if (typeInLowerCase.equals("recipe")) {
      return entity;
    } else if (typeInLowerCase.equals("webpage")) {
      return (Map<String, Object>) entity.get("mainEntity");
    }

    return Map.of();
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
    if (metadata.containsKey("totalTime")) {
      int totalTime =
          RecipeScrapperUtils.extractMinutesForSchemaOrg(String.valueOf(metadata.get("totalTime")))
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
    return recipeScrapper.getTotalTime();
  }

  @Override
  public Optional<String> getYields() {
    if (metadata.containsKey("recipeYield")) {
      Object yieldObj = metadata.get("recipeYield");
      String yield;
      if (yieldObj instanceof List) {
        List<?> yieldList = (List<?>) yieldObj;
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
        String[] yieldSplits = yield.split(System.lineSeparator());
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
        Map<?, ?> imageMap = (Map<?, ?>) imageObj;
        if (imageMap.containsKey("url")) {
          return Optional.of(String.valueOf(imageMap.get("url")));
        }
      }
      if (imageObj instanceof List) {
        List<?> imageList = (List<?>) imageObj;
        if (!imageList.isEmpty()) {
          Object imageListFirst = imageList.get(0);
          if (imageListFirst instanceof Map) {
            Map<?, ?> imageListFirstMap = (Map<?, ?>) imageListFirst;
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
      ingredients = (List<String>) metadata.get("recipeIngredient");
    } else if (metadata.containsKey("ingredients")) {
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
        List<?> instructionsList = (List<?>) instructionsObj;
        return instructionsList.stream()
            .map(
                val -> {
                  if (val instanceof Map) {
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
        return Optional.of((String) authorObj);
      } else if (authorObj instanceof Map) {
        Map<?, ?> authorMap = (Map<?, ?>) authorObj;
        if (authorMap.containsKey("name")) {
          return Optional.of(String.valueOf(authorMap.get("name")));
        }
      }
    }

    return recipeScrapper.getAuthor();
  }
}
