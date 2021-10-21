package io.github.gravetii.controller

import io.github.gravetii.db.PreferenceStore
import javafx.fxml.FXML
import javafx.scene.control.ProgressBar
import javafx.scene.layout.BorderPane
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ThreadFactory

class ProgressBarController(root: BorderPane) : FxController {

    private val task: GameTimerTask

    @FXML private lateinit var bar: ProgressBar

    companion object {

        private class CustomThreadFactory : ThreadFactory {
            override fun newThread(r: Runnable): Thread {
                val thread = Thread(r)
                thread.isDaemon = true
                return thread
            }
        }

        private val executor = Executors.newSingleThreadExecutor(CustomThreadFactory())

        private var currentTask: Future<*>? = null

    }

    init {
        val time = PreferenceStore.getGameTime().getValueInSeconds()
        this.task = GameTimerTask(root, time.toDouble())
        submit()
    }

    @FXML private fun initialize() {
        bar.progressProperty().bind(task.progressProperty())
    }

    private fun submit() {
        currentTask?.cancel(true)
        currentTask = executor.submit(task)
    }

}