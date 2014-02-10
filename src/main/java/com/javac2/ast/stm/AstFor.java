package com.javac2.ast.stm;

import com.javac2.ast.exp.AstExpression;
import java.util.List;

public class AstFor extends AstStatement {
  public final List<AstStatement> initStatements;
  public final AstExpression condition;
  public final List<AstStatement> updateStatements;
  public final AstStatement body;

  public AstFor(List<AstStatement> initStatements, AstExpression condition, List<AstStatement> updateStatements, AstStatement body) {
    this.initStatements = initStatements;
    this.condition = condition;
    this.updateStatements = updateStatements;
    this.body = body;
  }
}
