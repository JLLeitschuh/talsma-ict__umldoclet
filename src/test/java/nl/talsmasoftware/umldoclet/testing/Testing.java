/*
 * Copyright 2016-2018 Talsma ICT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.talsmasoftware.umldoclet.testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by sjoerd on 02-03-16.
 */
public class Testing {

    /**
     * Determine the newline for this OS.
     */
    public static final String NEWLINE;

    static {
        Writer writer = new StringWriter();
        new PrintWriter(writer).println();
        NEWLINE = writer.toString();
    }


    /**
     * Reads a file with a relative path from the test-content "umldoclet" path.
     *
     * @param name the relative path to the file from the "umldoclet" directory.
     * @return The content of the file (using UTF-8 encoding).
     * @deprecated Test different directories too
     */
    @Deprecated
    public static String readFile(String name) {
        return read(new File("target/test-content/nl/talsmasoftware/umldoclet", name));
    }

    public static String read(File file) {
        try (InputStream in = new FileInputStream(file)) {
            return readUml(in);
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Cannot read from \"%s\": %s.", file, e.getMessage()), e);
        }
    }

    public static String readClassUml(Class<?> type) {
        try (InputStream in = new FileInputStream("target/apidocs/" + type.getName().replace('.', '/') + ".puml")) {
            return readUml(in);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot read .puml file of " + type, e);
        }
    }

    public static String readUml(InputStream inputStream) {
        try (Reader in = new InputStreamReader(inputStream, "UTF-8")) {
            StringWriter out = new StringWriter();
            char[] buf = new char[1024];
            for (int read = in.read(buf); read >= 0; read = in.read(buf)) {
                out.write(buf, 0, read);
            }
            return out.toString();
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read from stream: " + ioe.getMessage(), ioe);
        }
    }

}
