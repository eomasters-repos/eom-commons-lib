/*-
 * ========================LICENSE_START=================================
 * EOMTBX PRO - EOMasters Toolbox PRO for SNAP
 * -> https://www.eomasters.org/sw/EOMTBX
 * ======================================================================
 * Copyright (C) 2023 Marco Peters
 * ======================================================================
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * -> http://www.gnu.org/licenses/gpl-3.0.html
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
