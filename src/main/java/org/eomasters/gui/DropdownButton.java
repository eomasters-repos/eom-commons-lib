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

package org.eomasters.gui;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;

public class DropdownButton extends JButton {

  private final JPopupMenu popupMenu;

  public DropdownButton(ImageIcon icon) {
    this(null, icon);
  }

  public DropdownButton(String label) {
    this(label, null);
  }

  public DropdownButton(String label, ImageIcon icon) {
    setIcon(icon);
    setText(label);
    popupMenu = new JPopupMenu();
    popupMenu.setVisible(false);
    addActionListener(this::actionPerformed);
  }

  @Override
  public void setText(String text) {
    if (text == null) {
      super.setText("▼");
    } else {
      super.setText(text + " ▼");
    }
  }

  public void setPopupComponent(JComponent component) {
    popupMenu.removeAll();
    popupMenu.add(component);
  }

  public void showPopup(boolean show) {
    if (show) {
      popupMenu.show(DropdownButton.this, 0, DropdownButton.this.getHeight());
    } else {
      popupMenu.setVisible(false);
    }
  }

  private void actionPerformed(ActionEvent e) {
    showPopup(!popupMenu.isVisible());
  }

}
