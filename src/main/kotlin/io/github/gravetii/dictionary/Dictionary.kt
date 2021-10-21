package io.github.gravetii.dictionary

import java.io.BufferedReader
import java.io.InputStreamReader

object Dictionary {

    private const val WORDS_FILE = "words.txt"

    private val trie = Trie()

    fun loadWords() {
        val inputStream = ClassLoader.getSystemResourceAsStream(WORDS_FILE) ?: throw Exception()
        val reader = BufferedReader(InputStreamReader(inputStream))
        while (true) {
            val word = reader.readLine() ?: break
            trie.insert(word.trim().lowercase())
        }

        reader.close()
    }

    fun search(word: String): Int = trie.search(word) ?: -1

}