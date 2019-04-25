package io.github.gravetii.util;

import java.util.ArrayList;
import java.util.List;

public enum Alphabet {
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

  private static List<Alphabet> weighted = new ArrayList<>();

  static {
    for (Alphabet alphabet : values()) {
      for (int c = 0; c < alphabet.weight; ++c) {
        weighted.add(alphabet);
      }
    }
  }

  private int weight;
  private int score;

  Alphabet(int weight, int score) {
    this.weight = weight;
    this.score = score;
  }

  public static List<Alphabet> weightedList() {
    return weighted;
  }

  public String get() {
    return this.toString().toLowerCase();
  }

  public int getWeight() {
    return weight;
  }

  public int getScore() {
    return score;
  }
}
