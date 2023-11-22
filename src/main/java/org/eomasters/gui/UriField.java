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
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.net.URI;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import net.miginfocom.swing.MigLayout;


public class UriField extends JPanel {

  private String label;
  private String uri;
  private boolean editable;

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel(new MigLayout("top, left, insets 0, gap 10"));
    frame.add(panel);

    UriField uriField1 = new UriField(URI.create("https://www.eomasters.org"));
    uriField1.setEditable(true);
    UriField uriField2 = new UriField("https://www.eomasters.org");
    uriField2.setEditable(true);
    UriField uriField3 = new UriField("[EOMasters](https://www.eomasters.org)");
    uriField3.setEditable(true);
    UriField uriField4 = new UriField();
    uriField4.setEditable(true);

    panel.add(uriField1, "wrap");
    panel.add(uriField2, "wrap");
    panel.add(uriField3, "wrap");
    panel.add(uriField4, "wrap");

    frame.pack();
    SwingUtilities.invokeLater(() -> frame.setVisible(true));
  }

  private final static boolean IS_BROWSING_SUPPORTED =
      Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);

  private final Font editFont;
  private final Font clickFont;

  private Cursor previousCursor;
  private JTextField textField;


  public UriField() {
    this(null, null);
  }

  public UriField(URI uri) {
    this(uri.toString());
  }

  public UriField(String uri) {
    this(uri, uri);
  }


  private UriField(String uri, String label) {
    this.uri = uri;
    this.label = label;
    setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    textField = new JTextField();
    textField.setForeground(Color.BLUE.darker());
    textField.setEditable(false);
    add(textField, "top, left, growx, pushx, wrap, insets 0");
    editFont = textField.getFont();
    clickFont = editFont.deriveFont(Map.of(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON));
    MouseHandler mouseHandler = new MouseHandler(this);
    textField.addMouseListener(mouseHandler);
    textField.addMouseMotionListener(mouseHandler);
    textField.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        updateUri();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        updateUri();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
      }

      private void updateUri() {
        parseUriLabel(textField.getText());
      }
    });
    updateState();
  }

  private void parseUriLabel(String text) {
    if (text != null && text.startsWith("[")) {
      Pattern pattern = Pattern.compile("\\A\\[(.*?)]\\((.*?)\\)\\Z");
      Matcher matcher = pattern.matcher(text);
      // extract label and uri from markdown
      if (matcher.find()) {
        uri = matcher.group(2);
        label = matcher.group(1);
      } else {
        ComponentHighlighter highlighter = new ComponentHighlighter();
        highlighter.setInfoMessage("Invalid Link Format");
        highlighter.highlight(textField);
      }
    } else {
      uri = text;
      label = text;
    }
  }

  public void setEditable(boolean editable) {
    this.editable = editable;
    updateState();
  }

  public void setUri(String uri) {
    setUri(uri, null);
  }

  public void setUri(URI uri) {
    setUri(uri.toString(), uri.toString());
  }

  public void setUri(String uri, String label) {
    this.uri = uri;
    if (label != null) {
      this.label = label;
    } else {
      this.label = uri;
    }
    updateState();
  }

  public String getUri() {
    return uri;
  }

  public String getLabel() {
    return label;
  }

  public void updateState() {
    textField.setEditable(editable);
    if (editable) {
      textField.setText(getMarkdown());
      textField.setFont(editFont);
      setToolTipText("Define Link: [Label](URL)");
    } else {
      textField.setText(label);
      textField.setFont(clickFont);
      setToolTipText("Click to open Link");
    }
  }

  private String getMarkdown() {
    return String.format("[%s](%s)", label != null ? label : "", uri != null ? uri : "");
  }

  private boolean isClickable() {
    if (IS_BROWSING_SUPPORTED) {
      return !editable;
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
      if (uriField.isClickable()) {
        try {
          Desktop.getDesktop().browse(URI.create(uriField.uri));
        } catch (Throwable t) {
          JOptionPane.showMessageDialog(uriField,
              String.format("Cannot open URL: %s", uriField.uri), "Cannot open URL", JOptionPane.ERROR_MESSAGE);
        }
        super.mouseClicked(e);
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      if (uriField.isClickable()) {
        uriField.setHandCursor();
      }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      if (uriField.isClickable()) {
        uriField.setHandCursor();
      }
    }

    @Override
    public void mouseExited(MouseEvent e) {
      uriField.resetCursor();
    }

  }

}
