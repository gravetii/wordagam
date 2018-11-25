package io.github.gravetii.theme;

import io.github.gravetii.util.AppLogger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ThemeFactory {

  private static volatile ThemeFactory INSTANCE;

  private Map<ThemeType, Theme> themeMap;

  private ThemeFactory() {
    this.themeMap = new ConcurrentHashMap<>();
    this.initAllThemes();
  }

  public static ThemeFactory get() {
    if (INSTANCE == null) {
      synchronized (ThemeFactory.class) {
        if (INSTANCE == null) {
          INSTANCE = new ThemeFactory();
          AppLogger.info(ThemeFactory.class.getCanonicalName(), "Created instance of ThemeFactory");
        }
      }
    }

    return INSTANCE;
  }

  private void initAllThemes() {
    this.themeMap.put(ThemeType.RANDOM, new Theme(ThemeType.RANDOM, ThemeType.RANDOM.getImgPath()));
    this.themeMap.put(ThemeType.AZURE, new Theme(ThemeType.AZURE, ThemeType.AZURE.getImgPath()));
    this.themeMap.put(ThemeType.BEACH, new Theme(ThemeType.BEACH, ThemeType.BEACH.getImgPath()));
    this.themeMap.put(
        ThemeType.BLACK_AND_WHITE,
        new Theme(ThemeType.BLACK_AND_WHITE, ThemeType.BLACK_AND_WHITE.getImgPath()));
    this.themeMap.put(ThemeType.BLUEZY, new Theme(ThemeType.BLUEZY, ThemeType.BLUEZY.getImgPath()));
    this.themeMap.put(
        ThemeType.EUANTHE, new Theme(ThemeType.EUANTHE, ThemeType.EUANTHE.getImgPath()));
    this.themeMap.put(
        ThemeType.GOLDEN_SKY, new Theme(ThemeType.GOLDEN_SKY, ThemeType.GOLDEN_SKY.getImgPath()));
    this.themeMap.put(
        ThemeType.HORIZON, new Theme(ThemeType.HORIZON, ThemeType.HORIZON.getImgPath()));
    this.themeMap.put(ThemeType.KAITO, new Theme(ThemeType.KAITO, ThemeType.KAITO.getImgPath()));
    this.themeMap.put(
        ThemeType.LAVENDER, new Theme(ThemeType.LAVENDER, ThemeType.LAVENDER.getImgPath()));
    this.themeMap.put(
        ThemeType.RED_DAWN, new Theme(ThemeType.RED_DAWN, ThemeType.RED_DAWN.getImgPath()));
    this.themeMap.put(ThemeType.SUNSET, new Theme(ThemeType.SUNSET, ThemeType.SUNSET.getImgPath()));
    this.themeMap.put(ThemeType.TERRA, new Theme(ThemeType.TERRA, ThemeType.TERRA.getImgPath()));
    this.themeMap.put(ThemeType.URIEL, new Theme(ThemeType.URIEL, ThemeType.URIEL.getImgPath()));
  }

  public Theme get(ThemeType type) {
    return this.themeMap.get(type);
  }

  public Theme current() {
    ThemeType current = CurrentTheme.get();
    if (current == null || current == ThemeType.RANDOM) {
      return this.random();
    } else {
      return this.themeMap.get(current);
    }
  }

  private Theme random() {
    ThemeType[] allThemeTypes = ThemeType.values();
    int count = ThemeType.values().length;
    int r = ThreadLocalRandom.current().nextInt(1, count);
    ThemeType type = allThemeTypes[r];
    return this.get(type);
  }

  public List<ThemeType> getAll() {
    return Arrays.asList(ThemeType.values());
  }
}
