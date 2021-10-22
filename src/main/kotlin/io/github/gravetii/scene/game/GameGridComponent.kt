package io.github.gravetii.scene.game

import io.github.gravetii.controller.GameGridController
import io.github.gravetii.game.Game
import io.github.gravetii.scene.FxComponent
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane

class GameGridComponent(private val game: Game) :
    FxComponent<GameGridController, GridPane>("gameGrid.fxml") {

    override val controller: GameGridController = GameGridController(game)

    override val node: GridPane = createNode()

    private fun createNode(): GridPane {
        val gridPane = loadNode()
        val panes = gridPane.children.iterator()
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val unit = game.grid[i][j]
                val pane = panes.next() as Pane
                val imgView = pane.children[0] as ImageView
                imgView.image = unit.image
                imgView.fitWidthProperty().bind(pane.widthProperty())
                imgView.fitHeightProperty().bind(pane.heightProperty())
            }
        }

        return gridPane
    }

    fun endGame() = controller.endGame()

    fun computeStats() = controller.computeStats()

}