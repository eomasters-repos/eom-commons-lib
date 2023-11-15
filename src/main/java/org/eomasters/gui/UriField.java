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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

public class UriField extends JTextField {
  private final static boolean IS_BROWSING_SUPPORTED =
      Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);

  private final Font editFont;
  private final Font clickFont;

  private Cursor previousCursor;


  public UriField() {
    this(null);
  }

  public UriField(URI uri) {
    super(uri == null ? "" : uri.toString());
    setForeground(Color.BLUE.darker());
    editFont = getFont();
    Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
    fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    clickFont = editFont.deriveFont(fontAttributes);
    setEditable(false);
    MouseHandler mouseHandler = new MouseHandler(this);
    addMouseListener(mouseHandler);
    addMouseMotionListener(mouseHandler);
    addKeyListener(new CtrlKeyListener());
  }

  @Override
  public void setEditable(boolean editable) {
    setFont(editable ? editFont : clickFont);
    super.setEditable(editable);
  }

  @Override
  public void setText(String t) {
    super.setText(t);
  }

  public void setUri(URI uri) {
    setText(uri == null ? "" : uri.toString());
  }

  private boolean isClickable(boolean controlDown) {
    if (IS_BROWSING_SUPPORTED && !getText().isBlank()) {
      return !isEditable() || controlDown;
    }
    return false;
  }

  private void setHandCursor() {
    previousCursor = getCursor();
    setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  private void resetCursor() {
    setCursor(previousCursor != null ? previousCursor : new Cursor(Cursor.DEFAULT_CURSOR));
  }

  private static class MouseHandler extends MouseInputAdapter {

    private final UriField uriField;

    public MouseHandler(UriField uriField) {
      this.uriField = uriField;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (uriField.isClickable(e.isControlDown())) {
        try {
          Desktop.getDesktop().browse(URI.create(uriField.getText()));
        } catch (Throwable t) {
          JOptionPane.showMessageDialog(uriField,
              String.format("Cannot open URL: %s", uriField.getText()), "Cannot open URL", JOptionPane.ERROR_MESSAGE);
        }
        super.mouseClicked(e);
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      if (uriField.isClickable(e.isControlDown())) {
        uriField.setHandCursor();
      }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      if (uriField.isClickable(e.isControlDown())) {
        uriField.setHandCursor();
      }
    }

    @Override
    public void mouseExited(MouseEvent e) {
      uriField.resetCursor();
    }

  }

  private class CtrlKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
        if (isClickable(true)) {
          setHandCursor();
        }
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
        if (isClickable(false)) {
          setHandCursor();
        }
      }

    }
  }
}
