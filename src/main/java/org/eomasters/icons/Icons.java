/*-
 * ========================LICENSE_START=================================
 * EOM Commons - Library of common utilities for Java
 * -> https://www.eomasters.org/
 * ======================================================================
 * Copyright (C) 2023 Marco Peters
 * ======================================================================
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
    SIZE size = SIZE.S16;
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
  public static final Icon ARROW_DOWN = new RasterIcon("/icons/essentials/ArrowDown");
  /**
   * An icon showing an arrow up.
   */
  public static final Icon ARROW_UP = new RasterIcon("/icons/essentials/ArrowUp");
  /**
   * An icon showing an arrow left.
   */
  public static final Icon ARROW_Left = new RasterIcon("/icons/essentials/ArrowLeft");
  /**
   * An icon showing an arrow right.
   */
  public static final Icon ARROW_RIGHT = new RasterIcon("/icons/essentials/ArrowRight");
  /**
   * An icon showing a bug.
   */
  public static final Icon BUG = new RasterIcon("/icons/essentials/Bug");
  /**
   * An icon showing checkmark in circle.
   */
  public static final Icon CHECKMARK = new RasterIcon("/icons/essentials/Checkmark");
  /**
   * An icon showing a clock.
   */
  public static final Icon CLOCK = new RasterIcon("/icons/essentials/Clock");
  /**
   * An icon showing a cloud.
   */
  public static final Icon CLOUD = new RasterIcon("/icons/essentials/Cloud");
  /**
   * An icon showing a cloud, with arrow up into the cloud.
   */
  public static final Icon CLOUD_UP = new RasterIcon("/icons/essentials/CloudUp");
  /**
   * An icon showing a cloud, with arrow down out of the cloud.
   */
  public static final Icon CLOUD_DOWN = new RasterIcon("/icons/essentials/CloudDown");
  /**
   * An icon representing code or a command line prompt.
   */
  public static final Icon CODE = new RasterIcon("/icons/essentials/Code");
  /**
   * An icon showing a computer.
   */
  public static final Icon COMPUTER = new RasterIcon("/icons/essentials/Computer");
  /**
   * An icon showing a credit card.
   */
  public static final Icon CREDIT_CARD = new RasterIcon("/icons/essentials/CreditCard");
  /**
   * An icon showing a database barrel.
   */
  public static final Icon DATABASE = new RasterIcon("/icons/essentials/Database");
  /**
   * An icon showing a dollar sign.
   */
  public static final Icon DOLLAR = new RasterIcon("/icons/essentials/Dollar");
  /**
   * An icon showing a drawer.
   */
  public static final Icon DRAWER = new RasterIcon("/icons/essentials/Drawer");
  /**
   * An icon showing a euro sign.
   */
  public static final Icon EURO = new RasterIcon("/icons/essentials/Euro");
  /**
   * An icon representing an export action.
   */
  public static final Icon EXPORT = new RasterIcon("/icons/essentials/Export");
  /**
   * An icon showing an eye.
   */
  public static final Icon EYE = new RasterIcon("/icons/essentials/Eye");
  /**
   * An icon showing an eye crossed.
   */
  public static final Icon EYE_CROSSED = new RasterIcon("/icons/essentials/EyeCrossed");
  /**
   * An icon showing a filter.
   */
  public static final Icon FILTER = new RasterIcon("/icons/essentials/Filter");
  /**
   * An icon showing a flag.
   */
  public static final Icon FLAG = new RasterIcon("/icons/essentials/Flag");
  /**
   * An icon showing a folder.
   */
  public static final Icon FOLDER = new RasterIcon("/icons/essentials/Folder");
  /**
   * An icon representing fullscreen mode.
   */
  public static final Icon FULLSCREEN = new RasterIcon("/icons/essentials/Fullscreen");
  /**
   * An icon showing a gear.
   */
  public static final Icon GEAR = new RasterIcon("/icons/essentials/Gear");
  /**
   * An icon showing a globe.
   */
  public static final Icon GLOBE = new RasterIcon("/icons/essentials/Globe");
  /**
   * An icon showing a graph.
   */
  public static final Icon GRAPH = new RasterIcon("/icons/essentials/Graph");
  /**
   * An icon representing an import action.
   */
  public static final Icon IMPORT = new RasterIcon("/icons/essentials/Import");
  /**
   * An icon with an 'i'.
   */
  public static final Icon INFO = new RasterIcon("/icons/essentials/Info");
  /**
   * An icon showing a map marker.
   */
  public static final Icon MAP_MARKER = new RasterIcon("/icons/essentials/MapMarker");
  /**
   * An icon showing a minus sign in a circle.
   */
  public static final Icon MINUS = new RasterIcon("/icons/essentials/Minus");
  /**
   * An icon showing a paper.
   */
  public static final Icon PAPER = new RasterIcon("/icons/essentials/Paper");
  /**
   * An icon showing a pen.
   */
  public static final Icon PEN = new RasterIcon("/icons/essentials/Pen");
  /**
   * An icon showing two people.
   */
  public static final Icon PEOPLE = new RasterIcon("/icons/essentials/People");
  /**
   * An icon showing simple pie chart.
   */
  public static final Icon PIE_CHART1 = new RasterIcon("/icons/essentials/PieChart1");
  /**
   * An icon showing more complex pie chart.
   */
  public static final Icon PIE_CHART2 = new RasterIcon("/icons/essentials/PieChart2");
  /**
   * An icon showing a pin.
   */
  public static final Icon PIN = new RasterIcon("/icons/essentials/Pin");
  /**
   * An icon showing a pipette.
   */
  public static final Icon PIPETTE = new RasterIcon("/icons/essentials/Pipette");
  /**
   * An icon showing a plus sign in a circle.
   */
  public static final Icon PLUS = new RasterIcon("/icons/essentials/Plus");
  /**
   * An icon showing a mouse pointer.
   */
  public static final Icon POINTER = new RasterIcon("/icons/essentials/Pointer");
  /**
   * An icon showing a question mark.
   */
  public static final Icon QUESTION_MARK = new RasterIcon("/icons/essentials/QuestionMark");
  /**
   * An icon showing a reload sign.
   */
  public static final Icon RELOAD = new RasterIcon("/icons/essentials/Reload");
  /**
   * An icon representing ending fullscreen mode.
   */
  public static final Icon SHRINK_SCREEN = new RasterIcon("/icons/essentials/ShrinkScreen");
  /**
   * An icon showing a speech bubble.
   */
  public static final Icon SPEECH_BUBBLE = new RasterIcon("/icons/essentials/SpeechBubble");
  /**
   * An icon showing a thumbs down.
   */
  public static final Icon THUMBS_DOWN = new RasterIcon("/icons/essentials/ThumbsDown");
  /**
   * An icon showing a thumbs up.
   */
  public static final Icon THUMBS_UP = new RasterIcon("/icons/essentials/ThumbsUp");

  private Icons() {
  }

}
