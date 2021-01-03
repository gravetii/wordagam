package io.github.gravetii.theme;

import javafx.stage.Window;

import java.util.List;

public class ThemeService {
  private final ThemeFactory factory;

  public ThemeService() {
    this.factory = ThemeFactory.get();
  }

  public Theme loadTheme(ThemeType type) {
    return this.factory.get(type);
  }

  public Theme loadCurrentTheme(boolean newIfRandom) {
    return this.factory.loadCurrentTheme(newIfRandom);
  }

  public List<ThemeType> getAll() {
    return this.factory.getAll();
  }

  public ThemeType getCurrent() {
    return this.factory.getCurrent();
  }

  public boolean changeTheme(ThemeType type) {
    if (type != this.factory.getCurrent()) {
      this.factory.setCurrent(type);
      return true;
    }

    return false;
  }

  public void dispatch(Window window) {
    window.getScene().getRoot().fireEvent(new Theme.ChangeEvent());
  }
}
