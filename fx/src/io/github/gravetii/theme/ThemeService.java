package io.github.gravetii.theme;

import java.util.List;

public class ThemeService {

  private ThemeFactory factory;

  public ThemeService() {
    this.factory = ThemeFactory.get();
  }

  public Theme get(ThemeType type) {
    return this.factory.get(type);
  }

  public Theme loadCurrentTheme() {
    return this.factory.loadCurrentTheme();
  }

  public List<ThemeType> getAll() {
    return this.factory.getAll();
  }

  public ThemeType getCurrent() {
    return this.factory.getCurrent();
  }

  public void setCurrent(ThemeType type) {
    this.factory.setCurrent(type);
  }

  public int count() {
    return this.getAll().size();
  }
}
