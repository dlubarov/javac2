package com.javac2.parsing;

import com.javac2.ast.AstQualifiedType;
import com.javac2.ast.AstType;
import com.javac2.ast.AstTypeArgument;
import com.javac2.ast.AstTypedVariable;
import java.util.ArrayList;
import javax.annotation.Nullable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TypedVariableParserTest {
  @Test
  public void testTryParse() {
    @Nullable ParseResult<AstTypedVariable> result =
        TypedVariableParser.singleton.tryParse("...String str;", 3);
    assertNotNull(result);
    assertEquals(
        new AstTypedVariable(
            new AstType(
                new AstQualifiedType("String"),
                new ArrayList<AstTypeArgument>(),
                0),
            "str"),
        result.value);
    assertEquals(13, result.newPos);
  }
}
