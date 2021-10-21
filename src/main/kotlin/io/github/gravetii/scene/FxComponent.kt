package io.github.gravetii.scene

import io.github.gravetii.controller.FxController
import javafx.fxml.FXMLLoader
import javafx.scene.Node

abstract class FxComponent<C : FxController, N : Node>(val fxml: String) {

    abstract val controller: C
    abstract val node: N

    protected fun loadNode(): N {
        val loader = FXMLLoader(javaClass.getResource("/fxml/$fxml"))
        loader.setController(controller)
        return loader.load()
    }

}