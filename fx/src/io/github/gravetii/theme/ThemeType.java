package io.github.gravetii.theme;

public enum ThemeType {
  RANDOM,
  KAITO,
  BLACK_AND_WHITE,
  AZURE,
  SUNSET,
  BEACH,
  RED_DAWN,
  TERRA,
  BLUEZY,
  HORIZON,
  URIEL,
  EUANTHE,
  LAVENDER,
  GOLDEN_SKY,
  ;

  public String getShowableName() {
    return this.name().replace("_", " ");
  }

  public String getImgPath() {
    return "files/" + this.name().toLowerCase() + ".jpg";
  }

  public String getCssPath() {
    return "css/" + this.name().toLowerCase() + ".css";
  }
}
