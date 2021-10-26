package io.github.gravetii.controller

import io.github.gravetii.game.Game
import io.github.gravetii.game.UserResult
import io.github.gravetii.model.GameStats
import io.github.gravetii.model.GridPoint
import io.github.gravetii.model.WordResult
import io.github.gravetii.validation.ValidationResult
import javafx.fxml.FXML
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane

class GameGridController(private val game: Game) : FxController {

    private val imgViewMap: MutableMap<String, ImageView> = mutableMapOf()
    private val validator = GamePlayValidator(game.result)
    private val userResult = UserResult()

    private lateinit var styler: GamePlayStyler

    @FXML
    private lateinit var gamePane: GridPane

    @FXML
    private lateinit var imgView_0_0: ImageView

    @FXML
    private lateinit var imgView_0_1: ImageView

    @FXML
    private lateinit var imgView_0_2: ImageView

    @FXML
    private lateinit var imgView_0_3: ImageView

    @FXML
    private lateinit var imgView_1_0: ImageView

    @FXML
    private lateinit var imgView_1_1: ImageView

    @FXML
    private lateinit var imgView_1_2: ImageView

    @FXML
    private lateinit var imgView_1_3: ImageView

    @FXML
    private lateinit var imgView_2_0: ImageView

    @FXML
    private lateinit var imgView_2_1: ImageView

    @FXML
    private lateinit var imgView_2_2: ImageView

    @FXML
    private lateinit var imgView_2_3: ImageView

    @FXML
    private lateinit var imgView_3_0: ImageView

    @FXML
    private lateinit var imgView_3_1: ImageView

    @FXML
    private lateinit var imgView_3_2: ImageView

    @FXML
    private lateinit var imgView_3_3: ImageView

    @FXML
    fun initialize() {
        imgViewMap["imgView_0_0"] = imgView_0_0
        imgViewMap["imgView_0_1"] = imgView_0_1
        imgViewMap["imgView_0_2"] = imgView_0_2
        imgViewMap["imgView_0_3"] = imgView_0_3
        imgViewMap["imgView_1_0"] = imgView_1_0
        imgViewMap["imgView_1_1"] = imgView_1_1
        imgViewMap["imgView_1_2"] = imgView_1_2
        imgViewMap["imgView_1_3"] = imgView_1_3
        imgViewMap["imgView_2_0"] = imgView_2_0
        imgViewMap["imgView_2_1"] = imgView_2_1
        imgViewMap["imgView_2_2"] = imgView_2_2
        imgViewMap["imgView_2_3"] = imgView_2_3
        imgViewMap["imgView_3_0"] = imgView_3_0
        imgViewMap["imgView_3_1"] = imgView_3_1
        imgViewMap["imgView_3_2"] = imgView_3_2
        imgViewMap["imgView_3_3"] = imgView_3_3
        styler = GamePlayStyler(gamePane, imgViewMap.values)
    }

    private fun String.getImageViewLabel(): GridPoint {
        val tokens = split("_")
        return GridPoint(tokens[1].toInt(), tokens[2].toInt())
    }

    private fun applyStyleAfterValidation(imgView: ImageView, result: ValidationResult) {
        when (result) {
            ValidationResult.ALL_INVALID -> styler.invalidate()
            ValidationResult.LAST_INVALID -> styler.forLastInvalidClick(imgView)
            else -> styler.forValidClick(imgView)
        }
    }

    private fun revisit(result: WordResult) {
        val imgViews = result.seq.map {
            val imgView = "imgView_${it.x}_${it.y}"
            imgViewMap[imgView]!!
        }

        validator.reset()
        styler.revisit(imgViews)
    }

    @FXML
    fun onImgViewClick(event: MouseEvent) {
        val imgView = event.source as ImageView
        val point = imgView.id.getImageViewLabel()
        val unit = game.getGridUnit(point)
        applyStyleAfterValidation(imgView, validator.validate(unit))
    }

    fun rotate() = styler.rotate()

    fun validateWordOnBtnClick(): WordResult? {
        val word = validator.validate()
        val result = if (word == null) {
            styler.forIncorrectWord()
            null
        } else if (userResult.exists(word)) {
            styler.forRepeatedWord()
            null
        } else {
            val points = game.result.getPoints(word)
            val seq = validator.getSeq()
            val wordResult = WordResult(word, points, seq)
            userResult.add(word, wordResult)
            styler.forCorrectWord()
            wordResult
        }

        validator.reset()
        return result
    }

    fun revisitUserWord(word: String) = revisit(userResult.get(word))

    fun revisitGameWord(word: String) = revisit(game.result.get(word))

    fun getAllGameWords(): Map<String, WordResult> = game.result.all()

    fun getAllUserWords(): Map<String, WordResult> = userResult.all()

    fun endGame() {
        styler.applyEndTransition()
        imgViewMap.values.forEach { it.isDisable = true }
        validator.reset()
        styler.rotateOnEnd()
    }

    fun computeStats(): GameStats = GameStats(game.result, userResult)

}