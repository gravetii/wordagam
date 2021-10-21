package io.github.gravetii.controller

import io.github.gravetii.game.Game
import javafx.concurrent.Task
import javafx.scene.layout.BorderPane
import java.util.logging.Logger

class GameTimerTask(private val root: BorderPane, private val time: Double) : Task<Unit>() {

    private val logger = Logger.getLogger(javaClass.canonicalName)

    override fun call() {
        var c: Double = time
        try {
            while (c > 0) {
                Thread.sleep(50)
                updateProgress(c, time)
                c -= 0.05
            }

            updateProgress(Double.MIN_VALUE, time)
            root.fireEvent(Game.Companion.EndEvent())
        } catch (e: InterruptedException) {
            logger.fine("Game stopped in between")
        }
    }


}