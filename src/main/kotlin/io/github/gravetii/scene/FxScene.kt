package io.github.gravetii.scene

import io.github.gravetii.theme.Theme
import io.github.gravetii.theme.ThemeFactory
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

abstract class FxScene(val stage: Stage) {

    protected val root = BorderPane()

    private fun setDimensions() = preferredDimensions()?.setFor(stage)

    private fun applyCurrentTheme() {
        root.stylesheets.clear()
        val styleSheet = loadTheme().stylesheet
        root.stylesheets.add(styleSheet)
    }

    abstract fun build()

    abstract fun title(): String

    open fun preferredDimensions(): FxDimensions? = null

    open fun loadTheme(): Theme = ThemeFactory.loadCurrentTheme(false)

    open fun setEventHandlers() {
        root.addEventHandler(Theme.THEME_CHANGE_EVENT_TYPE) {
            applyCurrentTheme()
            it.consume()
        }
    }

    fun showTop(component: FxComponent<*, *>) {
        root.top = component.node
    }

    fun showBottom(component: FxComponent<*, *>) {
        root.bottom = component.node
    }

    fun showLeft(component: FxComponent<*, *>) {
        root.left = component.node
    }

    fun showRight(component: FxComponent<*, *>) {
        root.right = component.node
    }

    fun showCenter(component: FxComponent<*, *>) {
        root.center = component.node
    }

    fun show() {
        build()
        applyCurrentTheme()
        val scene = Scene(root)
        stage.scene = scene
        stage.title = title()
        setDimensions()
        stage.show()
        setEventHandlers()
    }

}