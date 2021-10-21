package io.github.gravetii.dictionary

import io.github.gravetii.util.Alphabet

class TrieNode {
    val children: MutableMap<Int, TrieNode> = mutableMapOf()
    var score: Int = 0
}

class Trie {

    private val root = TrieNode()

    fun insert(word: String) {
        var node = root
        var score = 0
        word.forEach {
            val idx = it - 'a'
            val alphabet = Alphabet.values()[idx]
            node = node.children.getOrPut(idx) { TrieNode() }
            score += alphabet.score
        }

        node.score = score
    }

    fun search(word: String): Int? {
        var node: TrieNode? = root
        word.forEach {
            val idx = it - 'a'
            node = node?.children?.get(idx)
            if (node == null) return null
        }

        return node?.score
    }

}