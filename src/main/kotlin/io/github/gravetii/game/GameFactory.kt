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
        return generateSequence(Game()) {
            if (it.getQuality() != GameQuality.LOW) null else Game()
        }.last()
    }

    private fun backFill(n: Int) {
        (1..n).forEach { _ ->
            executor.submit {
                val game = create()
                if (game.getQuality() == GameQuality.HIGH) queue.offerFirst(game)
                else if (game.getQuality() == GameQuality.MEDIUM) queue.offerLast(game)
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