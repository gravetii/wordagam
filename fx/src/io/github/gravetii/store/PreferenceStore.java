package io.github.gravetii.store;

import io.github.gravetii.themes.ThemeType;

import java.util.prefs.Preferences;

public class PreferenceStore {

  private static final String CURRENT_THEME_KEY = "theme";

  public static void saveTheme(ThemeType type) {
    Preferences.userNodeForPackage(PreferenceStore.class).put(CURRENT_THEME_KEY, type.name());
  }

  public static ThemeType getTheme() {
    String theme =
        Preferences.userNodeForPackage(PreferenceStore.class)
            .get(CURRENT_THEME_KEY, ThemeType.RANDOM.name());
    return ThemeType.valueOf(theme);
  }

  public static void close() throws Exception {
    Preferences.userNodeForPackage(PreferenceStore.class).flush();
  }
}
