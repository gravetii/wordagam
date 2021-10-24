package io.github.gravetii.scene.help

import io.github.gravetii.scene.FxDimensions
import io.github.gravetii.scene.FxScene
import javafx.geometry.Dimension2D
import javafx.stage.Stage

class WhatIsItScene(stage: Stage) : FxScene(stage) {

    private val component = WhatIsItComponent()

    override fun build() = showCenter(component)

    override fun title() = "What is it?"

    override fun preferredDimensions(): FxDimensions = FxDimensions(Dimension2D(575.0, 430.0))

}