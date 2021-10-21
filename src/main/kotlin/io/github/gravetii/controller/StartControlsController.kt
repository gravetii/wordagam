package io.github.gravetii.controller

import javafx.fxml.FXML

class StartControlsController(private val ref: MenuBarController) : FxController {

    @FXML
    fun onPlayBtnClick() = ref.newGame()

    @FXML
    fun onChangeThemeBtnClick() = ref.editTheme()

    @FXML
    fun onWhatIsItBtnClick() = ref.whatIsIt()

}