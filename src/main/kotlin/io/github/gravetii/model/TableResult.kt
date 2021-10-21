package io.github.gravetii.model

data class TableResult(
    val id: Int,
    val word: String?,
    val score: Int,
    val byUser: Boolean
) {
    fun nonEmpty() = word != null
}
