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

import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Representing an icon and provides access to the different sizes.
 */
public class RasterIcon extends Icon {

  /**
   * Creates a new Icon with the given base path without size and file extension.
   *
   * @param path the base path
   */
  public RasterIcon(String path) {
    super(path);
  }

  @Override
  protected ImageIcon createIcon(SIZE size) {
    URL resource = Icons.class.getResource(getPath() + "_" + size.getSize() + ".png");
    if (resource == null) {
      throw new IllegalStateException("Icon '" + getPath() + "' with size " + size.getSize() + " not found.");
    }
    return new ImageIcon(resource);
  }

}
