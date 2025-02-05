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
package net.sourceforge.plantuml.cucadiagram;

import java.util.ArrayList;
import java.util.List;

class MagmaList {

	private final List<Magma> all = new ArrayList<>();

	public void add(Magma magma) {
		all.add(magma);
	}

	public MagmaList getMagmas(IGroup group) {
		final MagmaList result = new MagmaList();
		for (Magma m : all) {
			if (m.getContainer() == group) {
				result.add(m);
			}
		}
		return result;
	}

	public int size() {
		return all.size();
	}

	public void putInSquare() {
		final SquareLinker<Magma> linker = new SquareLinker<Magma>() {
			public void topDown(Magma top, Magma down) {
				top.linkToDown(down);
			}

			public void leftRight(Magma left, Magma right) {
				left.linkToRight(right);
			}
		};
		new SquareMaker<Magma>().putInSquare(all, linker);

	}

}
