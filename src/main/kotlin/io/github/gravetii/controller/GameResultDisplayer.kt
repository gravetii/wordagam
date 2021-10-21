package io.github.gravetii.controller

import io.github.gravetii.model.TableResult
import io.github.gravetii.model.WordResult
import javafx.scene.control.TableView

class GameResultDisplayer(private val table: TableView<TableResult>) {

    private val displayedWords: MutableSet<String> = hashSetOf()
    private var counter = 0

    private fun show(result: WordResult, byUser: Boolean) {
        val word = result.word
        if (!displayedWords.contains(word)) {
            val tableResult = TableResult(++counter, word, result.score, byUser)
            table.items.add(tableResult)
            displayedWords.add(word)
        }
    }

    fun showGameWord(result: WordResult) = show(result, false)

    fun showUserWord(result: WordResult) = show(result, true)

    fun setMarker() = table.scrollTo(counter)

}