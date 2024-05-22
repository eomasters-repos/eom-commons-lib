/*-
 * ========================LICENSE_START=================================
 * Toolbox Module - EOMasters Toolbox PRO for SNAP
 * -> https://www.eomasters.org/eomtbx/modules/toolbox
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// TODO: move to eom-commons
/**
 * Generic implementation for storing objects as json text using the
 * <a href="https://github.com/google/gson/#readme">Gson library</a>.
 */
public class GsonStore<T> {

    private final Gson gson;
    private final Class<T> type;

    /**
     * Creates a GsonStore for the provided type. Delegates to {@link GsonStore(Class, GsonStoreConfig)} with an empty
     * configuration definition
     * .
     * @param type the type of objects which shall be de-/serialized
     */
    public GsonStore(Class<T> type) {
        this(type, builder -> {/* empty */});
    }

    /**
     * Creates a GsonStore for the provided type. The configuration of the resulting json code can be
     * specified by providing an implementation of {@link GsonStoreConfig}.
     * <p>
     * In addition to the default configuration, the underlying {@link GsonBuilder} is pre-configured
     * to use pretty-printing and exclude transient fields.
     *
     * @param type the type of objects which shall be de-/serialized
     * @param config used to configure the {@link GsonBuilder}
     */
    public GsonStore(Class<T> type, GsonStoreConfig config) {
        this.type = type;
        final GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
        config.configBuilder(builder);
        gson = builder.create();
    }

    public T load(Path location) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(location)) {
            try {
                return gson.fromJson(reader, type);
            } catch (JsonSyntaxException | JsonIOException e) {
                throw new IOException(e);
            }
        }
    }

    public void save(T obj, Path location) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(location, StandardOpenOption.CREATE)) {
            try {
                gson.toJson(obj, writer);
            } catch (JsonIOException e) {
                throw new IOException(e);
            }
        }
    }

    public String asString(final T obj) {
        return gson.toJson(obj);
    }
}
