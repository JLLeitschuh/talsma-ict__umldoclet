/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  https://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * https://plantuml.com/patreon (only 1$ per month!)
 * https://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * Original Author:  Arnaud Roques
 */
package net.sourceforge.plantuml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.security.SFile;

public class NamedOutputStream extends OutputStream {

	private final OutputStream os;
	private final BaseFile basefile;

	public NamedOutputStream(SFile file) throws FileNotFoundException {
		this.os = file.createBufferedOutputStream();
		this.basefile = new BaseFile(file);
	}

	public void close() throws IOException {
		os.close();

	}

	public void flush() throws IOException {
		os.flush();

	}

	public void write(byte[] b) throws IOException {
		os.write(b);

	}

	public void write(byte[] b, int off, int len) throws IOException {
		os.write(b, off, len);
	}

	public void write(int b) throws IOException {
		os.write(b);
	}

	public BaseFile getBasefile() {
		return basefile;
	}

}
