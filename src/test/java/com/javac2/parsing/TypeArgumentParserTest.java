package com.javac2.parsing;

import com.javac2.ast.AstQualifiedType;
import com.javac2.ast.AstType;
import com.javac2.ast.AstTypeArgument;
import java.util.ArrayList;
import javax.annotation.Nullable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TypeArgumentParserTest {
  @Test
  public void testNormalTypeArgument() {
    @Nullable ParseResult<AstTypeArgument> result =
        TypeArgumentParser.singleton.tryParse("...String...", 3);
    assertNotNull(result);
    assertEquals(
        new AstTypeArgument(
            new AstType(
                new AstQualifiedType("String"),
                new ArrayList<AstTypeArgument>(),
                0),
            AstTypeArgument.Relation.IDENTICAL),
        result.value);
    assertEquals(9, result.newPos);
  }

  @Test
  public void testExtends() {
    @Nullable ParseResult<AstTypeArgument> result =
        TypeArgumentParser.singleton.tryParse("<? extends String>", 1);
    assertNotNull(result);
    assertEquals(
        new AstTypeArgument(
            new AstType(
                new AstQualifiedType("String"),
                new ArrayList<AstTypeArgument>(),
                0),
            AstTypeArgument.Relation.ANY_SUBTYPE),
        result.value);
    assertEquals(17, result.newPos);
  }

  @Test
  public void testSuper() {
    @Nullable ParseResult<AstTypeArgument> result =
        TypeArgumentParser.singleton.tryParse("<? super String>", 1);
    assertNotNull(result);
    assertEquals(
        new AstTypeArgument(
            new AstType(
                new AstQualifiedType("String"),
                new ArrayList<AstTypeArgument>(),
                0),
            AstTypeArgument.Relation.ANY_SUPERTYPE),
        result.value);
    assertEquals(15, result.newPos);
  }
}
