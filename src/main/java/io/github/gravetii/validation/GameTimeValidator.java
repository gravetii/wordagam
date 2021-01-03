package io.github.gravetii.validation;

import io.github.gravetii.model.GameTime;
import io.github.gravetii.util.Pair;
import io.github.gravetii.validation.ValidationRule;

public class GameTimeValidator {
  public GameTime validate(Pair<String, String> obj) {
    return this.gameTimeParser()
        .validate(
            this.integerParser()
                .validate(this.isNumberRule().validate(this.nonEmptyRule().validate(obj))));
  }

  private ValidationRule<Pair<String, String>, Pair<String, String>> nonEmptyRule() {
    return obj -> {
      if (obj == null) return null;
      String min = obj.getFirst().isEmpty() ? "0" : obj.getFirst();
      String sec = obj.getSecond().isEmpty() ? "0" : obj.getSecond();
      return new Pair<>(min, sec);
    };
  }

  private ValidationRule<Pair<String, String>, Pair<String, String>> isNumberRule() {
    return obj -> {
      if (obj == null) return null;
      String min = obj.getFirst();
      String sec = obj.getSecond();
      return (min.matches("[0-9]+") && sec.matches("[0-9]+")) ? obj : null;
    };
  }

  private ValidationRule<Pair<String, String>, Pair<Integer, Integer>> integerParser() {
    return obj -> {
      if (obj == null) return null;
      try {
        int minutes = Integer.parseInt(obj.getFirst());
        int seconds = Integer.parseInt(obj.getSecond());
        return new Pair<>(minutes, seconds);
      } catch (NumberFormatException e) {
        return null;
      }
    };
  }

  private ValidationRule<Pair<Integer, Integer>, GameTime> gameTimeParser() {
    return obj -> {
      if (obj == null) return null;
      int minutes = obj.getFirst();
      int seconds = obj.getSecond();
      if (minutes == 0 && seconds == 0) return null;
      if (seconds < 0 || seconds > 60) return null;
      return new GameTime(minutes, seconds);
    };
  }
}
