package io.github.gravetii.validation;

public interface ValidationRule<T, S> {
  S validate(T obj);
}
