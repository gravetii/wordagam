package io.github.gravetii.model

import io.github.gravetii.game.GameResult
import io.github.gravetii.game.UserResult

data class GameStats(val gameResult: GameResult, val userResult: UserResult) {

    override fun toString(): String {
        val result = "Words - %d out of %d\n" +
                "Words percentage - %.2f\n\n" +
                "Score - %d out of %d\n" +
                "Score percentage - %.2f"

        val userWordsCount = userResult.getWordCount()
        val totalWordsCount = gameResult.all().size
        val userScore = userResult.totalScore
        val totalScore = gameResult.totalScore
        val wordsPercent = (userWordsCount * 100.0) / totalWordsCount
        val scorePercent = (userScore * 100.0) / totalScore
        return String.format(
            result,
            userResult.getWordCount(),
            gameResult.all().size,
            wordsPercent,
            userScore,
            totalScore,
            scorePercent
        )
    }
}