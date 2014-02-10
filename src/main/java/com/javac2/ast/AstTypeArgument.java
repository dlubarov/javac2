package com.javac2.ast;

import com.javac2.util.EqualsBuilder;
import com.javac2.util.HashCodeBuilder;

/**
 * A generic type argument. This is usually just a type, but may also be an upper or lower bound.
 *
 * Examples:
 * - String
 * - ? extends Number
 * - ? super String
 *
 * @see com.javac2.ast.AstTypeParameter
 */
public class AstTypeArgument {
  public final AstType type;
  public final Relation relation;

  public AstTypeArgument(AstType type, Relation relation) {
    this.type = type;
    this.relation = relation;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AstTypeArgument))
      return false;
    AstTypeArgument that = (AstTypeArgument) o;

    return new EqualsBuilder()
        .append(this.type, that.type)
        .append(this.relation, that.relation)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(type)
        .append(relation)
        .toHashCode();
  }

  @Override
  public String toString() {
    switch (relation) {
      case IDENTICAL:
        return type.toString();
      case ANY_SUBTYPE:
        return "? extends " + type;
      case ANY_SUPERTYPE:
        return "? super " + type;
      default:
        throw new AssertionError();
    }
  }

  public enum Relation {
    IDENTICAL,
    ANY_SUBTYPE,
    ANY_SUPERTYPE,
  }
}
