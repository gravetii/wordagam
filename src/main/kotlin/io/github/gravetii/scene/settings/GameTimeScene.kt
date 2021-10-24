package io.github.gravetii.scene.settings

import io.github.gravetii.scene.FxDimensions
import io.github.gravetii.scene.FxScene
import javafx.geometry.Dimension2D
import javafx.stage.Stage

class GameTimeScene(stage: Stage) : FxScene(stage) {

    private val component = GameTimeComponent(stage)

    override fun build() = showCenter(component)

    override fun title() = "Configure game time"

    override fun preferredDimensions(): FxDimensions = FxDimensions(Dimension2D(500.0, 180.0))

}