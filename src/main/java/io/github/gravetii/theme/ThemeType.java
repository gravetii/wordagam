package io.github.gravetii.theme;

public enum ThemeType {
  RANDOM,
  AZURE,
  BLACK_AND_WHITE,
  BLUEZY,
  EUANTHE,
  CONTRA,
  MOTLEY,
  MOSSY,
  SUNSET,
  TERRA,
  VERDURA,
  PATCHY,
  ;

  public String getShowableName() {
    return this.name().replace("_", " ");
  }

  public String getImgPath() {
    return "/theme/" + this.name().toLowerCase() + ".jpg";
  }

  public String getCssPath() {
    return "/theme/" + this.name().toLowerCase() + ".css";
  }
}
