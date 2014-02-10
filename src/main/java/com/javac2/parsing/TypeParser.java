package com.javac2.parsing;

import com.javac2.ast.AstQualifiedType;
import com.javac2.ast.AstType;
import com.javac2.ast.AstTypeArgument;
import com.javac2.parsing.util.DelimitedSequenceParser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class TypeParser extends Parser<AstType> {
  public static final TypeParser singleton = new TypeParser();

  private static final Parser<List<AstTypeArgument>> typeListParser =
      new DelimitedSequenceParser<AstTypeArgument>(
          TypeArgumentParser.singleton, CommaWithWhitespaceParser.singleton);

  private TypeParser() {}

  @Nullable
  @Override
  public ParseResult<AstType> tryParse(String s, int pos) {
    @Nullable ParseResult<AstQualifiedType> rawTypeResult =
        QualifiedIdentifierParser.singleton.tryParse(s, pos);
    if (rawTypeResult == null)
      return null;
    pos = rawTypeResult.newPos;
    pos = ParseUtils.skipWS(s, pos);

    List<AstTypeArgument> genericArgs;
    if (s.charAt(pos) == '<') {
      ++pos;
      pos = ParseUtils.skipWS(s, pos);
      ParseResult<List<AstTypeArgument>> genericArgsResult = typeListParser.tryParse(s, pos);
      genericArgs = genericArgsResult.value;
      pos = genericArgsResult.newPos;
      pos = ParseUtils.skipWS(s, pos);
      if (s.charAt(pos++) != '>')
        throw new ParseException(pos, "Expecting '>' to close generic argument list.");
    } else
      genericArgs = new ArrayList<AstTypeArgument>();

    int numArrayDimensions = 0;
    for (;;) {
      pos = ParseUtils.skipWS(s, pos);
      if (s.charAt(pos) != '[')
        break;
      pos = ParseUtils.skipWS(s, pos + 1);
      if (s.charAt(pos++) != ']')
        throw new ParseException(pos, "Expecting ']' after '['.");
      ++numArrayDimensions;
    }

    return new ParseResult<AstType>(
        new AstType(rawTypeResult.value, genericArgs, numArrayDimensions), pos);
  }
}
