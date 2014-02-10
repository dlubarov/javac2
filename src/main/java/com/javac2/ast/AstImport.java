package com.javac2.ast;

import com.javac2.util.EqualsBuilder;
import com.javac2.util.HashCodeBuilder;
import java.util.List;

public class AstImport {
  private final boolean isStatic;
  private final List<String> parts;

  public AstImport(boolean isStatic, List<String> parts) {
    this.isStatic = isStatic;
    this.parts = parts;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AstImport))
      return false;
    AstImport that = (AstImport) o;

    return new EqualsBuilder()
        .append(this.isStatic, that.isStatic)
        .append(this.parts, that.parts)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(isStatic)
        .append(parts)
        .toHashCode();
  }
}
