package com.javac2.parsing;

import javax.annotation.Nullable;

public final class IdentifierParser extends Parser<String> {
  public static final IdentifierParser singleton = new IdentifierParser();

  private IdentifierParser() {}

  @Nullable
  @Override
  public ParseResult<String> tryParse(String s, int pos) {
    if (!Character.isJavaIdentifierStart(s.charAt(pos)))
      return null;
    StringBuilder sb = new StringBuilder().append(s.charAt(pos++));
    while (pos < s.length() && Character.isJavaIdentifierPart(s.charAt(pos)))
      sb.append(s.charAt(pos++));
    return new ParseResult<String>(sb.toString(), pos);
  }
}
