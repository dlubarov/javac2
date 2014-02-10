package com.javac2.parsing;

public final class ParseUtils {
  private ParseUtils() {}

  public static int skipWS(String s, int pos) {
    // TODO: Watch out for end of file.
    while (pos < s.length()) {
      if (Character.isWhitespace(s.charAt(pos))) {
        ++pos;
      } else if (s.startsWith("//", pos)) {
        pos += 2;
        while (s.charAt(pos) != '\n')
          ++pos;
      } else if (s.startsWith("/*", pos)) {
        pos += 2;
        while (!s.startsWith("*/", pos))
          ++pos;
      } else break;
    }
    return pos;
  }
}
