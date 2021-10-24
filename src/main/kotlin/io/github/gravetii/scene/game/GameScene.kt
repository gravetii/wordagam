package io.github.gravetii.scene.game

import io.github.gravetii.db.PreferenceStore
import io.github.gravetii.game.Game
import io.github.gravetii.game.GameFactory
import io.github.gravetii.scene.FxScene
import io.github.gravetii.scene.menu.MenuBarComponent
import io.github.gravetii.theme.ThemeFactory
import javafx.application.Platform
import javafx.stage.Stage
import java.lang.RuntimeException

class GameScene(stage: Stage) : FxScene(stage) {

    private val game = GameFactory.fetch()
    private val gridComponent = GameGridComponent(game)
    private val resultComponent = GameResultComponent(gridComponent)
    private val progressBarComponent = ProgressBarComponent(root)
    private val menuBarComponent = MenuBarComponent(stage, root)

    override fun build() {
        showTop(menuBarComponent)
        showCenter(gridComponent)
        showRight(resultComponent)
        showBottom(progressBarComponent)
    }

    private fun endGame() {
        gridComponent.endGame()
        showEndGameScene()
        Game.isInstanceRunning = false
    }

    override fun loadTheme() = ThemeFactory.loadCurrentTheme(true)

    override fun setEventHandlers() {
        super.setEventHandlers()
        root.addEventHandler(Game.GAME_END_EVENT_EVENT_TYPE) {
            endGame()
            it.consume()
        }
    }

    private fun showEndGameScene() {
        Platform.runLater {
            val scene = GameEndScene(stage, gridComponent)
            scene.show()
        }
    }

    override fun title() = "Game #${PreferenceStore.getGameId()}"

}