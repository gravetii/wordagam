package io.github.gravetii.game

import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

object GameFactory {

    private val logger = Logger.getLogger(javaClass.canonicalName)

    private const val MAX_GAMES_IN_QUEUE = 5

    private val queue = LinkedBlockingDeque<Game>()
    private val executor = Executors.newFixedThreadPool(2)

    private fun create(): Game {
        var game = Game()
        while (game.getQuality() == GameQuality.LOW) game = Game()
        return game
    }

    private fun backFill(n: Int) {
        (1..n).forEach { _ ->
            executor.submit {
                val game = create()
                val q = game.getQuality()
                if (q == GameQuality.HIGH) queue.offerFirst(game)
                else if (q == GameQuality.MEDIUM) queue.offerLast(game)
            }
        }
    }

    fun fetch(): Game {
        val game = queue.poll() ?: create()
        backFill(MAX_GAMES_IN_QUEUE - queue.size)
        return game
    }

    fun bootstrap() = backFill(MAX_GAMES_IN_QUEUE)

    fun close() {
        try {
            executor.shutdown()
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) executor.shutdownNow()
            queue.clear()
        } catch (e: Exception) {
            logger.severe("Error while closing game factory - ${e.message}")
        }
    }

}