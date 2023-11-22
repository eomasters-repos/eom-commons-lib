package org.eomasters.gui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class UriFieldTest {

  @Test
  void parseMarkDown() {
    String markdown = "[abc](def)";
    Pattern pattern = Pattern.compile("\\A\\[(.*?)]\\((.*?)\\)\\Z");
    Matcher matcher = pattern.matcher(markdown);
    assertTrue(matcher.find());
    assertEquals(2, matcher.groupCount());
    assertEquals("abc", matcher.group(1));
    assertEquals("def", matcher.group(2));
  }
}