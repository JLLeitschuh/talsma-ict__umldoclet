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

import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.security.SFile;

public class FileImageData {
	
	public static final int ERROR = 400;
	public static final int CRASH = 503;

	private final SFile file;
	private final ImageData imageData;

	public FileImageData(SFile file, ImageData imageData) {
		this.file = file;
		this.imageData = imageData;
	}

	public SFile getFile() {
		return file;
	}

	public ImageData getImageData() {
		return imageData;
	}

	public int getStatus() {
		if (imageData == null) {
			return 0;
		}
		return imageData.getStatus();
	}

}
