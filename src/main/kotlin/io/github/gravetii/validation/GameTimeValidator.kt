package io.github.gravetii.validation

import io.github.gravetii.model.GameTime
import kotlin.NumberFormatException

object GameTimeValidator {

    private fun nonEmpty(obj: Pair<String, String>?): Pair<String, String>? {
        return obj?.let {
            Pair(it.first.ifEmpty { "0" }, it.second.ifEmpty { "0" })
        }
    }

    private fun isNumber(obj: Pair<String, String>?): Pair<Int, Int>? {
        return obj?.let {
            val r = Regex("[0-9]+")
            return if (it.first.matches(r) && it.second.matches(r)) {
                try {
                    Pair(it.first.toInt(), it.second.toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            } else null
        }
    }

    private fun gameTimeParser(obj: Pair<Int, Int>?): GameTime? {
        return obj?.let {
            return if (it.first == 0 && it.second == 0) null
            else if (it.first < 0 || it.second > 60) null
            else GameTime(it.first, it.second)
        }
    }

    fun validate(obj: Pair<String, String>?): GameTime? {
        return gameTimeParser(isNumber(nonEmpty(obj)))
    }

}