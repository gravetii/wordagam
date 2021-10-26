package io.github.gravetii.dictionary

import io.github.gravetii.model.Alphabet

class TrieNode {
    val children: MutableMap<Int, TrieNode> = mutableMapOf()
    var score: Int = 0
}

class Trie {

    private val root = TrieNode()

    fun insert(word: String) {
        fun insertInternal(itr: CharIterator, root: TrieNode, score: Int) {
            val idx = itr.nextChar() - 'a'
            val alphabet = Alphabet.values()[idx]
            val node = root.children.getOrPut(idx) { TrieNode() }
            if (itr.hasNext()) insertInternal(itr, node, score + alphabet.score)
            else node.score = score + alphabet.score
        }

        insertInternal(word.iterator(), root, 0)
    }

    fun search(word: String): Int? {
        fun searchInternal(itr: CharIterator, root: TrieNode): Int? {
            val idx = itr.nextChar() - 'a'
            val node = root.children[idx]
            return if (itr.hasNext()) node?.let { searchInternal(itr, it) }
            else node?.score
        }

        return searchInternal(word.iterator(), root)
    }

}