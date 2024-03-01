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

package org.eomasters.utils;

import java.util.regex.Pattern;

public class TextUtils {

  private static final String MAIL_OWASP_REGEX = "^[a-zA-Z0-9_+&*-]+"
      + "(?:\\.[a-zA-Z0-9_+&*-]+)*@"
      + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

  private TextUtils() {
  }

  /**
   * Escaped characters in the provided text which have a special meaning in HTML.
   * These are the  characters: <, >, &, \, '
   *
   * @param text the text to escape
   * @return the text with escaped characters
   */
  public static String escapeHtml(String text) {
    return text.replace("<", "&lt;")
               .replace(">", "&gt;")
               .replace("&", "&amp;")
               .replace("\"", "&quot;")
               .replace("'", "&apos;");
  }

  /**
   * Checks whether the given address string is a valid email address.
   *
   * @param address the address to check
   * @return true if the address is valid
   */
  public static boolean isValidEmailAddress(String address) {
    return Pattern.matches(MAIL_OWASP_REGEX, address);
  }
}
