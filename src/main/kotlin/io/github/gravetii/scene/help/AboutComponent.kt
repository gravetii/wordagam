package io.github.gravetii.scene.help

import io.github.gravetii.controller.BaseController
import io.github.gravetii.scene.FxComponent
import javafx.scene.control.TextArea

class AboutComponent : FxComponent<BaseController, TextArea>("about.fxml") {

    override val controller: BaseController = BaseController()

    override val node: TextArea = loadNode()

}