package com.javac2.parsing;

import javax.annotation.Nullable;

public class LiteralParser extends Parser<Void> {
  private final String literal;

  public LiteralParser(String literal) {
    this.literal = literal;
  }

  @Nullable
  @Override
  public ParseResult<Void> tryParse(String s, int pos) {
    if (s.startsWith(literal, pos))
      return new ParseResult<Void>(null, pos + literal.length());
    return null;
  }
}
