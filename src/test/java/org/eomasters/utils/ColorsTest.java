package org.eomasters.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.Test;

class ColorsTest {

  @Test
  void create() {
    Color color = Colors.create("#004513FF");
    assertEquals(0, color.getRed());
    assertEquals(69, color.getGreen());
    assertEquals(19, color.getBlue());
    assertEquals(255, color.getAlpha());
  }

  @Test
  void createWithAlpha() {
    Color color = Colors.create("#FFAA06", 0.5f);
    assertEquals(255, color.getRed());
    assertEquals(170, color.getGreen());
    assertEquals(6, color.getBlue());
    assertEquals(127, color.getAlpha());
  }

  @Test
  void createWithAlphaAboveMax() {
    Color color = Colors.create("#FFAA06", 3.5f);
    assertEquals(255, color.getRed());
    assertEquals(170, color.getGreen());
    assertEquals(6, color.getBlue());
    assertEquals(255, color.getAlpha());
  }

  @Test
  void createWithAlphaBelowMin() {
    Color color = Colors.create("#FFAA06", -0.5f);
    assertEquals(255, color.getRed());
    assertEquals(170, color.getGreen());
    assertEquals(6, color.getBlue());
    assertEquals(0, color.getAlpha());
  }
}