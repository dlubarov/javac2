package com.javac2.ast.stm;

import java.util.List;

public class AstBlock extends AstStatement {
  public final List<AstStatement> parts;

  public AstBlock(List<AstStatement> parts) {
    this.parts = parts;
  }
}
