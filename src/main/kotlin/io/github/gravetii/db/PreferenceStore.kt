package io.github.gravetii.db

import io.github.gravetii.model.GameTime
import io.github.gravetii.theme.ThemeType
import java.util.prefs.Preferences

object PreferenceStore {

    private const val CURRENT_THEME_KEY = "theme"
    private const val GAME_TIME_KEY = "time"
    private const val GAME_ID_KEY = "game_id"

    private const val DEFAULT_GAME_TIME = "5_0"
    private const val DEFAULT_GAME_ID = "1"

    private val store: MutableMap<String, Any> = hashMapOf()

    private val preferences = Preferences.userNodeForPackage(javaClass)

    fun saveTheme(type: ThemeType) = preferences.put(CURRENT_THEME_KEY, type.name)

    fun getTheme(): ThemeType {
        val theme = preferences.get(CURRENT_THEME_KEY, ThemeType.RANDOM.name)
        return ThemeType.valueOf(theme)
    }

    fun close() = preferences.flush()

    fun getGameTime(): GameTime {
        val result = store.getOrPut(GAME_TIME_KEY) {
            preferences.get(GAME_TIME_KEY, DEFAULT_GAME_TIME)
        }

        return GameTime.from(result.toString())
    }

    fun setGameTime(value: GameTime) {
        val x = value.toString()
        preferences.put(GAME_TIME_KEY, x)
        store[GAME_TIME_KEY] = x
    }

    fun getGameId(): Int {
        val curr = store.getOrPut(GAME_ID_KEY) {
            preferences.get(GAME_ID_KEY, DEFAULT_GAME_ID);
        }.toString()

        val id = curr.toInt()
        val value = id + 1
        preferences.put(GAME_ID_KEY, value.toString())
        store[GAME_ID_KEY] = value.toString()
        return id
    }

    fun resetGameId() {
        preferences.put(GAME_ID_KEY, DEFAULT_GAME_TIME)
        store[GAME_ID_KEY] = DEFAULT_GAME_ID
    }

}