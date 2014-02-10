package com.javac2.util;

public class Check {
  private Check() {}

  public static <T> T notNull(T reference) {
    return Check.notNull(reference, "Reference was null.");
  }
  
  public static <T> T notNull(T reference, String format, Object... args) {
    Check.that(reference != null, format, args);
    return reference;
  }
  
  public static void that(boolean condition) {
    Check.that(condition, "Condition was not met.");
  }
  
  public static void that(boolean condition, String format, Object... args) {
    if (!condition)
      throw new RuntimeException(String.format(format, args));
  }
}
