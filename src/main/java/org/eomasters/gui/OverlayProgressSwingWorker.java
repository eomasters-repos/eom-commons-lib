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
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import org.eomasters.icons.Icon.SIZE;
import org.eomasters.icons.Icons;
import org.eomasters.utils.ImageUtils;

public class OverlayProgressSwingWorker extends SwingWorker<Void, Void> {

  private SyncComponentSizeListener sizeListener;

  public static void main(String[] args) {
    final JFrame frame = new JFrame("OverlayProgressSwingWorker");
    JButton progressBtn = new JButton(Icons.EYE.getImageIcon(SIZE.S24));
    JPanel overlayed = new JPanel(new BorderLayout());
    overlayed.add(progressBtn, BorderLayout.NORTH);
    overlayed.add(new JList<>(new String[]{"1", "2", "3", "4", "5"}), BorderLayout.CENTER);

    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(overlayed, BorderLayout.CENTER);
    contentPane.add(new JButton("Visible"), BorderLayout.SOUTH);

    progressBtn.addActionListener(e -> new OverlayProgressSwingWorker(overlayed, () -> {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException ignored) {
      }
    }).execute());

    frame.setSize(400, 600);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    SwingUtilities.invokeLater(() -> frame.setVisible(true));
  }

  private static JPopupMenu popupComponent;
  private final JComponent component;
  private final Runnable runnable;
  private Cursor originalCursor;


  /**
   * Creates a new swing worker that shows a progress indicator on a glass pane while it is running.
   *
   * @param component the component which is overlayed with a progress indicator
   * @param runnable  the runnable to execute
   */
  public OverlayProgressSwingWorker(JComponent component, Runnable runnable) {
    this.component = component;
    this.runnable = runnable;
    initPopup(component);
  }

  private void initPopup(JComponent component) {
    Rectangle bounds = component.getBounds();
    originalCursor = component.getCursor();
    if (component.isVisible() && bounds.width != 0 && bounds.height != 0) {
      AnimatedImage image;
      try {
        URL resource = OverlayProgressSwingWorker.class.getResource("/icons/progress/indefinite.gif");
        Image[] frames = ImageUtils.loadFramesFromGif(ImageIO.createImageInputStream(Objects.requireNonNull(resource).openStream()));
        image = new AnimatedImage(frames, 0.2);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      image.setBackground(new Color(0, 0, 0, 89));
      image.setOpaque(false);

      component.setCursor(new Cursor(Cursor.WAIT_CURSOR));

      JPanel overplayPanel = new JPanel(new BorderLayout());
      overplayPanel.setPreferredSize(component.getSize());
      overplayPanel.setBorder(component.getBorder());
      overplayPanel.add(image, BorderLayout.CENTER);
      overplayPanel.setBounds(bounds);
      overplayPanel.setBackground(new Color(0, 0, 0, 0));
      overplayPanel.setOpaque(false);
      popupComponent = new JPopupMenu() {
        @Override
        public void menuSelectionChanged(boolean isIncluded) {
          // override to prevent the popup from closing when the mouse is clicked outside the popup
        }
      };

      sizeListener = new SyncComponentSizeListener();
      component.addComponentListener(sizeListener);
      popupComponent.setBorder(BorderFactory.createEmptyBorder());
      popupComponent.add(overplayPanel);
      popupComponent.pack();
      popupComponent.setOpaque(false);
      if (component.isShowing()) {
        popupComponent.show(component, 0, 0);
      }
      image.start();
    }
  }

  @Override
  protected Void doInBackground() {
    runnable.run();
    return null;
  }

  @Override
  protected void done() {
    component.setCursor(originalCursor);
    if (popupComponent != null) {
      popupComponent.setVisible(false);
      component.removeComponentListener(sizeListener);
    }
  }

  private class SyncComponentSizeListener implements ComponentListener {

    @Override
    public void componentResized(ComponentEvent e) {
      popupComponent.setPopupSize(e.getComponent().getBounds().getSize());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
      popupComponent.setLocation(e.getComponent().getLocationOnScreen());
    }

    @Override
    public void componentShown(ComponentEvent e) {
      if(!isDone()) {
        popupComponent.setVisible(true);
      }
    }

    @Override
    public void componentHidden(ComponentEvent e) {
      popupComponent.setVisible(false);
    }
  }
}
