package com.javac2.parsing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

  public class IdentifierParserTest {
    @Test
    public void testNormalIdentifier() {
      ParseResult<String> result = IdentifierParser.singleton.tryParse("...aB12...", 3);
      assertNotNull(result);
      assertEquals("aB12", result.value);
      assertEquals(7, result.newPos);
    }
}
