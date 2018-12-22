package io.github.gravetii.controller;

import io.github.gravetii.pojo.GameTime;
import javafx.util.Pair;

public class GameTimeValidator {

  public GameTime validate(Pair<String, String> obj) {
    return this.gameTimeParser()
            .validate(this.integerParser()
                    .validate(this.isNumberRule()
                            .validate(this.nonEmptyRule()
                                    .validate(obj))));
  }

  private ValidationRule<Pair<String, String>, Pair<String, String>> nonEmptyRule() {
    return obj -> {
      if (obj != null) {
        String min = obj.getKey().isEmpty() ? "0" : obj.getKey();
        String sec = obj.getValue().isEmpty() ? "0" : obj.getValue();
        return new Pair<>(min, sec);
      }

      return null;
    };
  }

  private ValidationRule<Pair<String, String>, Pair<String, String>> isNumberRule() {
    return obj -> {
      if (obj != null) {
        String min = obj.getKey();
        String sec = obj.getValue();
        return (min.matches("[0-9]+") && sec.matches("[0-9]+")) ? obj : null;
      }

      return null;
    };
  }

  private ValidationRule<Pair<String, String>, Pair<Integer, Integer>> integerParser() {
    return obj -> {
      if (obj != null) {
        try {
          int minutes = Integer.parseInt(obj.getKey());
          int seconds = Integer.parseInt(obj.getValue());
          return new Pair<>(minutes, seconds);
        } catch (NumberFormatException e) {
          return null;
        }
      }

      return null;
    };
  }

  private ValidationRule<Pair<Integer, Integer>, GameTime> gameTimeParser() {
    return obj -> {
      if (obj != null) {
        int minutes = obj.getKey();
        int seconds = obj.getValue();
        if (minutes == 0 && seconds == 0) {
          return null;
        }

        if (seconds < 0 || seconds > 60) {
          return null;
        }

        return new GameTime(minutes, seconds);
      }

      return null;
    };
  }



}
