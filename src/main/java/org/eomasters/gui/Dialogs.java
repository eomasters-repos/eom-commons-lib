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

package org.eomasters.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.prefs.Preferences;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Utility class for showing dialogs.
 */
public final class Dialogs {

  private static final String CONFIRMATION_NODE = "confirmation";


  /**
   * Shows a message dialog.
   *
   * @param title   the title
   * @param message the message
   */
  public static void message(String title, String message) {
    message(title, new JLabel(message));
  }

  /**
   * Shows a message dialog.
   *
   * @param title   the title
   * @param message the message component
   */
  public static void message(String title, JComponent message) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Shows a message dialog.
   *
   * @param title   the title
   * @param icon    the dialog icon
   * @param message the message component
   */
  public static void message(String title, Icon icon, JComponent message) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, icon);
  }

  /**
   * Shows an error dialog.
   *
   * @param title        the title
   * @param errorMessage the error message
   */
  public static void error(String title, String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage, title, JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Shows an error dialog.
   *
   * @param parentComponent the parent component
   * @param title           the title
   * @param errorMessage    the error message
   */
  public static void error(Component parentComponent, String title, String errorMessage) {
    JOptionPane.showMessageDialog(parentComponent, errorMessage, title, JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Shows an error dialog with a collapsible area showing more details about the error. The details area is collapsed
   * by default. The details can be exported to a file or to the clipboard.
   *
   * @param title     the title
   * @param message   the message
   * @param throwable the throwable
   */
  public static void error(String title, String message, Throwable throwable) {
    JPanel messagePanel = new JPanel(new MigLayout("top, left, fillx, gap 5 5"));
    messagePanel.add(new JLabel("<html>"+ message + ": <br>" + throwable.getMessage()), "wrap");
    StringWriter stringWriter = new StringWriter();
    throwable.printStackTrace(new PrintWriter(stringWriter));
    CollapsiblePanel detailsArea = CollapsiblePanel.createLongTextPanel("Details", stringWriter.toString());
    messagePanel.add(detailsArea, "top, left, grow, wrap");
    messagePanel.doLayout();
    JOptionPane.showMessageDialog(null, messagePanel, title, JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Shows a dialog asking the user for input.
   *
   * @param title  the title
   * @param prompt the prompt
   */
  public static String input(String title, String prompt) {
    return input(title, new JLabel(prompt));
  }

  /**
   * Shows a prompt dialog.
   *
   * @param title  the title
   * @param prompt the prompt component
   */
  public static String input(String title, JComponent prompt) {
    return JOptionPane.showInputDialog(null, prompt, title, JOptionPane.QUESTION_MESSAGE);
  }

  /**
   * Asks the user for confirmation.
   *
   * @param title    the title
   * @param question the question
   * @param parentComponent   the parent component to determine the frame the dialog will be displayed in
   * @return {@code true} if the user confirmed, {@code false} otherwise
   */
  public static boolean confirmation(String title, String question, Component parentComponent) {
    return confirmation(title, new JLabel(question), parentComponent);
  }


  /**
   * Asks the user for confirmation.
   *
   * @param title             the title
   * @param questionComponent the question component
   * @param parentComponent   the parent component to determine the frame the dialog will be displayed in
   * @return {@code true} if the user confirmed, {@code false} otherwise
   */
  public static boolean confirmation(String title, JComponent questionComponent, Component parentComponent) {
    return confirmation(title, questionComponent, parentComponent, null, null);
  }

  /**
   * Asks the user for confirmation. The user can choose to store the answer and not to be asked again.
   *
   * @param title             the title
   * @param questionComponent the question component
   * @param parentComponent   the parent component to determine the frame the dialog will be displayed in
   * @param preferenceKey     the preference key to store the answer
   * @param preferences       the preferences to store the answer
   * @return {@code true} if the user confirmed, {@code false} otherwise
   */
  public static boolean confirmation(String title, JComponent questionComponent, Component parentComponent,
      String preferenceKey, Preferences preferences) {
    if (preferenceKey != null && preferences != null) {
      if (preferences.node(CONFIRMATION_NODE).getBoolean(preferenceKey, false)) {
        return true;
      }
    }
    JPanel msgPanel = new JPanel(new BorderLayout());
    msgPanel.add(questionComponent, BorderLayout.NORTH);
    if (preferenceKey != null) {
      JCheckBox checkBox = new JCheckBox("Store my answer and don't ask again");
      checkBox.setHorizontalAlignment(JCheckBox.RIGHT);
      checkBox.setHorizontalTextPosition(JCheckBox.RIGHT);
      msgPanel.add(checkBox, BorderLayout.SOUTH);
    }
    boolean confirmed =
        JOptionPane.showConfirmDialog(parentComponent, msgPanel, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
            == JOptionPane.YES_OPTION;
    if (preferenceKey != null && preferences != null) {
      preferences.node(CONFIRMATION_NODE).putBoolean(preferenceKey, confirmed);
    }
    return confirmed;
  }
}
