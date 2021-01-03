package io.github.gravetii.theme;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.image.Image;

public class Theme {
  private final ThemeType type;
  private final String imgPath;

  private Image img;
  private String styleSheet;

  public Theme(ThemeType type, String imgPath) {
    this.type = type;
    this.imgPath = imgPath;
  }

  public ThemeType getType() {
    return this.type;
  }

  public Image getImage() {
    if (this.img == null) {
      String path = getClass().getResource(this.imgPath).toExternalForm();
      this.img = new Image(path);
    }

    return this.img;
  }

  public String getStyleSheet() {
    if (this.styleSheet == null) {
      this.styleSheet = getClass().getResource(type.getCssPath()).toExternalForm();
    }

    return this.styleSheet;
  }

  public static class ChangeEvent extends Event {
    public static final EventType<ChangeEvent> THEME_CHANGE_EVENT_TYPE =
        new EventType<>(Event.ANY, "theme-change");

    public ChangeEvent() {
      super(THEME_CHANGE_EVENT_TYPE);
    }
  }
}
