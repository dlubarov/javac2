package com.javac2.ast.stm;

import com.javac2.ast.AstAnnotation;
import com.javac2.ast.AstType;
import com.javac2.ast.exp.AstExpression;
import java.util.List;

public class AstForEach extends AstStatement {
  public final List<AstAnnotation> variableAnnotations;
  public final boolean variableIsFinal;
  public final AstType variableType;
  public final String variableName;
  public final AstExpression source;
  public final AstStatement body;

  public AstForEach(
      List<AstAnnotation> variableAnnotations,
      boolean variableIsFinal,
      AstType variableType,
      String variableName,
      AstExpression source,
      AstStatement body) {
    this.variableAnnotations = variableAnnotations;
    this.variableIsFinal = variableIsFinal;
    this.variableType = variableType;
    this.variableName = variableName;
    this.source = source;
    this.body = body;
  }
}
