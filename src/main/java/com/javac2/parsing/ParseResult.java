package com.javac2.parsing;

public final class ParseResult<T> {
  public final T value;
  public final int newPos;

  public ParseResult(T value, int newPos) {
    this.value = value;
    this.newPos = newPos;
  }
}
