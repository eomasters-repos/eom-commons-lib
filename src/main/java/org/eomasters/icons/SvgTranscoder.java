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
