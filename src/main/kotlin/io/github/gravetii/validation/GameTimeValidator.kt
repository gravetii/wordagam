package io.github.gravetii.validation

import io.github.gravetii.model.GameTime
import java.lang.NumberFormatException

object GameTimeValidator {

    private fun nonEmpty(obj: Pair<String, String>?): Pair<String, String>? {
        return if (obj == null) null
        else Pair(obj.first.ifEmpty { "0" }, obj.second.ifEmpty { "0" })
    }

    private fun isNumber(obj: Pair<String, String>?): Pair<String, String>? {
        return if (obj == null) null
        else {
            val r = Regex("[0-9]+")
            if (obj.first.matches(r) && obj.second.matches(r)) obj else null
        }
    }

    private fun integerParser(obj: Pair<String, String>?): Pair<Int, Int>? {
        if (obj == null) return null
        return try {
            Pair(obj.first.toInt(), obj.second.toInt())
        } catch (e: NumberFormatException) {
            null
        }
    }

    private fun gameTimeParser(obj: Pair<Int, Int>?): GameTime? {
        if (obj == null) return null
        if (obj.first == 0 && obj.second == 0) return null
        if (obj.second < 0 || obj.second > 60) return null
        return GameTime(obj.first, obj.second)
    }

    fun validate(obj: Pair<String, String>?): GameTime? {
        return gameTimeParser(integerParser(isNumber(nonEmpty(obj))))
    }

}