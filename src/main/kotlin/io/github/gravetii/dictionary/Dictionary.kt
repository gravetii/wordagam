package io.github.gravetii.dictionary

import java.io.BufferedReader
import java.io.InputStreamReader

object Dictionary {

    private const val WORDS_FILE = "words.txt"

    private val trie = Trie()

    fun loadWords() {
        var reader: BufferedReader? = null
        try {
            val inputStream = ClassLoader.getSystemResourceAsStream(WORDS_FILE) ?: throw Exception()
            reader = BufferedReader(InputStreamReader(inputStream))
            while (true) {
                val word = reader.readLine() ?: break
                trie.insert(word.trim().lowercase())
            }
        } finally {
            reader?.close()
        }
    }

    fun search(word: String): Int = trie.search(word) ?: -1

}