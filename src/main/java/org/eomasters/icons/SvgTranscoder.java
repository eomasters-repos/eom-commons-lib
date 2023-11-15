package org.eomasters.icons;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import org.apache.batik.gvt.renderer.ImageRenderer;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

class SvgTranscoder extends ImageTranscoder {

  private BufferedImage image = null;

  public BufferedImage createImage(int w, int h) {
    image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    return image;
  }

  @Override
  protected ImageRenderer createRenderer() {
    ImageRenderer renderer = super.createRenderer();
    RenderingHints hints = renderer.getRenderingHints();
    hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); // Not a big difference
    hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    renderer.setRenderingHints(hints);
    return renderer;
  }

  public void writeImage(BufferedImage img, TranscoderOutput out) {
    // empty
  }

  public BufferedImage getImage() {
    return image;
  }
}
