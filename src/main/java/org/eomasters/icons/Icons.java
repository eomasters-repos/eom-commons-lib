/*-
 * ========================LICENSE_START=================================
 * EOMTBX - EOMasters Toolbox for SNAP
 * -> https://www.eomasters.org/sw/EOMTBX
 * ======================================================================
 * Copyright (C) 2023 Marco Peters
 * ======================================================================
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * -> http://www.gnu.org/licenses/gpl-3.0.html
 * =========================LICENSE_END==================================
 */

package org.eomasters.icons;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.eomasters.icons.Icon.SIZE;

/**
 * Provides essentials icons
 */
public final class Icons {


  public static void main(String[] args) throws IllegalAccessException {
    final JFrame frame = new JFrame("Icons");
    frame.setPreferredSize(new Dimension(400, 400));
    Container contentPane = frame.getContentPane();
    SIZE size = SIZE.S32;
    Field[] fields = Icons.class.getFields();
    for (Field field : fields) {
      if (Icon.class.isAssignableFrom(field.getType())) {
        contentPane.add(new JLabel(((Icon) field.get(null)).getImageIcon(size)));
      }
    }
    int gridsize = (int) Math.ceil(Math.sqrt(contentPane.getComponents().length));
    contentPane.setLayout(new GridLayout(gridsize, gridsize));
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    SwingUtilities.invokeLater(() -> frame.setVisible(true));
  }

  /**
   * An icon showing an arrow down.
   */
  public static final Icon ARROW_DOWN = new RasterIcon("essentials/ArrowDown");
  /**
   * An icon showing an arrow up.
   */
  public static final Icon ARROW_UP = new RasterIcon("essentials/ArrowUp");
  /**
   * An icon showing an arrow left.
   */
  public static final Icon ARROW_Left = new RasterIcon("essentials/ArrowLeft");
  /**
   * An icon showing an arrow right.
   */
  public static final Icon ARROW_RIGHT = new RasterIcon("essentials/ArrowRight");
  /**
   * An icon showing a bug.
   */
  public static final Icon BUG = new RasterIcon("essentials/Bug");
  /**
   * An icon showing checkmark in circle.
   */
  public static final Icon CHECKMARK = new RasterIcon("essentials/Checkmark");
  /**
   * An icon showing a clock.
   */
  public static final Icon CLOCK = new RasterIcon("essentials/Clock");
  /**
   * An icon showing a cloud.
   */
  public static final Icon CLOUD = new RasterIcon("essentials/Cloud");
  /**
   * An icon showing a cloud, with arrow up into the cloud.
   */
  public static final Icon CLOUD_UP = new RasterIcon("essentials/CloudUp");
  /**
   * An icon showing a cloud, with arrow down out of the cloud.
   */
  public static final Icon CLOUD_DOWN = new RasterIcon("essentials/CloudDown");
  /**
   * An icon representing code or a command line prompt.
   */
  public static final Icon CODE = new RasterIcon("essentials/Code");
  /**
   * An icon showing a computer.
   */
  public static final Icon COMPUTER = new RasterIcon("essentials/Computer");
  /**
   * An icon showing a credit card.
   */
  public static final Icon CREDIT_CARD = new RasterIcon("essentials/CreditCard");
  /**
   * An icon showing a database barrel.
   */
  public static final Icon DATABASE = new RasterIcon("essentials/Database");
  /**
   * An icon showing a dollar sign.
   */
  public static final Icon DOLLAR = new RasterIcon("essentials/Dollar");
  /**
   * An icon showing a drawer.
   */
  public static final Icon DRAWER = new RasterIcon("essentials/Drawer");
  /**
   * An icon showing a euro sign.
   */
  public static final Icon EURO = new RasterIcon("essentials/Euro");
  /**
   * An icon representing an export action.
   */
  public static final Icon EXPORT = new RasterIcon("essentials/Export");
  /**
   * An icon showing an eye.
   */
  public static final Icon EYE = new RasterIcon("essentials/Eye");
  /**
   * An icon showing an eye crossed.
   */
  public static final Icon EYE_CROSSED = new RasterIcon("essentials/EyeCrossed");
  /**
   * An icon showing a filter.
   */
  public static final Icon FILTER = new RasterIcon("essentials/Filter");
  /**
   * An icon showing a flag.
   */
  public static final Icon FLAG = new RasterIcon("essentials/Flag");
  /**
   * An icon showing a folder.
   */
  public static final Icon FOLDER = new RasterIcon("essentials/Folder");
  /**
   * An icon representing fullscreen mode.
   */
  public static final Icon FULLSCREEN = new RasterIcon("essentials/Fullscreen");
  /**
   * An icon showing a gear.
   */
  public static final Icon GEAR = new RasterIcon("essentials/Gear");
  /**
   * An icon showing a globe.
   */
  public static final Icon GLOBE = new RasterIcon("essentials/Globe");
  /**
   * An icon showing a graph.
   */
  public static final Icon GRAPH = new RasterIcon("essentials/Graph");
  /**
   * An icon representing an import action.
   */
  public static final Icon IMPORT = new RasterIcon("essentials/Import");
  /**
   * An icon with an 'i'.
   */
  public static final Icon INFO = new RasterIcon("essentials/Info");
  /**
   * An icon showing a map marker.
   */
  public static final Icon MAP_MARKER = new RasterIcon("essentials/MapMarker");
  /**
   * An icon showing a minus sign in a circle.
   */
  public static final Icon MINUS = new RasterIcon("essentials/Minus");
  /**
   * An icon showing a paper.
   */
  public static final Icon PAPER = new RasterIcon("essentials/Paper");
  /**
   * An icon showing a pen.
   */
  public static final Icon PEN = new RasterIcon("essentials/Pen");
  /**
   * An icon showing two people.
   */
  public static final Icon PEOPLE = new RasterIcon("essentials/People");
  /**
   * An icon showing simple pie chart.
   */
  public static final Icon PIE_CHART1 = new RasterIcon("essentials/PieChart1");
  /**
   * An icon showing more complex pie chart.
   */
  public static final Icon PIE_CHART2 = new RasterIcon("essentials/PieChart2");
  /**
   * An icon showing a pin.
   */
  public static final Icon PIN = new RasterIcon("essentials/Pin");
  /**
   * An icon showing a pipette.
   */
  public static final Icon PIPETTE = new RasterIcon("essentials/Pipette");
  /**
   * An icon showing a plus sign in a circle.
   */
  public static final Icon PLUS = new RasterIcon("essentials/Plus");
  /**
   * An icon showing a mouse pointer.
   */
  public static final Icon POINTER = new RasterIcon("essentials/Pointer");
  /**
   * An icon showing a question mark.
   */
  public static final Icon QUESTION_MARK = new RasterIcon("essentials/QuestionMark");
  /**
   * An icon showing a reload sign.
   */
  public static final Icon RELOAD = new RasterIcon("essentials/Reload");
  /**
   * An icon representing ending fullscreen mode.
   */
  public static final Icon SHRINK_SCREEN = new RasterIcon("essentials/ShrinkScreen");
  /**
   * An icon showing a speech bubble.
   */
  public static final Icon SPEECH_BUBBLE = new RasterIcon("essentials/SpeechBubble");
  /**
   * An icon showing a thumbs down.
   */
  public static final Icon THUMBS_DOWN = new RasterIcon("essentials/ThumbsDown");
  /**
   * An icon showing a thumbs up.
   */
  public static final Icon THUMBS_UP = new RasterIcon("essentials/ThumbsUp");

  private Icons() {
  }

}
