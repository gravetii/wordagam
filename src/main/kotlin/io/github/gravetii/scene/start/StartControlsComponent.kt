package io.github.gravetii.scene.start

import io.github.gravetii.controller.StartControlsController
import io.github.gravetii.scene.FxComponent
import io.github.gravetii.scene.menu.MenuBarComponent
import javafx.scene.layout.VBox

class StartControlsComponent(ref: MenuBarComponent) :
    FxComponent<StartControlsController, VBox>("startControls.fxml") {

    override val controller: StartControlsController = StartControlsController(ref.controller)

    override val node: VBox = loadNode()

}