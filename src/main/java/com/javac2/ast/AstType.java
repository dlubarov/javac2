package com.javac2.ast;

import com.javac2.util.EqualsBuilder;
import com.javac2.util.HashCodeBuilder;
import java.util.List;

public class AstType {
  public final AstQualifiedType rawType;
  public final List<AstTypeArgument> genericArguments;
  public final int numArrayDimensions;

  public AstType(AstQualifiedType rawType, List<AstTypeArgument> genericArguments, int numArrayDimensions) {
    this.rawType = rawType;
    this.genericArguments = genericArguments;
    this.numArrayDimensions = numArrayDimensions;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AstType))
      return false;
    AstType that = (AstType) o;

    return new EqualsBuilder()
        .append(this.rawType, that.rawType)
        .append(this.genericArguments, that.genericArguments)
        .append(this.numArrayDimensions, that.numArrayDimensions)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(rawType)
        .append(genericArguments)
        .append(numArrayDimensions)
        .toHashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append(rawType).append(genericArguments);
    for (int i = 0; i < numArrayDimensions; ++i)
      sb.append("[]");
    return sb.toString();
  }
}
