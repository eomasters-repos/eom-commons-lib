/*-
 * ========================LICENSE_START=================================
 * EOM Commons - Library of common utilities for Java
 * -> https://www.eomasters.org/
 * ======================================================================
 * Copyright (C) 2023 - 2024 Marco Peters
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
    SIZE size = SIZE.S48;
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
  public static final Icon ARROW_DOWN = new SvgIcon("/icons/essentials/ArrowDown", Icons.class);
  /**
   * An icon showing an arrow up.
   */
  public static final Icon ARROW_UP = new SvgIcon("/icons/essentials/ArrowUp", Icons.class);
  /**
   * An icon showing an arrow left.
   */
  public static final Icon ARROW_Left = new SvgIcon("/icons/essentials/ArrowLeft", Icons.class);
  /**
   * An icon showing an arrow right.
   */
  public static final Icon ARROW_RIGHT = new SvgIcon("/icons/essentials/ArrowRight", Icons.class);
  /**
   * An icon showing a bug.
   */
  public static final Icon BUG = new SvgIcon("/icons/essentials/Bug", Icons.class);
  /**
   * An icon showing checkmark in circle.
   */
  public static final Icon CHECKMARK = new SvgIcon("/icons/essentials/Checkmark", Icons.class);
  /**
   * An icon showing a clock.
   */
  public static final Icon CLOCK = new SvgIcon("/icons/essentials/Clock", Icons.class);
  /**
   * An icon showing a cloud.
   */
  public static final Icon CLOUD = new SvgIcon("/icons/essentials/Cloud", Icons.class);
  /**
   * An icon showing a cloud, with arrow up into the cloud.
   */
  public static final Icon CLOUD_UP = new SvgIcon("/icons/essentials/CloudUp", Icons.class);
  /**
   * An icon showing a cloud, with arrow down out of the cloud.
   */
  public static final Icon CLOUD_DOWN = new SvgIcon("/icons/essentials/CloudDown", Icons.class);
  /**
   * An icon representing code or a command line prompt.
   */
  public static final Icon CODE = new SvgIcon("/icons/essentials/Code", Icons.class);
  /**
   * An icon showing a computer.
   */
  public static final Icon COMPUTER = new SvgIcon("/icons/essentials/Computer", Icons.class);
  /**
   * An icon showing a credit card.
   */
  public static final Icon CREDIT_CARD = new SvgIcon("/icons/essentials/CreditCard", Icons.class);
  /**
   * An icon showing a database barrel.
   */
  public static final Icon DATABASE = new SvgIcon("/icons/essentials/Database", Icons.class);
  /**
   * An icon showing a document.
   */
  public static final Icon DOCUMENT = new SvgIcon("/icons/essentials/Document", Icons.class);
  /**
   * An icon showing a dollar sign.
   */
  public static final Icon DOLLAR = new SvgIcon("/icons/essentials/Dollar", Icons.class);
  /**
   * An icon showing a drawer.
   */
  public static final Icon DRAWER = new SvgIcon("/icons/essentials/Drawer", Icons.class);
  /**
   * An icon showing a euro sign.
   */
  public static final Icon EURO = new SvgIcon("/icons/essentials/Euro", Icons.class);
  /**
   * An icon representing an export action.
   */
  public static final Icon EXPORT = new SvgIcon("/icons/essentials/Export", Icons.class);
  /**
   * An icon showing an eye.
   */
  public static final Icon EYE = new SvgIcon("/icons/essentials/Eye", Icons.class);
  /**
   * An icon showing an eye crossed.
   */
  public static final Icon EYE_CROSSED = new SvgIcon("/icons/essentials/EyeCrossed", Icons.class);
  /**
   * An icon showing a filter.
   */
  public static final Icon FILTER = new SvgIcon("/icons/essentials/Filter", Icons.class);
  /**
   * An icon showing a flag.
   */
  public static final Icon FLAG = new SvgIcon("/icons/essentials/Flag", Icons.class);
  /**
   * An icon showing a folder.
   */
  public static final Icon FOLDER = new SvgIcon("/icons/essentials/Folder", Icons.class);
  /**
   * An icon representing fullscreen mode.
   */
  public static final Icon FULLSCREEN = new SvgIcon("/icons/essentials/Fullscreen", Icons.class);
  /**
   * An icon showing a gear.
   */
  public static final Icon GEAR = new SvgIcon("/icons/essentials/Gear", Icons.class);
  /**
   * An icon showing a globe.
   */
  public static final Icon GLOBE = new SvgIcon("/icons/essentials/Globe", Icons.class);
  /**
   * An icon showing a graph.
   */
  public static final Icon GRAPH = new SvgIcon("/icons/essentials/Graph", Icons.class);
  /**
   * An icon representing an import action.
   */
  public static final Icon IMPORT = new SvgIcon("/icons/essentials/Import", Icons.class);
  /**
   * An icon with an 'i'.
   */
  public static final Icon INFO = new SvgIcon("/icons/essentials/Info", Icons.class);
  /**
   * An icon showing a map marker.
   */
  public static final Icon MAP_MARKER = new SvgIcon("/icons/essentials/MapMarker", Icons.class);
  /**
   * An icon showing a minus sign in a circle.
   */
  public static final Icon MINUS = new SvgIcon("/icons/essentials/Minus", Icons.class);
  /**
   * An icon showing a paper.
   */
  public static final Icon PAPER = new SvgIcon("/icons/essentials/Paper", Icons.class);
  /**
   * An icon showing a pen.
   */
  public static final Icon PEN = new SvgIcon("/icons/essentials/Pen", Icons.class);
  /**
   * An icon showing two people.
   */
  public static final Icon PEOPLE = new SvgIcon("/icons/essentials/People", Icons.class);
  /**
   * An icon showing simple pie chart.
   */
  public static final Icon PIE_CHART1 = new SvgIcon("/icons/essentials/PieChart1", Icons.class);
  /**
   * An icon showing more complex pie chart.
   */
  public static final Icon PIE_CHART2 = new SvgIcon("/icons/essentials/PieChart2", Icons.class);
  /**
   * An icon showing a pin.
   */
  public static final Icon PIN = new SvgIcon("/icons/essentials/Pin", Icons.class);
  /**
   * An icon showing a pipette.
   */
  public static final Icon PIPETTE = new SvgIcon("/icons/essentials/Pipette", Icons.class);
  /**
   * An icon showing a plus sign in a circle.
   */
  public static final Icon PLUS = new SvgIcon("/icons/essentials/Plus", Icons.class);
  /**
   * An icon showing a mouse pointer.
   */
  public static final Icon POINTER = new SvgIcon("/icons/essentials/Pointer", Icons.class);
  /**
   * An icon showing a question mark.
   */
  public static final Icon QUESTION_MARK = new SvgIcon("/icons/essentials/QuestionMark", Icons.class);
  /**
   * An icon showing a reload sign.
   */
  public static final Icon RELOAD = new SvgIcon("/icons/essentials/Reload", Icons.class);
  /**
   * An icon representing report.
   */
  public static final Icon REPORT = new SvgIcon("/icons/essentials/Report", Icons.class);
  /**
   * An icon representing ending fullscreen mode.
   */
  public static final Icon SHRINK_SCREEN = new SvgIcon("/icons/essentials/ShrinkScreen", Icons.class);
  /**
   * An icon showing a speech bubble.
   */
  public static final Icon SPEECH_BUBBLE = new SvgIcon("/icons/essentials/SpeechBubble", Icons.class);
  /**
   * An icon showing a thumbs down.
   */
  public static final Icon THUMBS_DOWN = new SvgIcon("/icons/essentials/ThumbsDown", Icons.class);
  /**
   * An icon showing a thumbs up.
   */
  public static final Icon THUMBS_UP = new SvgIcon("/icons/essentials/ThumbsUp", Icons.class);

  private Icons() {
  }

}
