package org.eomasters.gui;

import java.awt.Component;
import java.awt.Container;

public class SwingUtils {

  public static void enableComponents(Container parent, boolean enable) {
    Component[] components = parent.getComponents();
    parent.setEnabled(enable);
    for (Component component : components) {
      if (component instanceof Container) {
        enableComponents((Container) component, enable);
      } else {
        component.setEnabled(enable);
      }
    }
  }

}
