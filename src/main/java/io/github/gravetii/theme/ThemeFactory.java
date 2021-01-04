package io.github.gravetii.theme;

import io.github.gravetii.db.PreferenceStore;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class ThemeFactory {

  private static final Logger logger = Logger.getLogger(ThemeFactory.class.getCanonicalName());

  private static volatile ThemeFactory INSTANCE;

  private final Map<ThemeType, Theme> themeMap = new ConcurrentHashMap<>();

  private ThemeType current;
  private Theme currentRandomTheme;

  private ThemeFactory() {
    for (ThemeType type: ThemeType.values()) this.themeMap.put(type, new Theme(type));
    this.current = PreferenceStore.getTheme();
    this.currentRandomTheme = null;
  }

  public static ThemeFactory get() {
    if (INSTANCE == null) {
      synchronized (ThemeFactory.class) {
        if (INSTANCE == null) {
          INSTANCE = new ThemeFactory();
          logger.fine("Created instance of ThemeFactory");
        }
      }
    }

    return INSTANCE;
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

  public Theme loadCurrentTheme(boolean newIfRandom) {
    if (this.current == ThemeType.RANDOM) {
      if (this.currentRandomTheme == null || newIfRandom) this.currentRandomTheme = newRandom();
      return this.currentRandomTheme;
    }

    return this.themeMap.get(this.current);
  }

  private Theme newRandom() {
    int r = ThreadLocalRandom.current().nextInt(1, ThemeType.values().length);
    ThemeType type = ThemeType.values()[r];
    return this.themeMap.get(type);
  }

  public List<ThemeType> getAll() {
    return Arrays.asList(ThemeType.values());
  }
}
