package io.github.gravetii.controller

import javafx.animation.*
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.util.Duration

class GamePlayStyler(private val gamePane: GridPane, private val imgViews: MutableCollection<ImageView>) {

    private val seq: ArrayList<ImageView> = arrayListOf()
    private var timeline = Timeline()
    private var gridRotationCount = 0
    private val pauser = PauseTransition(Duration.millis(200.0))
    private val panes = fetchPanes()
    private var sequencer = SequentialTransition()

    init {
        pauser.setOnFinished { invalidate() }
    }

    private fun ImageView.revert() = styleClass.remove("custom-img-view-click")

    private fun revert() = seq.forEach { it.revert() }

    private fun reset() = seq.clear()

    private fun truncate() = seq.removeLast()

    private fun applyRotateTransition() {
        val transition = ParallelTransition()
        seq.forEach {
            val rotate = RotateTransition(Duration.millis(65.0), it)
            rotate.byAngle = 360.0
            rotate.cycleCount = 4
            transition.children.add(rotate)
        }

        transition.play()
    }

    private fun applyFadeTransition() {
        val transition = ParallelTransition()
        seq.forEach {
            val fade = FadeTransition(Duration.millis(50.0), it)
            fade.fromValue = 1.0
            fade.toValue = 0.3
            fade.cycleCount = 6
            fade.isAutoReverse = true
            transition.children.add(fade)
        }

        transition.play()
    }

    private fun applyScaleTransition() {
        val transition = ParallelTransition()
        seq.forEach {
            val scale = ScaleTransition(Duration.millis(50.0), it)
            scale.byX = 0.2
            scale.byY = 0.2
            scale.cycleCount = 6
            scale.isAutoReverse = true
            transition.children.add(scale)
        }

        transition.play()
    }

    fun applyEndTransition() {
        val transition = ParallelTransition()
        imgViews.map {
            val scale = ScaleTransition(Duration.millis(100.0))
            scale.byX = 0.5
            scale.byY = 0.5
            scale.cycleCount = 4
            scale.isAutoReverse = true
            val translate = TranslateTransition(Duration.millis(100.0))
            translate.byX = 0.5
            translate.byY = 0.5
            translate.cycleCount = 4
            translate.isAutoReverse = true
            transition.children.add(ParallelTransition(it, scale, translate))
        }

        transition.play()
    }

    private fun fetchPanes(): Array<Array<Pane>> {
        val itr = gamePane.children.iterator()
        return (0 until 4).map { (0 until 4).map { itr.next() as Pane }.toTypedArray() }
            .toTypedArray()
    }

    private fun rearrange() {
        for (i in 0 until 2) {
            for (j in i until 3 - i) {
                val pane = panes[i][j]
                panes[i][j] = panes[3 - j][i]
                panes[3 - j][i] = panes[3 - i][3 - j]
                panes[3 - i][3 - j] = panes[j][3 - i]
                panes[j][3 - i] = pane
            }
        }

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val pane = panes[i][j]
                GridPane.setRowIndex(pane, i)
                GridPane.setColumnIndex(pane, j)
            }
        }
    }

    private fun rotateGrid() {
        rearrange()
        if (++gridRotationCount % 4 == 0) gridRotationCount = 0
    }

    private fun rotateGamePane() {
        val gridTransition = RotateTransition(Duration.millis(200.0), gamePane)
        gridTransition.byAngle = 360.0
        gridTransition.cycleCount = 1
        sequencer.children.add(gridTransition)
        imgViews.shuffled().forEach {
            val imgViewTransition = RotateTransition(Duration.millis(20.0), it)
            imgViewTransition.byAngle = 90.0
            imgViewTransition.cycleCount = 2
            imgViewTransition.isAutoReverse = true
            sequencer.children.add(imgViewTransition)
        }

        sequencer.setOnFinished { sequencer.children.clear() }
        sequencer.play()
    }

    fun revisit(imgViews: List<ImageView>) {
        timeline.stop()
        invalidate()
        val itr = imgViews.iterator()
        val keyframe = KeyFrame(Duration.millis(300.0), { forValidClick(itr.next()) })
        timeline = Timeline(keyframe)
        timeline.cycleCount = imgViews.size
        timeline.setOnFinished { pauser.play() }
        timeline.play()
    }

    fun invalidate() {
        revert()
        reset()
    }

    fun forValidClick(imgView: ImageView) {
        imgView.styleClass.add("custom-img-view-click")
        seq.add(imgView)
    }

    fun forLastInvalidClick(imgView: ImageView) {
        imgView.revert()
        truncate()
    }

    fun forRepeatedWord() {
        revert()
        applyScaleTransition()
        reset()
    }

    fun forIncorrectWord() {
        revert()
        applyFadeTransition()
        reset()
    }

    fun forCorrectWord() {
        revert()
        applyRotateTransition()
        reset()
    }

    fun rotate() {
        rotateGamePane()
        rotateGrid()
    }

    fun rotateOnEnd() {
        invalidate()
        while (gridRotationCount != 0) rotateGrid()
    }

}