package io.github.gravetii.theme;

import io.github.gravetii.db.PreferenceStore;
import io.github.gravetii.util.AppLogger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ThemeFactory {
  private static volatile ThemeFactory INSTANCE;

  private ThemeType current;
  private Theme currentRandomTheme;

  private final Map<ThemeType, Theme> themeMap;

  private ThemeFactory() {
    this.themeMap = new ConcurrentHashMap<>();
    this.initAllThemes();
    this.current = PreferenceStore.getTheme();
    this.currentRandomTheme = null;
  }

  public static ThemeFactory get() {
    if (INSTANCE == null) {
      synchronized (ThemeFactory.class) {
        if (INSTANCE == null) {
          INSTANCE = new ThemeFactory();
          AppLogger.fine(ThemeFactory.class.getCanonicalName(), "Created instance of ThemeFactory");
        }
      }
    }

    return INSTANCE;
  }

  private void initAllThemes() {
    for (ThemeType type : ThemeType.values()) {
      this.themeMap.put(type, new Theme(type, type.getImgPath()));
    }
  }

  public Theme get(ThemeType type) {
    return this.themeMap.get(type);
  }

  public ThemeType getCurrent() {
    return this.current;
  }

  public void setCurrent(ThemeType current) {
    this.current = current;
    PreferenceStore.saveTheme(current);
  }

  public Theme loadCurrentTheme() {
    if (this.current == ThemeType.RANDOM) {
      if (this.currentRandomTheme == null) this.currentRandomTheme = this.newRandom();
      return this.currentRandomTheme;
    }

    return this.get(this.current);
  }

  public Theme loadNewCurrentTheme() {
    if (this.current == ThemeType.RANDOM) {
      this.currentRandomTheme = this.newRandom();
      return this.currentRandomTheme;
    }

    return this.get(this.current);
  }

  private Theme newRandom() {
    int r = ThreadLocalRandom.current().nextInt(1, ThemeType.values().length);
    ThemeType type = ThemeType.values()[r];
    return this.get(type);
  }

  public List<ThemeType> getAll() {
    return Arrays.asList(ThemeType.values());
  }
}
