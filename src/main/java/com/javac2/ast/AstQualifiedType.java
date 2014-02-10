package com.javac2.ast;

import java.util.Arrays;
import java.util.List;

public final class AstQualifiedType {
  public final List<String> components;

  public AstQualifiedType(List<String> components) {
    this.components = components;
  }

  public AstQualifiedType(String... components) {
    this(Arrays.asList(components));
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof AstQualifiedType && components.equals(((AstQualifiedType) o).components);
  }

  @Override
  public int hashCode() {
    return components.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String component : components) {
      if (sb.length() > 0)
        sb.append('.');
      sb.append(component);
    }
    return sb.toString();
  }
}
