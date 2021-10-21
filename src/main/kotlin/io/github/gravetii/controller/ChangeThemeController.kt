package io.github.gravetii.controller

import io.github.gravetii.theme.ThemeFactory
import io.github.gravetii.theme.ThemeType
import io.github.gravetii.util.Utils
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.stage.Stage

class ChangeThemeController(private val stage: Stage) : FxController {

    private val imgViewMap: MutableMap<Int, ImageView> = mutableMapOf()
    private val styler = ChangeThemeStyler()

    @FXML
    private lateinit var footerLabel: Label

    @FXML
    private lateinit var imgView_0: ImageView

    @FXML
    private lateinit var imgView_1: ImageView

    @FXML
    private lateinit var imgView_2: ImageView

    @FXML
    private lateinit var imgView_3: ImageView

    @FXML
    private lateinit var imgView_4: ImageView

    @FXML
    private lateinit var imgView_5: ImageView

    @FXML
    private lateinit var imgView_6: ImageView

    @FXML
    private lateinit var imgView_7: ImageView

    @FXML
    private lateinit var imgView_8: ImageView

    @FXML
    private lateinit var imgView_9: ImageView

    @FXML
    private lateinit var imgView_10: ImageView

    @FXML
    private lateinit var imgView_11: ImageView

    @FXML
    private lateinit var imgView_12: ImageView

    @FXML
    private lateinit var imgView_13: ImageView

    @FXML
    private lateinit var imgView_14: ImageView

    @FXML
    fun initialize() {
        imgViewMap[0] = imgView_0
        imgViewMap[1] = imgView_1
        imgViewMap[2] = imgView_2
        imgViewMap[3] = imgView_3
        imgViewMap[4] = imgView_4
        imgViewMap[5] = imgView_5
        imgViewMap[6] = imgView_6
        imgViewMap[7] = imgView_7
        imgViewMap[8] = imgView_8
        imgViewMap[9] = imgView_9
        imgViewMap[10] = imgView_10
        imgViewMap[11] = imgView_11
        imgViewMap[12] = imgView_12
        imgViewMap[13] = imgView_13
        imgViewMap[14] = imgView_14
        initStyle()
        footerLabel.text = "Your current theme is set to ${ThemeFactory.current.getDisplayName()}"
    }

    @FXML
    fun onImgViewClick(event: MouseEvent) {
        if (event.button.equals(MouseButton.PRIMARY)) {
            val imgView = event.source as ImageView
            val idx = Utils.getImageViewIndexFromLabel(imgView.id)
            if (idx < ThemeFactory.getAll().size) {
                val type = ThemeFactory.getAll()[idx]
                styler.applySelectStyle(imgView)
                if (ThemeFactory.changeTheme(type)) {
                    ThemeFactory.dispatch(stage.owner)
                    updateThemeChange(type)
                }
            }

            if (event.clickCount == 2) stage.close()
        }
    }

    private fun initStyle() {
        val idx = ThemeFactory.getAll().indexOf(ThemeFactory.current)
        val imgView = imgViewMap[idx]
        styler.applySelectStyle(imgView!!)
    }

    private fun updateThemeChange(type: ThemeType) {
        val text = if (ThemeType.RANDOM == type) {
            "You have changed the theme to be random, you will see a new theme each time"
        } else {
            "Theme changed to ${type.getDisplayName()}"
        }

        footerLabel.text = text
    }

}