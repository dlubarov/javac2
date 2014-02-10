package com.javac2.parsing;

import com.javac2.ast.AstType;
import com.javac2.ast.AstTypedVariable;
import javax.annotation.Nullable;

/**
 * Parses a type followed by a variable. Does not parse any modifiers or annotations.
 *
 * Examples:
 * - "String foo"
 * - "List<Integer>[] bar"
 * - "int[] baz[][]"
 */
public class TypedVariableParser extends Parser<AstTypedVariable> {
  public static final TypedVariableParser singleton = new TypedVariableParser();

  private TypedVariableParser() {}

  @Nullable
  @Override
  public ParseResult<AstTypedVariable> tryParse(String s, int pos) {
    @Nullable ParseResult<AstType> typeResult = TypeParser.singleton.tryParse(s, pos);
    if (typeResult == null)
      return null;
    pos = typeResult.newPos;
    pos = ParseUtils.skipWS(s, pos);

    @Nullable ParseResult<String> nameResult = IdentifierParser.singleton.tryParse(s, pos);
    if (nameResult == null)
      return null;
    pos = nameResult.newPos;

    int extraArrayDimensions = 0;
    for (;;) {
      pos = ParseUtils.skipWS(s, pos);
      if (s.charAt(pos) != '[')
        break;
      pos = ParseUtils.skipWS(s, pos + 1);
      if (s.charAt(pos++) != ']')
        throw new ParseException(pos, "Expecting ']' after '['.");
      ++extraArrayDimensions;
    }

    AstType type = new AstType(typeResult.value.rawType, typeResult.value.genericArguments,
        typeResult.value.numArrayDimensions + extraArrayDimensions);
    return new ParseResult<AstTypedVariable>(new AstTypedVariable(type, nameResult.value), pos);
  }
}
