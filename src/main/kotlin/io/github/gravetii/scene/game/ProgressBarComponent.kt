package io.github.gravetii.scene.game

import io.github.gravetii.controller.ProgressBarController
import io.github.gravetii.scene.FxComponent
import javafx.scene.control.ProgressBar
import javafx.scene.layout.BorderPane

class ProgressBarComponent(private val root: BorderPane) :
    FxComponent<ProgressBarController, ProgressBar>("progressBar.fxml") {

    override val controller: ProgressBarController = ProgressBarController(root)

    override val node: ProgressBar = loadNode()

}