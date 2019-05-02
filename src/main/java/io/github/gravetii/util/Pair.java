package io.github.gravetii.util;

import java.util.Objects;

public class Pair<F, S> {
  private F first;
  private S second;

  public Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  public F getFirst() {
    return first;
  }

  public S getSecond() {
    return second;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return first.equals(pair.first) &&
            second.equals(pair.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public String toString() {
    return "Pair{" +
            "first=" + first +
            ", second=" + second +
            '}';
  }
}
