package com.javac2.ast;

import com.javac2.util.EqualsBuilder;
import com.javac2.util.HashCodeBuilder;

public class AstTypedVariable {
  public final AstType type;
  public final String name;

  public AstTypedVariable(AstType type, String name) {
    this.type = type;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AstTypedVariable))
      return false;
    AstTypedVariable that = (AstTypedVariable) o;

    return new EqualsBuilder()
        .append(this.type, that.type)
        .append(this.name, that.name)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(type)
        .append(name)
        .toHashCode();
  }

  @Override
  public String toString() {
    return type + " " + name;
  }
}
