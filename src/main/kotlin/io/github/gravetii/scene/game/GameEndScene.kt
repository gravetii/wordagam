package io.github.gravetii.scene.game

import io.github.gravetii.scene.FxScene
import io.github.gravetii.scene.menu.MenuBarComponent
import javafx.stage.Stage

class GameEndScene(stage: Stage, private val gameGridComponent: GameGridComponent) : FxScene(stage) {

    private val menuBarComponent = MenuBarComponent(stage, root)
    private val gameEndResultComponent = GameEndResultComponent(gameGridComponent)

    override fun build() {
        showTop(menuBarComponent)
        showCenter(gameGridComponent)
        showRight(gameEndResultComponent)
    }

    override fun title() = "Game Over"

}