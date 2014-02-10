package com.javac2.parsing;

import com.javac2.ast.AstImport;
import com.javac2.parsing.util.NonEmptyDelimitedSequenceParser;
import java.util.List;
import javax.annotation.Nullable;

public class ImportParser extends Parser<AstImport> {
  public static final ImportParser singleton = new ImportParser();

  private static final Parser<List<String>> proxy = new NonEmptyDelimitedSequenceParser<String>(
      IdentifierOrWildcardParser.singleton, new LiteralParser("."));

  private ImportParser() {}

  @Nullable
  @Override
  public ParseResult<AstImport> tryParse(String s, int pos) {
    if (!s.startsWith("import", pos))
      return null;
    pos += "import".length();
    if (ParseUtils.skipWS(s, pos) == pos)
      return null;
    pos = ParseUtils.skipWS(s, pos);

    boolean isStatic = false;
    if (s.startsWith("static", pos)) {
      int newPos = pos + "static".length();
      if (ParseUtils.skipWS(s, newPos) != newPos) {
        isStatic = true;
        pos = ParseUtils.skipWS(s, newPos);
      }
    }

    @Nullable ParseResult<List<String>> result = proxy.tryParse(s, pos);
    if (result == null)
      throw new ParseException(pos, "Expected identifier in import statement.");
    pos = result.newPos;

    for (int i = 0; i < result.value.size() - 1; ++i)
      if (result.value.get(i).equals("*"))
        throw new ParseException(pos, "Wildcards are only allowed at end of import.");

    pos = ParseUtils.skipWS(s, pos);
    if (s.charAt(pos++) != ';')
      throw new ParseException(pos, "Expected semicolon to terminate import statement.");

    return new ParseResult<AstImport>(new AstImport(isStatic, result.value), pos);
  }
}
