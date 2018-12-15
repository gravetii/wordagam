package io.github.gravetii.store;

import io.github.gravetii.theme.ThemeType;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class Settings {

  private static final String CURRENT_THEME_KEY = "theme";
  private static final String GAME_TIME_KEY = "game_time";

  private static final String DEFAULT_GAME_TIME_SECONDS = "60.0";

  private static final Map<String, Object> store = new HashMap<>();

  private static Preferences preferences() {
    return Preferences.userNodeForPackage(Settings.class);
  }

  public static void saveTheme(ThemeType type) {
    preferences().put(CURRENT_THEME_KEY, type.name());
  }

  public static ThemeType getTheme() {
    String theme = preferences().get(CURRENT_THEME_KEY, ThemeType.RANDOM.name());
    return ThemeType.valueOf(theme);
  }

  public static void close() throws Exception {
    preferences().flush();
  }

  public static double getGameTime() {
    String time =
        (String)
            store.getOrDefault(
                GAME_TIME_KEY, preferences().get(GAME_TIME_KEY, DEFAULT_GAME_TIME_SECONDS));
    return Double.valueOf(time);
  }

  public static void setGameTime(double value) {
    String valueStr = Double.toString(value);
    preferences().put(GAME_TIME_KEY, valueStr);
    store.put(GAME_TIME_KEY, valueStr);
  }
}
