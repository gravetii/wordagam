package io.github.gravetii.scene.game

import io.github.gravetii.controller.GameEndResultController
import io.github.gravetii.scene.FxComponent
import javafx.scene.layout.VBox

class GameEndResultComponent(private val ref: GameGridComponent) :
    FxComponent<GameEndResultController, VBox>("gameEndResult.fxml") {

    override val controller: GameEndResultController = GameEndResultController(ref.controller)

    override val node: VBox = loadNode()

    init {
        updateStats()
    }

    private fun updateStats() {
        val stats = ref.computeStats()
        controller.updateText(stats.toString())
    }

}