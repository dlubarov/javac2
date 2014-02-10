package com.javac2.parsing;

import javax.annotation.Nullable;

/**
 * Parses a comma with optional whitespace on either side.
 */
public class CommaWithWhitespaceParser extends Parser<Void> {
  public static final CommaWithWhitespaceParser singleton = new CommaWithWhitespaceParser();

  private CommaWithWhitespaceParser() {}

  @Nullable
  @Override
  public ParseResult<Void> tryParse(String s, int pos) {
    pos = ParseUtils.skipWS(s, pos);
    if (s.charAt(pos++) != ',')
      return null;
    pos = ParseUtils.skipWS(s, pos);
    return new ParseResult<Void>(null, pos);
  }
}
