package io.github.gravetii.scene.menu

import io.github.gravetii.controller.MenuBarController
import io.github.gravetii.scene.FxComponent
import javafx.scene.control.MenuBar
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class MenuBarComponent(val stage: Stage, val root: BorderPane) :
    FxComponent<MenuBarController, MenuBar>("menuBar.fxml") {

    override val controller: MenuBarController = MenuBarController(stage)

    override val node: MenuBar = createNode()

    private fun createNode(): MenuBar {
        val menuBar = loadNode()
        menuBar.prefWidthProperty().bind(root.widthProperty())
        return menuBar
    }

}