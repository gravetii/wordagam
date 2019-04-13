package io.github.gravetii.theme;

public enum ThemeType {
  RANDOM,
  AZURE,
  BLACK_AND_WHITE,
  BLUEZY,
  EUANTHE,
  GOLDEN_SKY,
  HORIZON,
  LAVENDER,
  RED_DAWN,
  SUNSET,
  TERRA,
  URIEL,
  ;

  public String getShowableName() {
    return this.name().replace("_", " ");
  }

  public String getImgPath() {
    return "theme/files/" + this.name().toLowerCase() + ".jpg";
  }

  public String getCssPath() {
    return "theme/css/" + this.name().toLowerCase() + ".css";
  }
}
