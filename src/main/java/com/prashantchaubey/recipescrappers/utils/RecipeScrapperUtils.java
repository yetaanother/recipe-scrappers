package com.prashantchaubey.recipescrappers.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RecipeScrapperUtils {
  private static final Logger LOG = LoggerFactory.getLogger(RecipeScrapperUtils.class);
  private static final Pattern SCHMEA_ORG_TIME_PATTERN =
      Pattern.compile(
          "(\\D*(?<hours>\\d+)\\s*(hours|hrs|hr|h|Hours|H|óra))?(\\D*(?<minutes>\\d+)\\s*(minutes|mins|min|m|Minutes|M|perc))?");

  private static final Map<String, String> USER_HEADERS =
      Map.of(
          "User-Agent",
          "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.7) Gecko/2009021910 Firefox/3.0.7");

  public static Map<String, String> getUserHeaders() {
    return USER_HEADERS;
  }

  public static String normalize(String input) {
    input = StringEscapeUtils.unescapeHtml4(input);
    input = input.replace("\\xa0", " ").replace("\n", " ").replace("\t", " ").trim();
    return input.replaceAll("\\s+", " ");
  }

  public static Optional<Integer> extractMinutesForSchemaOrg(String minutesStr) {
    if (minutesStr.startsWith("P") && minutesStr.contains("T")) {
      minutesStr = minutesStr.split("T", 2)[1];
    }
    if (minutesStr.contains("-")) {
      minutesStr = minutesStr.split("-", 2)[1];
    }
    Matcher matcher = SCHMEA_ORG_TIME_PATTERN.matcher(minutesStr);
    if (!matcher.matches()) {
      return Optional.empty();
    }

    int minutes;
    String minuteGrp = matcher.group("minutes");
    minutes = StringUtils.isEmpty(minuteGrp) ? 0 : Integer.parseInt(minuteGrp);
    String hoursGrp = matcher.group("hours");
    minutes += 60 * (StringUtils.isEmpty(hoursGrp) ? 0 : Integer.parseInt(hoursGrp));

    return Optional.of(minutes);
  }

  public static Map<String, Object> extractJsonLdSchema(Document dom) {
    Evaluator jsonLdEvaluator =
        CombiningEvaluators.and(
            new Evaluator.Tag("script"),
            new Evaluator.AttributeWithValue("type", "application/ld+json"));
    Element element = dom.selectFirst(jsonLdEvaluator);
    if (element == null) {
      return Map.of();
    }

    try {
      return new ObjectMapper().readValue(element.data(), new TypeReference<>() {});
    } catch (IOException e) {
      LOG.error("Error in reading json-ld schema", e);
      return Map.of();
    }
  }
}
