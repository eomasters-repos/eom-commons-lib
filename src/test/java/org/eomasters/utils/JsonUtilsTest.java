/*-
 * ========================LICENSE_START=================================
 * EOMTBX PRO - EOMasters Toolbox PRO for SNAP
 * -> https://www.eomasters.org/sw/EOMTBX
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

class JsonUtilsTest {

  @Test
  void readMap() throws IOException {
    Map<String, Instance> map = JsonUtils.readMap(JsonUtilsTest.class.getResourceAsStream("instances.json"),
        new TypeToken<Map<String, Instance>>() {
        });
    assertEquals(3, map.size());
    Set<String> strings = map.keySet();
    ArrayList<String> temp = new ArrayList<>();
    Collections.addAll(temp, "A", "B", "Z");
    assertTrue(strings.containsAll(temp));

    Instance a = map.get("A");
    assertEquals("A", a.name);
    assertEquals("Value A", a.description);
    assertEquals(6.0, a.value);

    Instance b = map.get("B");
    assertEquals("B", b.name);
    assertEquals("Value B", b.description);
    assertEquals(7.5, b.value);

    Instance z = map.get("Z");
    assertEquals("OMEGA", z.name);
    assertEquals("Value Z", z.description);
    assertEquals(1.06589, z.value);
  }

  private static class Instance {

    public String name;
    public double value;
    public String description;
  }
}
