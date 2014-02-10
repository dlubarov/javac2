package com.javac2.parsing;

import com.javac2.ast.AstType;
import com.javac2.ast.AstTypeParameter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class TypeParameterParser extends Parser<AstTypeParameter> {
  public static final TypeParameterParser singleton = new TypeParameterParser();

  private TypeParameterParser() {}

  @Nullable
  @Override
  public ParseResult<AstTypeParameter> tryParse(String s, int pos) {
    @Nullable ParseResult<String> nameResult = IdentifierParser.singleton.tryParse(s, pos);
    if (nameResult == null)
      throw new ParseException(pos, "Expected name of type parameter.");
    pos = ParseUtils.skipWS(s, nameResult.newPos);

    List<AstType> upperBounds = new ArrayList<AstType>();
    if (s.startsWith("extends", pos)) {
      pos += "extends".length();
      if (ParseUtils.skipWS(s, pos) == pos)
        throw new ParseException(pos, "Expected whitespace after 'extends' in type parameter.");
      pos = ParseUtils.skipWS(s, pos);

      @Nullable ParseResult<AstType> firstBoundResult = TypeParser.singleton.tryParse(s, pos);
      if (firstBoundResult == null)
        throw new ParseException(pos, "Expected type after 'extends'.");
      upperBounds.add(firstBoundResult.value);
      pos = ParseUtils.skipWS(s, firstBoundResult.newPos);

      while (s.charAt(pos) == '&') {
        pos = ParseUtils.skipWS(s, pos + 1);
        @Nullable ParseResult<AstType> boundResult = TypeParser.singleton.tryParse(s, pos);
        if (boundResult == null)
          throw new ParseException(pos, "Expected type after 'extends'.");
        upperBounds.add(boundResult.value);
        pos = ParseUtils.skipWS(s, boundResult.newPos);
      }
    }

    return new ParseResult<AstTypeParameter>(new AstTypeParameter(nameResult.value, upperBounds), pos);
  }
}
