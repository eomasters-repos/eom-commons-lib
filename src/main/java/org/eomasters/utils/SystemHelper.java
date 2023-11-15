package org.eomasters.utils;

public class SystemHelper {

  public static boolean isHeadless() {
    return java.lang.System.getProperty("java.awt.headless", "false").equals("true");
  }

}
