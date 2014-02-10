package com.javac2.parsing;

import javax.annotation.Nullable;

public class IdentifierOrWildcardParser extends Parser<String> {
  public static final IdentifierOrWildcardParser singleton = new IdentifierOrWildcardParser();

  private IdentifierOrWildcardParser() {}

  @Nullable
  @Override
  public ParseResult<String> tryParse(String s, int pos) {
    if (s.charAt(pos) == '*')
      return new ParseResult<String>("*", pos + 1);
    return IdentifierParser.singleton.tryParse(s, pos);
  }
}
