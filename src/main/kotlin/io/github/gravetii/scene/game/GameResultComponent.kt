package io.github.gravetii.scene.game

import io.github.gravetii.controller.GameResultController
import io.github.gravetii.scene.FxComponent
import javafx.scene.layout.VBox

class GameResultComponent(ref: GameGridComponent) : FxComponent<GameResultController, VBox>("gameResult.fxml") {

    override val controller: GameResultController = GameResultController(ref.controller)

    override val node: VBox = loadNode()

}