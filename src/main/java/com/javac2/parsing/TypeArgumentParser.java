package com.javac2.parsing;

import com.javac2.ast.AstType;
import com.javac2.ast.AstTypeArgument;
import javax.annotation.Nullable;

public class TypeArgumentParser extends Parser<AstTypeArgument> {
  public static final TypeArgumentParser singleton = new TypeArgumentParser();

  private TypeArgumentParser() {}

  @Nullable
  @Override
  public ParseResult<AstTypeArgument> tryParse(String s, int pos) {
    if (s.charAt(pos) == '?') {
      pos = ParseUtils.skipWS(s, pos + 1);
      if (s.startsWith("extends", pos)) {
        pos += "extends".length();
        if (ParseUtils.skipWS(s, pos) == pos)
          throw new ParseException(pos, "Expected whitespace after 'extends'.");
        pos = ParseUtils.skipWS(s, pos);
        ParseResult<AstType> typeResult = TypeParser.singleton.tryParse(s, pos);
        if (typeResult == null)
          throw new ParseException(pos, "Expected type after 'extends'.");
        return new ParseResult<AstTypeArgument>(
            new AstTypeArgument(typeResult.value, AstTypeArgument.Relation.ANY_SUBTYPE),
            typeResult.newPos);
      }
      if (s.startsWith("super", pos)) {
        pos += "super".length();
        if (ParseUtils.skipWS(s, pos) == pos)
          throw new ParseException(pos, "Expected whitespace after 'super'.");
        pos = ParseUtils.skipWS(s, pos);
        ParseResult<AstType> typeResult = TypeParser.singleton.tryParse(s, pos);
        if (typeResult == null)
          throw new ParseException(pos, "Expected type after 'super'.");
        return new ParseResult<AstTypeArgument>(
            new AstTypeArgument(typeResult.value, AstTypeArgument.Relation.ANY_SUPERTYPE),
            typeResult.newPos);
      }
      throw new ParseException(pos, "Expected 'extends' or 'super' after wildcard.");
    }

    ParseResult<AstType> typeResult = TypeParser.singleton.tryParse(s, pos);
    if (typeResult == null)
      throw new ParseException(pos, "Expected type argument after '<'.");
    return new ParseResult<AstTypeArgument>(
        new AstTypeArgument(typeResult.value, AstTypeArgument.Relation.IDENTICAL),
        typeResult.newPos);
  }
}
