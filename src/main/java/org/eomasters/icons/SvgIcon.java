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

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.swing.ImageIcon;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;

class SvgIcon extends Icon {

  public SvgIcon(String name) {
    super(name);
  }

  @Override
  protected ImageIcon createIcon(SIZE size) {
    SvgTranscoder transcoder = new SvgTranscoder();
    TranscodingHints hints = new TranscodingHints();
    hints.put(ImageTranscoder.KEY_WIDTH, (float) 500);
    hints.put(ImageTranscoder.KEY_HEIGHT, (float) 500);
    hints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
    hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
    hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG);
    hints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, false);
    transcoder.setTranscodingHints(hints);
    try {
      InputStream resource = Icons.class.getResourceAsStream("/icons/" + getName() + ".svg");
      transcoder.transcode(new TranscoderInput(resource), null);
    } catch (TranscoderException e) {
      return new ImageIcon(new BufferedImage(size.getSize(),size.getSize(), BufferedImage.TYPE_INT_ARGB));
    }

    return new ImageIcon(transcoder.getImage().getScaledInstance(size.getSize(), size.getSize(), BufferedImage.SCALE_SMOOTH));
  }

}
