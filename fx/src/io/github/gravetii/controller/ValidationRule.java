package io.github.gravetii.controller;

public interface ValidationRule<T, S> {
  S validate(T obj);
}
