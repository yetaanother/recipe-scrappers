package com.prashantchaubey.recipescrappers.utils;

import com.prashantchaubey.recipescrappers.exceptions.InvalidURLException;
import org.apache.commons.text.StringEscapeUtils;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RecipeScrapperUtils {
  private static final Pattern URL_PATTERN =
      Pattern.compile(
          "^((?<schema>.+?)://)?((?<user>.+?)(:(?<password>.*?))?@)?(?<host>.*?)(:(?<port>\\d+?))?(?<path>/.*?)?(?<query>[?].*?)?$");
  private static final Pattern SCHMEA_ORG_TIME_PATTERN =
      Pattern.compile(
          "(\\D*(?<hours>\\d+)\\s*(hours|hrs|hr|h|Hours|H|óra))?(\\D*(?<minutes>\\d+)\\s*(minutes|mins|min|m|Minutes|M|perc))?");

  private static final Map<String, String> USER_HEADERS = new HashMap<>();

  static {
    USER_HEADERS.put(
        "User-Agent",
        "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.7) Gecko/2009021910 Firefox/3.0.7");
  }

  private RecipeScrapperUtils() {
    throw new IllegalStateException("This should never be constructed");
  }

  public static String getHost(String url) {
    Matcher matcher = URL_PATTERN.matcher(url);
    if (!matcher.matches()) {
      throw new InvalidURLException("No matches with the URL matcher");
    }
    try {
      return matcher.group("host");
    } catch (Exception e) {
      throw new InvalidURLException("Can't extract [host] from the url");
    }
  }

  public static Map<String, String> getUserHeaders() {
    return USER_HEADERS;
  }

  public static String normalize(String input) {
    input = StringEscapeUtils.unescapeHtml4(input);
    input = input.replace("\\xa0", " ").replace("\n", " ").replace("\t", " ").trim();
    return input.replace("\\s+", " ");
  }

  public static Optional<Integer> extractMinutesForSchemaOrg(String minutesStr) {
    if (minutesStr.startsWith("P") && minutesStr.contains("T")) {
      minutesStr = minutesStr.split("T", 1)[1];
    }
    // formats like 12-15 minutes
    if (minutesStr.contains("-")) {
      minutesStr = minutesStr.split("-", 1)[1];
    }
    if (minutesStr.contains("h")) {
      minutesStr = minutesStr.replace("h", "hours") + "minutes";
    }
    Matcher matcher = SCHMEA_ORG_TIME_PATTERN.matcher(minutesStr);
    if (!matcher.matches()) {
      return Optional.empty();
    }

    int minutes;
    String minuteGrp = matcher.group("minutes");
    minutes = isNullOrEmpty(minuteGrp) ? 0 : Integer.parseInt(minuteGrp);
    String hoursGrp = matcher.group("hours");
    minutes += 60 * (isNullOrEmpty(hoursGrp) ? 0 : Integer.parseInt(hoursGrp));

    return Optional.of(minutes);
  }

  private static boolean isNullOrEmpty(String val) {
    return val == null || val.isEmpty();
  }
}
