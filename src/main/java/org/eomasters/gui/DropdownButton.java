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
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

public class DropdownButton extends JButton {

  private final JPopupMenu popupMenu;

  public DropdownButton(ImageIcon icon) {
    this(new JLabel(icon));
  }

  public DropdownButton(String label) {
    this(new JLabel(label));
  }

  private DropdownButton(JLabel westLabel) {
    setLayout(new BorderLayout(5, 0));
    westLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
    add(westLabel, BorderLayout.WEST);
    JLabel arrowLabel = new JLabel("▼");
    add(arrowLabel, BorderLayout.EAST);

    popupMenu = new JPopupMenu();
    popupMenu.setVisible(false);

    addActionListener(this::actionPerformed);
  }

  public void setPopupComponent(JComponent component) {
    popupMenu.removeAll();
    popupMenu.add(component);
  }

  public void showPopup(boolean show) {
    if(show) {
      popupMenu.show(DropdownButton.this, 0, DropdownButton.this.getHeight());
    } else {
      popupMenu.setVisible(false);
    }
  }

  private void actionPerformed(ActionEvent e) {
    showPopup(!popupMenu.isVisible());
  }

}
