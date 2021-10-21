package io.github.gravetii.model

data class GridPoint(val x: Int, val y: Int) {

    companion object {

        private val points = arrayOf(
            Pair(0, -1),
            Pair(-1, 0),
            Pair(0, 1),
            Pair(1, 0),
            Pair(-1, -1),
            Pair(1, 1),
            Pair(1, -1),
            Pair(-1, 1)
        )

    }

    private var _neighbors: Set<GridPoint>? = null

    fun getNeighbors(): Set<GridPoint> {
        if (_neighbors == null) {
            _neighbors = points.mapNotNull {
                val point = Pair(x + it.first, y + it.second)
                if (point.first in 0..3 && point.second in 0..3) {
                    GridPoint(point.first, point.second)
                } else null
            }.toHashSet()
        }

        return _neighbors!!
    }

    fun isNeighbor(point: GridPoint) = getNeighbors().contains(point)

}

