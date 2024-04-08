package org.eomasters.utils;

import java.awt.Color;

public class Colors {

  /**
   * Creates a color from a rgba string. The string should start with "#" followed with a 6 digit hex value.
   *
   * @param rgb   the rgba string in the form '#000000'
   * @param alpha the alpha value
   * @return the created color
   */
  public static Color create(String rgb, float alpha) {
    if (rgb.length() > 7) {
      rgb = rgb.substring(0, 7);
    }
    int clippedAlpha = (int) (255 * Math.max(0, Math.min(1, alpha)));
    String alphaHex = Integer.toHexString(clippedAlpha);
    if (alphaHex.length() < 2) {
      alphaHex = "0" + alphaHex;
    }
    String rgba = rgb + alphaHex;
    return parseRGBA(rgba);
  }

  /**
   * Creates a color from a rgba string. The string should start with "#" followed with an 8 digit hex value.
   *
   * @param rgba the rgba string in the form '#00000000'
   * @return the created color
   */
  public static Color create(String rgba) {
    return parseRGBA(rgba);
  }

  private static Color parseRGBA(String rgba) {
    int r = Integer.parseInt(rgba.substring(1, 3), 16);
    int g = Integer.parseInt(rgba.substring(3, 5), 16);
    int b = Integer.parseInt(rgba.substring(5, 7), 16);
    int a = Integer.parseInt(rgba.substring(7, 9), 16);
    return new Color(r, g, b, a);
  }

}
