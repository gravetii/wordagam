package io.github.gravetii.controller

import io.github.gravetii.model.TableResult
import javafx.animation.Interpolator
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.fxml.FXML
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.control.TableColumn
import javafx.scene.control.TableRow
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.util.Duration

class GameEndResultController(private val ref: GameGridController) : FxController {

    companion object {

        private val interpolator = Interpolator.SPLINE(0.4, 0.6, 0.6, 1.0)

        private class CustomTableRow : TableRow<TableResult>() {
            override fun updateItem(item: TableResult?, empty: Boolean) {
                styleClass.remove("user-table-row-cell")
                super.updateItem(item, empty)
                if (!empty && item?.byUser == true) styleClass.add("user-table-row-cell")
            }
        }

    }

    private lateinit var displayer: GameResultDisplayer

    @FXML
    private lateinit var resultBox: Label

    @FXML
    private lateinit var tblDisplay: TableView<TableResult>

    @FXML
    private lateinit var wordTblCol: TableColumn<TableResult, String>

    @FXML
    private lateinit var pointsTblCol: TableColumn<TableResult, Int>

    @FXML
    fun initialize() {
        wordTblCol.cellValueFactory = PropertyValueFactory("word")
        pointsTblCol.cellValueFactory = PropertyValueFactory("score")
        tblDisplay.setRowFactory {
            val row = CustomTableRow()
            row.setOnMouseClicked {
                if (!row.isEmpty) {
                    val result = row.item
                    if (result.nonEmpty()) {
                        result.word?.let {
                            if (result.byUser) ref.revisitUserWord(it)
                            else ref.revisitGameWord(it)
                        }
                    }
                }
            }

            row
        }

        wordTblCol.comparator = Comparator.comparingInt(String::length)
        displayer = GameResultDisplayer(tblDisplay)
        showAllWords()
    }

    private fun showAllWords() {
        with(displayer) {
            ref.getAllUserWords().values.forEach(::showUserWord)
            ref.getAllGameWords().values.forEach(::showGameWord)
        }
    }

    private fun applyAnimation(node: Node) {
        val timeline = Timeline(
            KeyFrame(
                Duration.millis(0.0),
                KeyValue(node.opacityProperty(), 0, interpolator),
                KeyValue(node.translateYProperty(), -3000, interpolator)
            ),
            KeyFrame(
                Duration.millis(600.0),
                KeyValue(node.opacityProperty(), 1, interpolator),
                KeyValue(node.translateYProperty(), 25, interpolator)
            ),
            KeyFrame(
                Duration.millis(750.0),
                KeyValue(node.translateYProperty(), -10, interpolator)
            ),
            KeyFrame(
                Duration.millis(900.0),
                KeyValue(node.translateYProperty(), 5, interpolator)
            ),
            KeyFrame(
                Duration.millis(1000.0),
                KeyValue(node.translateYProperty(), 0, interpolator)
            ),
        )

        timeline.play()
    }

    fun updateText(text: String) {
        applyAnimation(resultBox)
        resultBox.text = text
        resultBox.alignment = Pos.CENTER
    }

}