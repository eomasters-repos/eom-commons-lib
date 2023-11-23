package org.eomasters.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class PromptTextField extends JTextField {

  private final JLabel label;

  PromptTextField(String prompt) {
    label = new JLabel(prompt, SwingConstants.CENTER);
    label.setForeground(Color.GRAY);
    this.addComponentListener(new BoundsSynchronizer(label));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.getText().isEmpty()) {
      label.paint(g);
    }
  }

  private static class BoundsSynchronizer extends ComponentAdapter {

    private final JComponent syncComponent;

    public BoundsSynchronizer(JComponent syncComponent) {
      this.syncComponent = syncComponent;
    }

    @Override
    public void componentResized(ComponentEvent e) {
      syncComponent.setBounds(e.getComponent().getBounds());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
      syncComponent.setLocation(e.getComponent().getLocation());
    }
  }
}
