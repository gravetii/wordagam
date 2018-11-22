package io.github.gravetii.themes;

import java.util.List;

public class ThemeService {

  private ThemeFactory factory;

  public ThemeService() {
    this.factory = ThemeFactory.get();
  }

  public Theme get(ThemeType type) {
    return this.factory.get(type);
  }

  public Theme current() {
    return this.factory.current();
  }

  public Theme random() {
    return this.factory.random();
  }

  public List<Theme> getAll() {
    return this.factory.getAll();
  }
}
