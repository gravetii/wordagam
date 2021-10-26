package io.github.gravetii.game

import io.github.gravetii.model.GridPoint
import io.github.gravetii.model.GridUnit
import io.github.gravetii.model.Alphabet
import javafx.event.Event
import javafx.event.EventType

class Game {

    companion object {
        var isInstanceRunning: Boolean = false

        val GAME_END_EVENT_EVENT_TYPE: EventType<EndEvent> = EventType(Event.ANY, "game-end")

        class EndEvent : Event(GAME_END_EVENT_EVENT_TYPE)

        fun isRunning(): Boolean = isInstanceRunning
    }

    val grid = (0..3).map { x -> (0..3).map { y -> GridUnit(Alphabet.newRandom(), GridPoint(x, y)) } }

    val result: GameResult = GameSolver(grid).solve()

    fun getGridUnit(point: GridPoint): GridUnit = grid[point.x][point.y]

    fun getQuality(): GameQuality = result.quality

}