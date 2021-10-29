package io.github.gravetii.controller

import io.github.gravetii.model.TableResult
import io.github.gravetii.model.WordResult
import javafx.scene.control.TableView

class GameResultDisplayer(private val table: TableView<TableResult>) {

    private val displayedWords: MutableSet<String> = hashSetOf()
    private var counter = 0

    private fun WordResult.show(byUser: Boolean) {
        if (!displayedWords.contains(word)) {
            val result = TableResult(++counter, word, score, byUser)
            table.items.add(result)
            displayedWords.add(word)
        }
    }

    fun showGameWord(result: WordResult) = result.show(false)

    fun showUserWord(result: WordResult) = result.show(true)

    fun setMarker() = table.scrollTo(counter)

}