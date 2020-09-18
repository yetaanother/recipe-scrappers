package com.prashantchaubey.recipescrappers.utils;

import com.prashantchaubey.recipescrappers.exceptions.InvalidURLException;
import org.apache.commons.text.StringEscapeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RecipeScrapperUtils {
  private static final Pattern URL_PATTERN =
      Pattern.compile(
          "^((?<schema>.+?)://)?((?<user>.+?)(:(?<password>.*?))?@)?(?<host>.*?)(:(?<port>\\d+?))?(?<path>/.*?)?(?<query>[?].*?)?$");

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
}
