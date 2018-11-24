package io.github.gravetii.theme;

import io.github.gravetii.store.PreferenceStore;

public class CurrentTheme {

  private static ThemeType type = PreferenceStore.getTheme();

  public static void set(ThemeType value) {
    type = value;
    PreferenceStore.saveTheme(type);
  }

  public static ThemeType get() {
    return type;
  }
}
