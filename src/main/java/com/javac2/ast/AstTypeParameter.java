package com.javac2.ast;

import java.util.List;

/**
 * A type parameter (part of a class or method declaration).
 *
 * Some examples:
 * - T
 * - T extends Serializable
 * - T extends Collection<U> & Comparable<Collection<U>> & Serializable
 *
 * @see com.javac2.ast.AstTypeArgument
 */
public class AstTypeParameter {
  public final String name;
  public final List<AstType> upperBounds;

  public AstTypeParameter(String name, List<AstType> upperBounds) {
    this.name = name;
    this.upperBounds = upperBounds;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append(name);
    if (!upperBounds.isEmpty()) {
      sb.append(" extends ").append(upperBounds.get(0));
      for (int i = 1; i < upperBounds.size(); ++i)
        sb.append(" & ").append(upperBounds.get(i));
    }
    return sb.toString();
  }
}
