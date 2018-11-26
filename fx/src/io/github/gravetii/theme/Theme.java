package io.github.gravetii.theme;

import io.github.gravetii.App;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Theme {

  private ThemeType type;
  private String imgPath;
  private Image img;
  private String name;
  private Background gameGridBackground;

  public Theme(ThemeType type, String imgPath) {
    this.type = type;
    this.imgPath = imgPath;
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

  public Background getGameGridBackground() {
    if (this.gameGridBackground == null) {
      BackgroundSize size = new BackgroundSize(100, 100, true, true, true, true);
      BackgroundImage image =
          new BackgroundImage(
              this.getImage(),
              BackgroundRepeat.NO_REPEAT,
              BackgroundRepeat.NO_REPEAT,
              BackgroundPosition.DEFAULT,
              size);
      this.gameGridBackground = new Background(image);
    }

    return this.gameGridBackground;
  }
}
