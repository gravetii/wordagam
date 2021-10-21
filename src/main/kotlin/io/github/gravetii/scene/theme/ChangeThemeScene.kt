package io.github.gravetii.scene.theme

import io.github.gravetii.scene.FxDimensions
import io.github.gravetii.scene.FxScene
import javafx.geometry.Dimension2D
import javafx.stage.Stage

class ChangeThemeScene(stage: Stage) : FxScene(stage) {

    private val component = ChangeThemeComponent(stage)

    override fun build() {
        showCenter(component)
    }

    override fun title() = "Change theme"

    override fun preferredDimensions(): FxDimensions {
        val def = Dimension2D(800.0, 550.0)
        return FxDimensions(def, def, def)
    }

}