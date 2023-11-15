package org.eomasters.icons;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;

public class IconTest {

  public static void main(String[] args) throws IllegalAccessException, TranscoderException {
    final JFrame frame = new JFrame("Icon Test");
    frame.setPreferredSize(new Dimension(400, 400));
    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(new JLabel("Test"), BorderLayout.NORTH);
    SvgTranscoder transcoder = new SvgTranscoder();
    TranscodingHints hints = new TranscodingHints();
    hints.put(ImageTranscoder.KEY_WIDTH, (float) 500);
    hints.put(ImageTranscoder.KEY_HEIGHT, (float) 500);
    // hints.put(ImageTranscoder.KEY_WIDTH, (float) size.getSize());
    // hints.put(ImageTranscoder.KEY_HEIGHT, (float) size.getSize());
    hints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
    hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
    hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG);
    hints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, false);
    transcoder.setTranscodingHints(hints);
    InputStream resource = Icons.class.getResourceAsStream("/org/eomasters/eomtbx/icons/" + "Minus" + ".svg");
    transcoder.transcode(new TranscoderInput(resource), null);
    JPanel drawPanel = new JPanel() {
      @Override
      public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(transcoder.getImage(), 30, 30, 16, 16, null);
        g.drawImage(transcoder.getImage(), 50, 30, 32, 32, null);
      }
    };
    drawPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    contentPane.add(drawPanel, BorderLayout.CENTER);



    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    SwingUtilities.invokeLater(() -> frame.setVisible(true));
  }


}
