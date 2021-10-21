package io.github.gravetii.theme

enum class ThemeType {

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

    fun getDisplayName(): String = name.replace("_", " ")

    fun getImgPath(): String = "/theme/${name.lowercase()}.jpg"

    fun getStyleSheetPath(): String = "/theme/${name.lowercase()}.css"

}