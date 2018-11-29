package io.github.gravetii.theme;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.image.Image;

public class Theme {

  private ThemeType type;
  private String imgPath;
  private Image img;
  private String name;
  private String styleSheet;

  public Theme(ThemeType type, String imgPath) {
    this.type = type;
    this.imgPath = imgPath;
  }

  public ThemeType getType() {
    return this.type;
  }

  public String getImgPath() {
    return this.imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  public Image getImage() {
    if (this.img == null) {
      this.img = new Image(Theme.class.getResourceAsStream(this.imgPath), 0, 0, false, false);
    }

    return this.img;
  }

  public String getStyleSheet() {
    if (this.styleSheet == null) {
      this.styleSheet = Theme.class.getResource(this.type.getCssPath()).toExternalForm();
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
