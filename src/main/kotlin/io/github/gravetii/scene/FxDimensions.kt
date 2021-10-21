package io.github.gravetii.scene

import javafx.geometry.Dimension2D
import javafx.stage.Stage

data class FxDimensions(val def: Dimension2D, val min: Dimension2D? = null, val max: Dimension2D? = null) {

    fun setFor(stage: Stage) {
        stage.width = def.width
        stage.height = def.height
        stage.minWidth = min?.width ?: 0.0
        stage.minHeight = min?.height ?: 0.0
        stage.maxWidth = max?.width ?: Double.MAX_VALUE
        stage.maxHeight = max?.width ?: Double.MAX_VALUE
    }

}