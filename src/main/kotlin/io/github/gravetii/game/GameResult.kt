package io.github.gravetii.game

import io.github.gravetii.model.GridPoint
import io.github.gravetii.model.WordResult

class GameResult {

    private var wordToResultMap: MutableMap<String, WordResult> = mutableMapOf()
    var totalScore: Int = 0
    var quality: GameQuality = GameQuality.LOW

    fun put(word: String, score: Int, seq: List<GridPoint>) {
        if (!wordToResultMap.containsKey(word)) {
            wordToResultMap[word] = WordResult(word, score, seq)
            totalScore += score
        }
    }

    fun exists(word: String): Boolean = wordToResultMap.containsKey(word)

    fun getPoints(word: String): Int = wordToResultMap[word]!!.score

    fun all(): Map<String, WordResult> = wordToResultMap

    fun get(word: String): WordResult = wordToResultMap[word]!!

    fun defineQuality(): GameResult {
        quality = GameQuality.values()
            .dropWhile { wordToResultMap.size < it.minCount }
            .firstOrNull() ?: GameQuality.LOW
        return this
    }

}