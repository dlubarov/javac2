package com.javac2.parsing;

import com.javac2.ast.AstQualifiedType;
import com.javac2.parsing.util.NonEmptyDelimitedSequenceParser;
import com.javac2.parsing.util.NonEmptySequenceParser;
import java.util.List;
import javax.annotation.Nullable;

public class QualifiedIdentifierParser extends Parser<AstQualifiedType> {
  public static final QualifiedIdentifierParser singleton = new QualifiedIdentifierParser();

  private static final Parser<List<String>> proxy = new NonEmptyDelimitedSequenceParser<String>(
      IdentifierParser.singleton, new LiteralParser("."));

  private QualifiedIdentifierParser() {}

  @Nullable
  @Override
  public ParseResult<AstQualifiedType> tryParse(String s, int pos) {
    ParseResult<List<String>> identifierListResult = proxy.tryParse(s, pos);
    if (identifierListResult == null)
      return null;

    return new ParseResult<AstQualifiedType>(
        new AstQualifiedType(identifierListResult.value), identifierListResult.newPos);
  }
}
