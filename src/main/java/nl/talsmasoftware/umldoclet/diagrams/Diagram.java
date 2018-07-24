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
package nl.talsmasoftware.umldoclet.diagrams;

import net.sourceforge.plantuml.FileFormat;
import nl.talsmasoftware.umldoclet.configuration.Configuration;
import nl.talsmasoftware.umldoclet.uml.UMLFile;
import nl.talsmasoftware.umldoclet.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static java.util.Objects.requireNonNull;
import static nl.talsmasoftware.umldoclet.logging.Message.INFO_GENERATING_FILE;
import static nl.talsmasoftware.umldoclet.util.FileUtils.ensureParentDir;
import static nl.talsmasoftware.umldoclet.util.FileUtils.withoutExtension;

public class Diagram {

    private final UMLFile umlFile;
    private final FileFormat format;
    private File diagramFile;

    public Diagram(UMLFile plantUMLFile, FileFormat format) {
        this.umlFile = requireNonNull(plantUMLFile, "PlantUML file is <null>.");
        this.format = requireNonNull(format, "Diagram file format is <null>.");
    }

    private File getDiagramFile() {
        if (diagramFile == null) {
            Configuration config = umlFile.getConfiguration();
            File destinationDir = new File(config.destinationDirectory());
            String relativePumlFile = FileUtils.relativePath(destinationDir, umlFile.pumlFile());
            diagramFile = config.images().directory()
                    .map(imgDir -> new File(destinationDir, imgDir))
                    .map(imgDir -> new File(imgDir, relativePumlFile.replace('/', '.')))
                    .map(file -> new File(file.getParent(), withDiagramExtension(file.getName())))
                    .orElseGet(() -> new File(destinationDir, withDiagramExtension(relativePumlFile)));
        }
        return diagramFile;
    }

    private String withDiagramExtension(String path) {
        return withoutExtension(path) + format.getFileSuffix();
    }

    public void render() {
        File diagramFile = getDiagramFile();
        try (OutputStream out = new FileOutputStream(ensureParentDir(diagramFile))) {
            umlFile.getConfiguration().logger().info(INFO_GENERATING_FILE, diagramFile);

            // TODO

        } catch (IOException ioe) {
            throw new IllegalStateException("I/O error rendering " + this + ": " + ioe.getMessage(), ioe);
        }
    }

    @Override
    public String toString() {
        return getDiagramFile().getPath();
    }

}
