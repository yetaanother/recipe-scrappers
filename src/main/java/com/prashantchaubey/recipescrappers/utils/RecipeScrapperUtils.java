package com.prashantchaubey.recipescrappers.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RecipeScrapperUtils {
  private static final Pattern SCHMEA_ORG_TIME_PATTERN =
      Pattern.compile(
          "(\\D*(?<hours>\\d+)\\s*(hours|hrs|hr|h|Hours|H|óra))?(\\D*(?<minutes>\\d+)\\s*(minutes|mins|min|m|Minutes|M|perc))?");

  private static final Map<String, String> USER_HEADERS =
      Map.of(
          "User-Agent",
          "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.7) Gecko/2009021910 Firefox/3.0.7");

  private RecipeScrapperUtils() {
    throw new IllegalStateException("This should never be constructed");
  }

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
}
