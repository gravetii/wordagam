package io.github.gravetii.dictionary

import java.io.BufferedReader
import java.io.InputStreamReader

object Dictionary {

    private const val WORDS_FILE = "words.txt"

    private val trie = Trie()

    fun loadWords() {
        var reader: BufferedReader? = null
        try {
            val stream = ClassLoader.getSystemResourceAsStream(WORDS_FILE)
            reader = BufferedReader(InputStreamReader(stream ?: throw Exception("Error while loading words file")))
            while (true) reader.readLine()?.also { trie.insert(it) } ?: break
        } finally {
            reader?.close()
        }
    }

    fun search(word: String): Int = trie.search(word) ?: -1

}