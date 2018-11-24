package io.github.gravetii.themes;

public enum ThemeType {
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
  RANDOM;

  private static final String THEME_BASE_PATH = "themes/files";

  public String getImgPath() {
    return THEME_BASE_PATH + "/" + this.name().toLowerCase() + ".jpg";
  }
}
