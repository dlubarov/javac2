package com.javac2.parsing.util;

import com.javac2.parsing.ParseResult;
import com.javac2.parsing.Parser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class SequenceParser<T> extends Parser<List<T>> {
  private final Parser<T> componentParser;

  public SequenceParser(Parser<T> componentParser) {
    this.componentParser = componentParser;
  }

  @Nullable
  @Override
  public ParseResult<List<T>> tryParse(String s, int pos) {
    List<T> components = new ArrayList<T>();
    for (;;) {
      @Nullable ParseResult<T> componentResult = componentParser.tryParse(s, pos);
      if (componentResult == null)
        return new ParseResult<List<T>>(components, pos);
      components.add(componentResult.value);
      pos = componentResult.newPos;
    }
  }
}
