package com.javac2.parsing;

import com.javac2.ast.AstTypeParameter;
import javax.annotation.Nullable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TypeParameterParserTest {
  @Test
  public void testBasic() {
    @Nullable ParseResult<AstTypeParameter> result = TypeParameterParser.singleton.tryParse(
        "<T extends Serializable>", 1);
    assertNotNull(result);
    assertEquals("T", result.value.name);
    assertEquals(1, result.value.upperBounds.size());
    assertEquals(23, result.newPos);
  }

  @Test
  public void testMultipleBounds() {
    @Nullable ParseResult<AstTypeParameter> result = TypeParameterParser.singleton.tryParse(
        "<Thing extends Collection<U> & Comparable<Collection<U>> & Serializable>", 1);
    assertNotNull(result);
    assertEquals("Thing", result.value.name);
    assertEquals(3, result.value.upperBounds.size());
    assertEquals(71, result.newPos);
  }
}
