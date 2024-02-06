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

import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Highlighter {

  private Border oldBorder;
  private Color color = Color.red.darker();
  private int margin = 2;
  private double duration = 2.0;
  private JComponent theComponent;
  private String infoMessage;
  private PopupComponent popupComponent;

  public void setColor(Color color) {
    this.color = color;
  }

  public void setMargin(int margin) {
    this.margin = margin;
  }

  /**
   * Sets the highlighting duration in seconds.
   * @param duration in seconds
   */
  public void setDuration(double duration) {
    this.duration = duration;
  }

  public void setInfoMessage(String infoMessage) {
    this.infoMessage = infoMessage;
  }

  public void highlight(JComponent component) {
    oldBorder = component.getBorder();
    theComponent = component;
    theComponent.setBorder(new MatteBorder(margin, margin, margin, margin, color));
    if (infoMessage != null) {
      popupComponent = createPopup(infoMessage);
    }

    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.schedule(this::removeHighlighting, (long) (duration * 1000), TimeUnit.MILLISECONDS);
  }

  private PopupComponent createPopup(String message) {
    MultiLineText textField = new MultiLineText(message);
    textField.setFont(textField.getFont().deriveFont(Font.BOLD));
    textField.setEditable(false);
    textField.setBorder(new EmptyBorder(0, 0, 0, 0));
    float[] rgbComponents = color.getRGBComponents(null);
    textField.setBackground(new Color(color.getColorSpace(), rgbComponents, rgbComponents[3] * 0.3f));
    PopupComponent popupComponent = new PopupComponent(textField);
    popupComponent.showPopupAt(theComponent.getLocationOnScreen().x,theComponent.getLocationOnScreen().y + theComponent.getHeight());
    return popupComponent;
  }

  private void removeHighlighting() {
    theComponent.setBorder(oldBorder);
    if (popupComponent != null) {
      popupComponent.hidePopup();
    }
  }

  public static void error(JComponent component, String message) {
    Highlighter highlighter = new Highlighter();
    highlighter.setInfoMessage(message);
    highlighter.highlight(component);
  }
}

