package com.javac2.parsing.util;

import com.javac2.parsing.ParseResult;
import com.javac2.parsing.Parser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class NonEmptySequenceParser<T> extends Parser<List<T>> {
  private final Parser<T> componentParser;

  public NonEmptySequenceParser(Parser<T> componentParser) {
    this.componentParser = componentParser;
  }

  @Nullable
  @Override
  public ParseResult<List<T>> tryParse(String s, int pos) {
    @Nullable ParseResult<T> firstComponentResult = componentParser.tryParse(s, pos);
    if (firstComponentResult == null)
      return null;

    List<T> components = new ArrayList<T>();
    components.add(firstComponentResult.value);
    pos = firstComponentResult.newPos;

    for (;;) {
      @Nullable ParseResult<T> componentResult = componentParser.tryParse(s, pos);
      if (componentResult == null)
        return new ParseResult<List<T>>(components, pos);
      components.add(componentResult.value);
      pos = componentResult.newPos;
    }
  }
}
