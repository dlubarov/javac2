package com.javac2.parsing.util;

import com.javac2.parsing.ParseResult;
import com.javac2.parsing.Parser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class DelimitedSequenceParser<T> extends Parser<List<T>> {
  private final Parser<T> componentParser;
  private final Parser<?> delimiterParser;

  public DelimitedSequenceParser(Parser<T> componentParser, Parser<?> delimiterParser) {
    this.componentParser = componentParser;
    this.delimiterParser = delimiterParser;
  }

  @Nullable
  @Override
  public ParseResult<List<T>> tryParse(String s, int pos) {
    @Nullable ParseResult<T> firstComponentResult = componentParser.tryParse(s, pos);
    if (firstComponentResult == null)
      return new ParseResult<List<T>>(new ArrayList<T>(0), pos);

    List<T> components = new ArrayList<T>();
    components.add(firstComponentResult.value);
    pos = firstComponentResult.newPos;

    for (;;) {
      @Nullable ParseResult<?> delimiterResult = delimiterParser.tryParse(s, pos);
      if (delimiterResult == null)
        return new ParseResult<List<T>>(components, pos);
      @Nullable ParseResult<T> componentResult = componentParser.tryParse(s, delimiterResult.newPos);
      if (componentResult == null)
        return new ParseResult<List<T>>(components, pos);

      components.add(componentResult.value);
      pos = componentResult.newPos;
    }
  }
}
