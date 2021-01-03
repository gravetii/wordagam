package io.github.gravetii.db;

import io.github.gravetii.model.GameTime;
import io.github.gravetii.theme.ThemeType;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class PreferenceStore {
  private static final String CURRENT_THEME_KEY = "theme";
  private static final String GAME_TIME_KEY = "time";
  private static final String GAME_ID_KEY = "game_id";

  private static final String DEFAULT_GAME_TIME = "5$0";
  private static final String DEFAULT_GAME_ID = "1";

  private static final Map<String, Object> store = new HashMap<>();

  private static final Preferences preferences =
      Preferences.userNodeForPackage(PreferenceStore.class);

  public static void saveTheme(ThemeType type) {
    preferences.put(CURRENT_THEME_KEY, type.name());
  }

  public static ThemeType getTheme() {
    String theme = preferences.get(CURRENT_THEME_KEY, ThemeType.RANDOM.name());
    return ThemeType.valueOf(theme);
  }

  public static void close() throws Exception {
    preferences.flush();
  }

  public static GameTime getGameTime() {
    String time;
    if (store.containsKey(GAME_TIME_KEY)) {
      time = (String) store.get(GAME_TIME_KEY);
    } else {
      time = preferences.get(GAME_TIME_KEY, DEFAULT_GAME_TIME);
      store.put(GAME_TIME_KEY, time);
    }

    return GameTime.from(time);
  }

  public static void setGameTime(GameTime value) {
    preferences.put(GAME_TIME_KEY, value.toString());
    store.put(GAME_TIME_KEY, value.toString());
  }

  public static int getGameId() {
    String value;
    if (store.containsKey(GAME_ID_KEY)) {
      value = (String) store.get(GAME_ID_KEY);
    } else {
      value = preferences.get(GAME_ID_KEY, DEFAULT_GAME_ID);
    }

    int id = Integer.parseInt(value);
    String incrValue = Integer.toString(id + 1);
    preferences.put(GAME_ID_KEY, incrValue);
    store.put(GAME_ID_KEY, incrValue);
    return id;
  }

  public static void resetGameId() {
    preferences.put(GAME_ID_KEY, DEFAULT_GAME_ID);
    store.put(GAME_ID_KEY, DEFAULT_GAME_ID);
  }
}
