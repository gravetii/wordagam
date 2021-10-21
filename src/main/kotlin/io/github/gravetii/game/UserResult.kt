package io.github.gravetii.game

import io.github.gravetii.model.WordResult

class UserResult {

    private var wordToResultMap: MutableMap<String, WordResult> = mutableMapOf()
    var totalScore: Int = 0

    fun add(word: String, result: WordResult) {
        wordToResultMap[word] = result
        totalScore += result.score
    }

    fun get(word: String): WordResult = wordToResultMap[word]!!

    fun all(): Map<String, WordResult> = wordToResultMap

    fun exists(word: String) = wordToResultMap.containsKey(word)

    fun getWordCount(): Int = wordToResultMap.size

}