package io.github.gravetii.controller

import io.github.gravetii.model.TableResult
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableRow
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.input.MouseEvent

class GameResultController(private val ref: GameGridController) : FxController {

    private lateinit var displayer: GameResultDisplayer

    @FXML
    private lateinit var tblDisplay: TableView<TableResult>

    @FXML
    private lateinit var idTblCol: TableColumn<TableResult, Int>

    @FXML
    private lateinit var wordTblCol: TableColumn<TableResult, String>

    @FXML
    private lateinit var pointsTblCol: TableColumn<TableResult, Int>

    @FXML
    fun initialize() {
        idTblCol.cellValueFactory = PropertyValueFactory("id")
        wordTblCol.cellValueFactory = PropertyValueFactory("word")
        pointsTblCol.cellValueFactory = PropertyValueFactory("score")
        tblDisplay.setRowFactory {
            val row = TableRow<TableResult>()
            row.setOnMouseClicked {
                if (!row.isEmpty) {
                    val result = row.item
                    val word = result.word
                    if (result.byUser) ref.revisitUserWord(word!!)
                    else ref.revisitGameWord(word!!)
                }
            }
            row
        }

        displayer = GameResultDisplayer(tblDisplay)
    }

    @FXML
    fun rotate(event: MouseEvent) = ref.rotate()

    @FXML
    fun onGoBtnClick(event: ActionEvent) {
        val word = ref.validateWordOnBtnClick()
        if (word != null) {
            displayer.showUserWord(word)
            displayer.setMarker()
        }
    }

}