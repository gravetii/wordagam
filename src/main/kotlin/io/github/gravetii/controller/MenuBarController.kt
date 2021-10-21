package io.github.gravetii.controller

import io.github.gravetii.App
import io.github.gravetii.db.PreferenceStore
import io.github.gravetii.game.Game
import io.github.gravetii.scene.game.GameScene
import io.github.gravetii.scene.help.AboutScene
import io.github.gravetii.scene.help.WhatIsItScene
import io.github.gravetii.scene.settings.GameTimeScene
import io.github.gravetii.scene.theme.ChangeThemeScene
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.Modality
import javafx.stage.Stage

class MenuBarController(private val stage: Stage) : FxController {

    private fun newModalWindow(): Stage {
        val window = Stage()
        window.isResizable = false
        window.initOwner(stage)
        window.initModality(Modality.APPLICATION_MODAL)
        return window
    }

    private fun showQuitGameAlert(): Boolean {
        val alert = Alert(Alert.AlertType.CONFIRMATION, "Quit current game?", ButtonType.NO, ButtonType.YES)
        alert.headerText = ""
        alert.title = "Quit?"
        alert.initOwner(stage)
        return alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES
    }

    @FXML
    fun newGame() {
        if (!Game.isRunning() || showQuitGameAlert()) {
            val scene = GameScene(stage)
            scene.show()
            Game.isInstanceRunning = true
        }
    }

    @FXML
    fun setGameTime() {
        val stage = newModalWindow()
        val scene = GameTimeScene(stage)
        scene.show()
    }

    @FXML
    fun resetGameCounter() = PreferenceStore.resetGameId()

    @FXML
    fun exit() = App.close(stage)

    fun editTheme() {
        val stage = newModalWindow()
        val scene = ChangeThemeScene(stage)
        scene.show()
    }

    fun showAbout() {
        val stage = newModalWindow()
        val scene = AboutScene(stage)
        scene.show()
    }

    fun whatIsIt() {
        val stage = newModalWindow()
        val scene = WhatIsItScene(stage)
        scene.show()
    }

}