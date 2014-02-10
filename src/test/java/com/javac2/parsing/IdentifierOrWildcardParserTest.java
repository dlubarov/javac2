package com.javac2.parsing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IdentifierOrWildcardParserTest {
  @Test
  public void testNormalIdentifier() {
    ParseResult<String> result = IdentifierOrWildcardParser.singleton.tryParse("...aB12...", 3);
    assertNotNull(result);
    assertEquals("aB12", result.value);
    assertEquals(7, result.newPos);
  }

  @Test
  public void testWildcard() {
    ParseResult<String> result = IdentifierOrWildcardParser.singleton.tryParse("...*...", 3);
    assertNotNull(result);
    assertEquals("*", result.value);
    assertEquals(4, result.newPos);
  }
}
