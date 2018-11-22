package io.github.gravetii.themes;

import io.github.gravetii.util.AppLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ThemeFactory {

  private static volatile ThemeFactory INSTANCE;

  private Map<ThemeType, Theme> themeMap;
  private List<Theme> themeList;

  private ThemeFactory() {
    this.themeMap = new ConcurrentHashMap<>();
    this.themeList = this.initAllThemes();
    this.themeList.forEach(
        theme -> {
          this.themeMap.put(theme.getType(), theme);
        });
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

  private List<Theme> initAllThemes() {
    List<Theme> themes = new ArrayList<>();
    themes.add(new Theme(ThemeType.RANDOM, ThemeType.RANDOM.getImgPath()));
    themes.add(new Theme(ThemeType.AZURE, ThemeType.AZURE.getImgPath()));
    themes.add(new Theme(ThemeType.BEACH, ThemeType.BEACH.getImgPath()));
    themes.add(new Theme(ThemeType.BLACK_AND_WHITE, ThemeType.BLACK_AND_WHITE.getImgPath()));
    themes.add(new Theme(ThemeType.BLUEZY, ThemeType.BLUEZY.getImgPath()));
    themes.add(new Theme(ThemeType.EUANTHE, ThemeType.EUANTHE.getImgPath()));
    themes.add(new Theme(ThemeType.GARNET, ThemeType.GARNET.getImgPath()));
    themes.add(new Theme(ThemeType.GOLDEN_SKY, ThemeType.GOLDEN_SKY.getImgPath()));
    themes.add(new Theme(ThemeType.HORIZON, ThemeType.HORIZON.getImgPath()));
    themes.add(new Theme(ThemeType.KAITO, ThemeType.KAITO.getImgPath()));
    themes.add(new Theme(ThemeType.LAVENDER, ThemeType.LAVENDER.getImgPath()));
    themes.add(new Theme(ThemeType.RED_DAWN, ThemeType.RED_DAWN.getImgPath()));
    themes.add(new Theme(ThemeType.SUNSET, ThemeType.SUNSET.getImgPath()));
    themes.add(new Theme(ThemeType.TERRA, ThemeType.TERRA.getImgPath()));
    themes.add(new Theme(ThemeType.URIEL, ThemeType.URIEL.getImgPath()));
    return themes;
  }

  public Theme get(ThemeType type) {
    return this.themeMap.get(type);
  }

  public Theme current() {
    ThemeType current = Theme.getCurrent();
    if (current == null || current == ThemeType.RANDOM) {
      return this.random();
    } else {
      return this.themeMap.get(current);
    }
  }

  public Theme random() {
    ThemeType[] allThemeTypes = ThemeType.values();
    int count = ThemeType.values().length;
    int r = ThreadLocalRandom.current().nextInt(1, count);
    ThemeType type = allThemeTypes[r];
    return this.get(type);
  }

  public List<Theme> getAll() {
    return this.themeList;
  }
}
