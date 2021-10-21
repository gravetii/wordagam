package io.github.gravetii.util

import io.github.gravetii.model.GridPoint

object Utils {

    fun getGridPointFromImageViewLabel(label: String): GridPoint {
        val tokens = label.split("_")
        return GridPoint(tokens[1].toInt(), tokens[2].toInt())
    }

    fun getImgViewLabelFromGridPoint(point: GridPoint): String = "imgView_" + point.x + "_" + point.y

    fun getImageViewIndexFromLabel(label: String): Int {
        val tokens = label.split("_")
        return tokens[1].toInt()
    }

}