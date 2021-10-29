package io.github.gravetii

import io.github.gravetii.db.PreferenceStore
import io.github.gravetii.dictionary.Dictionary
import io.github.gravetii.game.GameFactory
import io.github.gravetii.scene.start.StartScene
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.Stage

class App : Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = launch(App::class.java)

        private fun exitCheck(stage: Stage?): Boolean {
            val alert = Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.NO, ButtonType.YES)
            alert.headerText = ""
            alert.title = "Really Exit?"
            alert.initOwner(stage!!)
            return alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES
        }

        fun close(stage: Stage?): Boolean {
            return if (exitCheck(stage)) {
                Platform.exit()
                true
            } else false
        }
    }

    override fun start(stage: Stage?) {
        stage?.setOnCloseRequest { e -> if (!close(stage)) e.consume() }
        val scene = StartScene(stage!!)
        scene.show()
    }

    override fun init() {
        Dictionary.loadWords()
        GameFactory.bootstrap()
    }

    override fun stop() {
        PreferenceStore.close()
        GameFactory.close()
    }

}