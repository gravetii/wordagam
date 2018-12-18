package io.github.gravetii.store;

import io.github.gravetii.pojo.GameTime;
import io.github.gravetii.theme.ThemeType;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class Settings {

  private static final String CURRENT_THEME_KEY = "theme";
  private static final String GAME_TIME_KEY = "time";

  private static final String DEFAULT_GAME_TIME = "5$0";

  private static final Map<String, String> store = new HashMap<>();

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

  public static GameTime getGameTime() {
    String time;
    if (store.containsKey(GAME_TIME_KEY)) {
      time = store.get(GAME_TIME_KEY);
    } else {
      time = preferences().get(GAME_TIME_KEY, DEFAULT_GAME_TIME);
      store.put(GAME_TIME_KEY, time);
    }

    return GameTime.from(time);
  }

  public static void setGameTime(GameTime value) {
    preferences().put(GAME_TIME_KEY, value.to());
    store.put(GAME_TIME_KEY, value.to());
  }
}
