package io.github.gravetii.scene.settings

import io.github.gravetii.controller.GameTimeController
import io.github.gravetii.scene.FxComponent
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class GameTimeComponent(stage: Stage) : FxComponent<GameTimeController, AnchorPane>("gameTime.fxml") {

    override val controller: GameTimeController = GameTimeController(stage)

    override val node: AnchorPane = loadNode()

}