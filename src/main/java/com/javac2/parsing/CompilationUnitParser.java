package com.javac2.parsing;

import com.javac2.ast.AstFile;
import javax.annotation.Nullable;

public final class CompilationUnitParser extends Parser<AstFile> {
  public static final CompilationUnitParser SINGLETON = new CompilationUnitParser();

  private CompilationUnitParser() {}

  @Nullable
  @Override
  public ParseResult<AstFile> tryParse(String s, int pos) {
    throw new UnsupportedOperationException("TODO");
  }
}
