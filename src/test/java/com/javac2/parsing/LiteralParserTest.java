package com.javac2.parsing;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LiteralParserTest {
  @Test
  public void testTryParse() {
    @NotNull ParseResult<Void> result = new LiteralParser("class").tryParse("...class...", 3);
    assertNotNull(result);
    assertEquals(8, result.newPos);
  }
}
