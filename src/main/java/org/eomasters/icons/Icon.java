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

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public abstract class Icon {

  protected final String path;

  public Icon(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }

  public ImageIcon getImageIcon(SIZE size) {
    return createIcon(size);
  }

  public List<? extends Image> getImages() {
    ArrayList<Image> images = new ArrayList<>();
    SIZE[] sizes = SIZE.values();
    for (SIZE size : sizes) {
      images.add(getImageIcon(size).getImage());
    }
    return images;
  }

  protected abstract ImageIcon createIcon(SIZE size);

  public Icon withAddition(Icon addition) {
    return new IconWithAddition(this, addition);
  }

  public enum SIZE {
    S16(16), S24(24), S32(32), S48(48);

    private final int size;

    SIZE(int size) {
      this.size = size;
    }

    public int getSize() {
      return size;
    }
  }

}
