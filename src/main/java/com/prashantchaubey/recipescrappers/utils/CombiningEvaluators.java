package com.prashantchaubey.recipescrappers.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

import java.util.Arrays;

public class CombiningEvaluators {
  public static Evaluator and(Evaluator... evaluators) {
    return new Evaluator() {
      @Override
      public boolean matches(Element root, Element element) {
        return Arrays.stream(evaluators).allMatch(evaluator -> evaluator.matches(root, element));
      }
    };
  }

  public static Evaluator or(Evaluator... evaluators) {
    return new Evaluator() {
      @Override
      public boolean matches(Element root, Element element) {
        return Arrays.stream(evaluators).anyMatch(evaluator -> evaluator.matches(root, element));
      }
    };
  }
}
