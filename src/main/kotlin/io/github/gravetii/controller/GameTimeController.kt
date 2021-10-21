package io.github.gravetii.controller

import io.github.gravetii.db.PreferenceStore
import io.github.gravetii.validation.GameTimeValidator
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.stage.Stage

class GameTimeController(private val stage: Stage) : FxController {

    @FXML
    private lateinit var minutesTxtField: TextField

    @FXML
    private lateinit var secondsTxtField: TextField

    @FXML
    lateinit var validationLbl: Label

    @FXML
    fun initialize() {
        val gameTime = PreferenceStore.getGameTime()
        minutesTxtField.text = gameTime.minutes.toString()
        secondsTxtField.text = gameTime.seconds.toString()
    }

    @FXML
    fun ok() {
        val pair = Pair(minutesTxtField.text, secondsTxtField.text)
        val gameTime = GameTimeValidator.validate(pair)
        if (gameTime != null) {
            PreferenceStore.setGameTime(gameTime)
            stage.close()
        } else {
            initialize()
            validationLbl.text = "Invalid value!"
        }
    }

    @FXML
    fun cancel() = stage.close()

}