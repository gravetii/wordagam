package io.github.gravetii.model

data class GameTime(val minutes: Int, val seconds: Int) {

    companion object {
        fun from(value: String): GameTime {
            val tokens = value.split("_")
            val minutes = tokens[0].toInt()
            val seconds = tokens[1].toInt()
            return GameTime(minutes, seconds)
        }
    }

    fun getValueInSeconds(): Int = minutes * 60 + seconds

    override fun toString(): String = "${minutes}_${seconds}"

}