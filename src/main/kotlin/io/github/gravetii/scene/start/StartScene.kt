package io.github.gravetii.scene.start

import io.github.gravetii.scene.FxDimensions
import io.github.gravetii.scene.FxScene
import io.github.gravetii.scene.menu.MenuBarComponent
import javafx.geometry.Dimension2D
import javafx.stage.Stage

class StartScene(stage: Stage) : FxScene(stage) {

    private val menuBarComponent = MenuBarComponent(stage, root)
    private val startImageComponent = StartImageComponent(root)
    private val startControlsComponent = StartControlsComponent(menuBarComponent)

    override fun build() {
        showTop(menuBarComponent)
        showCenter(startImageComponent)
        showRight(startControlsComponent)
    }

    override fun title() = "-WORDAGAM-"

    override fun preferredDimensions(): FxDimensions {
        val def = Dimension2D(960.0, 630.0)
        val min = Dimension2D(960.0, 630.0)
        val max = Dimension2D(1100.0, 732.0)
        return FxDimensions(def, min, max)
    }

}