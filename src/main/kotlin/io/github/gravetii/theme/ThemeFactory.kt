package io.github.gravetii.theme

import io.github.gravetii.db.PreferenceStore
import javafx.stage.Window
import kotlin.random.Random

object ThemeFactory {

    private val themeMap: MutableMap<ThemeType, Theme> = mutableMapOf()

    var current: ThemeType = PreferenceStore.getTheme()
        set(current) {
            field = current
            PreferenceStore.saveTheme(current)
        }

    private var currentRandomTheme: Theme? = null

    init {
        for (type in ThemeType.values()) themeMap[type] = Theme(type)
    }

    fun get(type: ThemeType): Theme = themeMap[type]!!

    private fun newRandom(): Theme {
        val r = Random.nextInt(1, ThemeType.values().size)
        val type = ThemeType.values()[r]
        return themeMap[type]!!
    }

    fun loadCurrentTheme(newIfRandom: Boolean): Theme {
        return if (current == ThemeType.RANDOM) {
            if (currentRandomTheme == null || newIfRandom) currentRandomTheme = newRandom()
            currentRandomTheme!!
        } else themeMap[current]!!
    }

    fun getAll(): List<ThemeType> = ThemeType.values().toList()

    fun changeTheme(type: ThemeType): Boolean {
        return if (type != current) {
            current = type
            true
        } else false
    }

    fun dispatch(window: Window) = window.scene.root.fireEvent(Theme.Companion.ChangeEvent())

}