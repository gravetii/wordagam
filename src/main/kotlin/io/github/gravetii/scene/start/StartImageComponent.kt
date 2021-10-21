package io.github.gravetii.scene.start

import io.github.gravetii.controller.BaseController
import io.github.gravetii.scene.FxComponent
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane

class StartImageComponent(private val root: BorderPane) : FxComponent<BaseController, Pane>("startImage.fxml") {

    companion object {
        private const val SKIN_PATH = "/images/1.gif"
    }

    override val controller: BaseController = BaseController()

    override val node: Pane = createNode()

    private fun createNode(): Pane {
        val pane = loadNode()
        pane.prefHeightProperty().bind(root.heightProperty())
        pane.prefWidthProperty().bind(root.widthProperty())
        val imgView: ImageView = pane.children[0] as ImageView
        val img = Image(javaClass.getResource(SKIN_PATH)!!.toExternalForm())
        imgView.image = img
        imgView.fitHeightProperty().bind(pane.heightProperty())
        imgView.fitWidthProperty().bind(pane.widthProperty())
        return pane
    }

}