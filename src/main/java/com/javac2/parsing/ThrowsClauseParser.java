package com.javac2.parsing;

import com.javac2.ast.AstQualifiedType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public final class ThrowsClauseParser extends Parser<List<AstQualifiedType>> {
  public static final ThrowsClauseParser singleton = new ThrowsClauseParser();

  private ThrowsClauseParser() {}

  @Nullable
  @Override
  public ParseResult<List<AstQualifiedType>> tryParse(String s, int pos) {
    if (!s.startsWith("throws", pos))
      return null;
    pos += "throws".length();
    if (ParseUtils.skipWS(s, pos) == pos)
      return null;
    pos = ParseUtils.skipWS(s, pos);

    List<AstQualifiedType> exceptionTypes = new ArrayList<AstQualifiedType>();
    @Nullable ParseResult<AstQualifiedType> firstTypeResult =
        QualifiedIdentifierParser.singleton.tryParse(s, pos);
    if (firstTypeResult == null)
      return null;
    exceptionTypes.add(firstTypeResult.value);
    pos = firstTypeResult.newPos;

    for (;;) {
      pos = ParseUtils.skipWS(s, pos);
      if (s.charAt(pos++) != ',')
        return new ParseResult<List<AstQualifiedType>>(exceptionTypes, pos);
      pos = ParseUtils.skipWS(s, pos);

      @Nullable ParseResult<AstQualifiedType> typeResult =
          QualifiedIdentifierParser.singleton.tryParse(s, pos);
      if (typeResult == null)
        throw new IllegalArgumentException("Expected exception type.");
      exceptionTypes.add(typeResult.value);
      pos = typeResult.newPos;
    }
  }
}
