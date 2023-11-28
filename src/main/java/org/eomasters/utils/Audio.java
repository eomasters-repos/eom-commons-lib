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

package org.eomasters.utils;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

  /**
   * Plays the given audio input stream. Supported formats are: WAV, AU SND, and AIFF.
   * @param audioInput the audio input stream
   * @throws AudioException if the audio input stream cannot be played
   * @see javax.sound.sampled.AudioFileFormat.Type
   */
  public static void play(InputStream audioInput) throws AudioException {
    try {
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioInput);
      Clip clip = AudioSystem.getClip();
      clip.open(audioStream);
      clip.start();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      throw new AudioException(e);
    }
  }

}
