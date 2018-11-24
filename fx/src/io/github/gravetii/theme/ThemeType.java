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

  private static final String THEME_BASE_PATH = "theme/files";

  public String getImgPath() {
    return THEME_BASE_PATH + "/" + this.name().toLowerCase() + ".jpg";
  }
}
