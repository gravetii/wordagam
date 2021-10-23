package io.github.gravetii.scene.theme

import io.github.gravetii.controller.ChangeThemeController
import io.github.gravetii.scene.FxComponent
import io.github.gravetii.theme.ThemeFactory
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.stage.Stage

class ChangeThemeComponent(stage: Stage) :
    FxComponent<ChangeThemeController, BorderPane>("changeTheme.fxml") {

    override val controller: ChangeThemeController = ChangeThemeController(stage)

    override val node: BorderPane = createNode()

    private fun createNode(): BorderPane {
        val parent = loadNode()
        val gridPane = parent.top as GridPane
        ThemeFactory.getAll().forEachIndexed { c, type ->
            val theme = ThemeFactory.get(type)
            val borderPane = gridPane.children[c] as BorderPane
            val pane = borderPane.center as Pane
            val imgView = pane.children.first() as ImageView
            imgView.fitWidthProperty().bind(pane.widthProperty())
            imgView.fitHeightProperty().bind(pane.heightProperty())
            imgView.image = theme.image
        }

        return parent
    }

}