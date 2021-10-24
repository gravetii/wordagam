package io.github.gravetii.scene.help

import io.github.gravetii.scene.FxDimensions
import io.github.gravetii.scene.FxScene
import javafx.geometry.Dimension2D
import javafx.stage.Stage

class AboutScene(stage: Stage) : FxScene(stage) {

    private val component = AboutComponent()

    override fun build() = showCenter(component)

    override fun title() = "About"

    override fun preferredDimensions(): FxDimensions = FxDimensions(Dimension2D(500.0, 200.0))

}