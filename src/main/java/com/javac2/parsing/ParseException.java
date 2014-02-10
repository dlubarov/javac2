package com.javac2.parsing;

public final class ParseException extends RuntimeException {
  private final int pos;

  public ParseException(int pos, String format, Object... args) {
    super(String.format(format, args));
    this.pos = pos;
  }

  public int getPos() {
    return pos;
  }
}
