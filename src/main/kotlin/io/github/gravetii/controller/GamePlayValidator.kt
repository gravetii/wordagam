package io.github.gravetii.controller

import io.github.gravetii.game.GameResult
import io.github.gravetii.model.GridPoint
import io.github.gravetii.model.GridUnit
import io.github.gravetii.validation.ValidationResult

class GamePlayValidator(private val result: GameResult) {

    private val seq: ArrayList<GridPoint> = arrayListOf()
    private var builder = StringBuilder()

    fun reset() {
        builder = StringBuilder()
        seq.clear()
    }

    private fun append(unit: GridUnit) {
        builder.append(unit.letter)
        seq.add(unit.point)
    }

    private fun truncate() {
        builder.deleteAt(builder.length - 1)
        seq.removeLast()
    }

    private fun validateFirstClick(unit: GridUnit): ValidationResult {
        append(unit)
        return ValidationResult.ALL_VALID
    }

    private fun validateSubsequentClick(unit: GridUnit): ValidationResult {
        return if (unit.point == seq.last()) {
            truncate()
            ValidationResult.LAST_INVALID
        } else if (seq.contains(unit.point) || !unit.point.isNeighbor(seq.last())) {
            reset()
            ValidationResult.ALL_INVALID
        } else {
            append(unit)
            ValidationResult.ALL_VALID
        }
    }

    fun validate(unit: GridUnit): ValidationResult {
        return if (seq.isEmpty()) validateFirstClick(unit)
        else validateSubsequentClick(unit)
    }

    fun validate(): String? {
        val word = builder.toString()
        return if (word.isEmpty() || !result.exists(word)) null
        else word
    }

    fun getSeq(): List<GridPoint> {
        return ArrayList(seq)
    }

}