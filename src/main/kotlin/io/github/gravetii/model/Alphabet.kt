package io.github.gravetii.model

import java.util.concurrent.ThreadLocalRandom

enum class Alphabet(val weight: Int, val score: Int) {
    A(20, 1),
    B(10, 3),
    C(12, 3),
    D(12, 2),
    E(10, 1),
    F(10, 4),
    G(10, 2),
    H(15, 4),
    I(20, 1),
    J(5, 8),
    K(5, 5),
    L(12, 1),
    M(12, 3),
    N(15, 1),
    O(20, 1),
    P(10, 3),
    Q(2, 10),
    R(15, 1),
    S(15, 1),
    T(20, 1),
    U(12, 1),
    V(5, 4),
    W(10, 4),
    X(2, 8),
    Y(10, 4),
    Z(2, 10);

    companion object {

        private val totalWeight = values().sumOf { it.weight }

        fun newRandom(): Alphabet {
            val value = 1 + ThreadLocalRandom.current().nextInt(totalWeight)
            var score = 0
            return values().first {
                score += it.weight
                score >= value
            }
        }

    }

    fun get(): String = toString().lowercase()

}