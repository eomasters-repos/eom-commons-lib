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

package org.eomasters.icons;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import org.eomasters.utils.ImageUtils;

public class SvgIcon extends Icon {

  public SvgIcon(String path, Class<?> loadingClass) {
    super(path, loadingClass);
  }

  @Override
  protected ImageIcon createIcon(SIZE size) {
    try {
      InputStream resource = getClassLoader().getResourceAsStream(getPath() + ".svg");
      return new ImageIcon(ImageUtils.loadSvgImage(resource, size.getSize(), size.getSize()));
    } catch (IOException e) {
      return new ImageIcon(new BufferedImage(size.getSize(), size.getSize(), BufferedImage.TYPE_INT_ARGB));
    }
  }

}
