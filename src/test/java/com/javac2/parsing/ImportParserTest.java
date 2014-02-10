package com.javac2.parsing;

import com.javac2.ast.AstImport;
import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImportParserTest {
  @Test
  public void testNormalImport() {
    ParseResult<AstImport> result = ImportParser.singleton.tryParse(
        "import java.util.List;", 0);
    assertNotNull(result);
    assertEquals(
        new AstImport(false, Arrays.asList("java", "util", "List")),
        result.value);
  }

  @Test
  public void testWithOffset() {
    ParseResult<AstImport> result = ImportParser.singleton.tryParse(
        "import java.util.Map; import java.util.List;", 22);
    assertNotNull(result);
    assertEquals(
        new AstImport(false, Arrays.asList("java", "util", "List")),
        result.value);
  }

  @Test
  public void testStaticImport() {
    ParseResult<AstImport> result = ImportParser.singleton.tryParse(
        "import static java.lang.Math.sin;", 0);
    assertNotNull(result);
    assertEquals(
        new AstImport(true, Arrays.asList("java", "lang", "Math", "sin")),
        result.value);
  }

  @Test
  public void testWithWildcard() {
    ParseResult<AstImport> result = ImportParser.singleton.tryParse(
        "import java.util.*;", 0);
    assertNotNull(result);
    assertEquals(
        new AstImport(false, Arrays.asList("java", "util", "*")),
        result.value);
  }
}
