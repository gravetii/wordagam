package io.github.gravetii.theme;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.image.Image;

public class Theme {
  private final ThemeType type;

  private Image image;
  private String styleSheet;

  public Theme(ThemeType type) {
    this.type = type;
  }

  public ThemeType getType() {
    return this.type;
  }

  public Image getImage() {
    if (this.image == null) {
      String path = getClass().getResource(this.type.getImgPath()).toExternalForm();
      this.image = new Image(path);
    }

    return this.image;
  }

  public String getStyleSheet() {
    if (this.styleSheet == null) {
      this.styleSheet = getClass().getResource(type.getStyleSheetPath()).toExternalForm();
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
