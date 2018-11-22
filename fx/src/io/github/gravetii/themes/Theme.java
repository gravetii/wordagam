package io.github.gravetii.themes;

import io.github.gravetii.App;
import javafx.scene.image.Image;

public class Theme {

  private static volatile ThemeType current = ThemeType.RANDOM;

  private ThemeType type;
  private String imgPath;
  private Image img;
  private String name;

  public Theme(ThemeType type, String imgPath) {
    this.type = type;
    this.imgPath = imgPath;
  }

  public static ThemeType getCurrent() {
    return current;
  }

  public static void setCurrent(ThemeType type) {
    current = type;
  }

  public ThemeType getType() {
    return type;
  }

  public String getImgPath() {
    return imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  public Image getImage() {
    if (this.img == null) {
      this.img = new Image(App.class.getResourceAsStream(this.imgPath), 0, 0, false, false);
    }

    return this.img;
  }

  public String getShowableName() {
    return this.type.name().replace("_", " ");
  }
}
