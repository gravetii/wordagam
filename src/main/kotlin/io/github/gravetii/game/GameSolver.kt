package io.github.gravetii.game

import io.github.gravetii.dictionary.Dictionary
import io.github.gravetii.model.GridPoint
import io.github.gravetii.model.GridUnit

class GameSolver(private val grid: List<List<GridUnit>>) {

    companion object {
        private const val MIN_WORD_LENGTH = 3
    }

    private val result = GameResult()

    private fun crawl(point: GridPoint, prefix: String, seq: ArrayList<GridPoint>, visited: Array<BooleanArray>) {
        val unit = grid[point.x][point.y]
        visited[point.x][point.y] = true
        val word = prefix + unit.letter
        val score = Dictionary.search(word)
        if (score >= 0) {
            seq.add(point)
            if (score > 0 && word.length >= MIN_WORD_LENGTH) result.put(word, score, seq)
            point.neighbors.forEach {
                if (!visited[it.x][it.y]) {
                    crawl(it, word, ArrayList(seq), visited)
                    visited[it.x][it.y] = false
                }
            }
        }
    }

    fun solve(): GameResult {
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val visited = Array(4) { BooleanArray(4) { false } }
                crawl(grid[i][j].point, "", arrayListOf(), visited)
            }
        }

        return result.defineQuality()
    }

}