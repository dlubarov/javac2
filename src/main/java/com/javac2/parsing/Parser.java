package com.javac2.parsing;

import javax.annotation.Nullable;

public abstract class Parser<T> {
  @Nullable
  public abstract ParseResult<T> tryParse(String s, int pos);
}
